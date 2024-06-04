package org.example.clients.stock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.clients.scraper.DailyStockPriceJsoupScraper;
import org.example.domain.stock.DailyStockPrice;
import org.example.domain.stock.DailyStockPriceImportPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyStockPriceScraperAdapter implements DailyStockPriceImportPort {

    private final DailyStockPriceJsoupScraper scrapper;

    @Override
    public List<DailyStockPrice> importNonExistentDailyStockPrices(String stockCode, LocalDate recentSavedPriceDate) {
        List<DailyStockPrice> prices = new ArrayList<>();

        try {
            int lastPage = scrapper.getLastPageNumber(stockCode);

            for (int i = 1; i <= lastPage; i++) {
                if (!scrapper.addPricesAndCheckNextPage(stockCode, i, recentSavedPriceDate, prices)) {
                    return prices;
                }
            }
        } catch (Exception e) {
            log.error("[Parsing Error]: stockCode {}", stockCode);
        }

        return prices;
    }
}
