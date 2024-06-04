package org.example.domain.stock;

import java.time.LocalDate;
import java.util.List;

public interface DailyStockPriceImportPort {
    List<DailyStockPrice> importNonExistentDailyStockPrices(String stockCode, LocalDate recentSavedPriceDate);
}
