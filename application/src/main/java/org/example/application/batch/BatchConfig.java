package org.example.application.batch;

import lombok.RequiredArgsConstructor;
import org.example.dbcore.corporate.CorporateEntity;
import org.example.domain.stock.DailyStockPrice;
import org.example.domain.stock.DailyStockPriceCommandPort;
import org.example.domain.stock.DailyStockPriceImportPort;
import org.example.domain.stock.DailyStockPriceQueryPort;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * 일별 주가 정보 Scraping용 Batch 구성요소
 * scheduler를 이용하기 위해 관리 포인트를 줄이기 위해 application module에 작성
 */
@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final LocalDate ONE_DAY_BEFORE_KRX_FOUNDING = LocalDate.of(1956, 3, 2);
    private final DailyStockPriceCommandPort dailyStockPriceCommandPort;
    private final DailyStockPriceQueryPort dailyStockPriceQueryPort;
    private final DailyStockPriceImportPort dailyStockPriceImportPort;
    private final JobRepository jobRepository;
    private final DataSource dataSource;

    @Bean
    public ItemReader<CorporateEntity> reader() {
        JdbcCursorItemReader<CorporateEntity> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("SELECT * FROM corporates WHERE STOCK_CODE IS NOT NULL");
        reader.setRowMapper(CorporateEntity.getRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<CorporateEntity, List<DailyStockPrice>> processor() {
        return corporate -> {
            Optional<DailyStockPrice> recentDailyStockPrice =
                    dailyStockPriceQueryPort.findRecentDailyStockPrice(corporate.getStockCode());

            if (recentDailyStockPrice.isEmpty()) {
                return dailyStockPriceImportPort.importNonExistentDailyStockPrices(corporate.getStockCode(), ONE_DAY_BEFORE_KRX_FOUNDING);
            }

            return dailyStockPriceImportPort.importNonExistentDailyStockPrices(corporate.getStockCode(), recentDailyStockPrice.get().getDate());
        };
    }

    @Bean
    public ItemWriter<List<DailyStockPrice>> writer() {
        return chunk -> chunk.forEach(dailyStockPrices -> {
            dailyStockPrices.forEach(dailyStockPriceCommandPort::save);
        });
    }

    @Bean
    public Step importDailyStockPricesStep() {
        return new StepBuilder("importDailyStockPricesStep", jobRepository)
                .<CorporateEntity, List<DailyStockPrice>>chunk(10, new ResourcelessTransactionManager())
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job importDailyStockPricesJob() {
        return new JobBuilder("importDailyStockPricesJob", jobRepository)
                .start(importDailyStockPricesStep())
                .build();
    }
}
