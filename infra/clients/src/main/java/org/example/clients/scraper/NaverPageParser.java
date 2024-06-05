package org.example.clients.scraper;

import org.example.domain.stock.DailyStockPrice;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class NaverPageParser {

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");
    private final String ONLY_NUMBER_REGEX = "\\D";

    public int parseLastPageNumber(Document document) {
        Element paginationTable = document.select("table.Nnavi").first();
        Objects.requireNonNull(paginationTable, "paginationTable must not be null");

        Element lastPageElement = paginationTable.select("td.pgRR a").first();
        Objects.requireNonNull(lastPageElement, "lastPageElement must not be null");

        String lastPageHref = lastPageElement.attr("href");
        String lastPageNumber = lastPageHref.substring(lastPageHref.lastIndexOf("=") + 1);

        return Integer.parseInt(lastPageNumber);
    }

    public Elements parsePriceTableRows(Document document) {
        Element table = document.select("table.type2").first();
        Objects.requireNonNull(table, "(price)table must not be null");

        return table.select("tr");
    }

    public boolean isPriceRow(Element row) {
        Elements cells = row.select("td");

        if (cells.size() != 7) {
            return false;
        }

        return !cells.get(0).text().isEmpty();
    }

    public DailyStockPrice parseDailyStockPrice(Element row, String stockCode) {
        Elements cells = row.select("td");

        LocalDate date = LocalDate.parse(cells.get(0).text(), DATE_FORMATTER);
        int closing = parseInt(cells.get(1).text());
        int variation = parseInt(cells.get(2).text());
        int opening = parseInt(cells.get(3).text());
        int high = parseInt(cells.get(4).text());
        int low = parseInt(cells.get(5).text());
        long volume = parseLong(cells.get(6).text());

        return DailyStockPrice.builder()
                .stockCode(stockCode)
                .date(date)
                .closing(closing)
                .variation(variation)
                .opening(opening)
                .high(high)
                .low(low)
                .volume(volume)
                .build();
    }

    private int parseInt(String text) {
        int value = Integer.parseInt(text.replaceAll(ONLY_NUMBER_REGEX, ""));
        return text.startsWith("하") ? -value : value;
    }

    private long parseLong(String text) {
        long value = Long.parseLong(text.replaceAll(ONLY_NUMBER_REGEX, ""));
        return text.startsWith("하") ? -value : value;
    }

}
