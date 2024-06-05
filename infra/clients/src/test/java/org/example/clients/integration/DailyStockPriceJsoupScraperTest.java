package org.example.clients.integration;

import lombok.RequiredArgsConstructor;
import org.example.clients.scraper.DailyStockPriceJsoupScraper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@DisplayName("DailyStockPriceJsoupScraper 통합 테스트")
public class DailyStockPriceJsoupScraperTest {
    private final DailyStockPriceJsoupScraper dailyStockPriceJsoupScraper;

    @Test
    @DisplayName("네이버 시세 페이지를 Scrap해서 마지막 페이지 값을 추출할 수 있다.")
    public void parseLasPageNumber() throws IOException {
        // given
        String samsungStockCode = "005930";

        // when
        int lastPageNumber = dailyStockPriceJsoupScraper.getLastPageNumber(samsungStockCode);

        // then
        assertTrue(701 <= lastPageNumber);
    }
}
