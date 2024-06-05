package org.example.api.adapter.scheduler;

import org.example.application.corporate.command.RenewCorporateUseCase;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CorporateRenewScheduler {
    private final RenewCorporateUseCase renewCorporateUseCase;
    private final JobLauncher jobLauncher;
    private final Job importDailyStockPricesJob;

    public CorporateRenewScheduler(
            RenewCorporateUseCase renewCorporateUseCase,
            JobLauncher jobLauncher,
            @Qualifier("importDailyStockPricesJob") Job importDailyStockPricesJob
    ) {
        this.renewCorporateUseCase = renewCorporateUseCase;
        this.jobLauncher = jobLauncher;
        this.importDailyStockPricesJob = importDailyStockPricesJob;
    }

    @Scheduled(cron = "0 0 10 * * ?", zone = "Asia/Seoul")
    public void renewAllCorporates() {
        renewCorporateUseCase.renewAll();
    }

    @Scheduled(cron = "0 0 16 * * ?", zone = "Asia/Seoul")
    public void setImportDailyStockPrices() throws JobExecutionException {
        jobLauncher.run(
                importDailyStockPricesJob,
                new JobParametersBuilder()
                        .addLocalDate("run.date", LocalDate.now()).toJobParameters()
        );
    }
}
