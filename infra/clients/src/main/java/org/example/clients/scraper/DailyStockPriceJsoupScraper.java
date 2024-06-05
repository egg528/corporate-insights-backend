package org.example.clients.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.stock.DailyStockPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyStockPriceJsoupScraper {
    private final NaverPageParser naverPageParser;
    @Value("${scraper.daily-stock-price.url}")
    private String url;

    public int getLastPageNumber(String stockCode) throws IOException {
        Document document = Jsoup.connect(url.formatted(stockCode, 1)).get();

        return naverPageParser.parseLastPageNumber(document);
    }

    public boolean addPricesAndCheckNextPage(String stockCode, int page, LocalDate recentSavedPriceDate, List<DailyStockPrice> stockPrices) throws IOException {
        Document document = Jsoup.connect(url.formatted(stockCode, page)).get();

        Elements priceTableRows = naverPageParser.parsePriceTableRows(document);

        for (Element row : priceTableRows) {
            if (!naverPageParser.isPriceRow(row)) {
                continue;
            }

            DailyStockPrice price = naverPageParser.parseDailyStockPrice(row, stockCode);

            if (!price.getDate().isAfter(recentSavedPriceDate)) {
                return false;
            }

            stockPrices.add(price);
        }

        return true;
    }
}