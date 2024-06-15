package org.example.dbcore.stock;

import lombok.RequiredArgsConstructor;
import org.example.domain.stock.DailyStockPrice;
import org.example.domain.stock.DailyStockPriceCommandPort;
import org.example.domain.stock.DailyStockPriceQueryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DailyStockPriceDbCoreAdapter implements DailyStockPriceCommandPort, DailyStockPriceQueryPort {

    private final DailyStockPriceJpaRepository jpaRepository;

    @Override
    public void save(DailyStockPrice domain) {
        jpaRepository.save(DailyStockPriceDbCoreMapper.toEntity(domain));
    }

    @Override
    public Optional<DailyStockPrice> findRecentDailyStockPrice(String stockCode) {
        return jpaRepository.findTop1ByStockCodeOrderByDateDesc(stockCode)
                .map(DailyStockPriceDbCoreMapper::toDomain);
    }
}
