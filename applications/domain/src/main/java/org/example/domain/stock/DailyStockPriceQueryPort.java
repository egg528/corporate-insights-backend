package org.example.domain.stock;

import java.util.Optional;

public interface DailyStockPriceQueryPort {
    Optional<DailyStockPrice> findRecentDailyStockPrice(String stockCode);
}
