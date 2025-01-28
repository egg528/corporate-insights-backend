package org.example.clients.unit;

import org.example.clients.scraper.NaverPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NaverPageParser 단위 테스트")
public class NaverPageParserTest {
    private final NaverPageParser naverPageParser = new NaverPageParser();
    File naverPriceHtml = new File("src/test/resources/stock-price/samsung-price.html");

    @Test
    @DisplayName("네이버 시세 페이지 양식에서 마지막 페이지 값을 추출할 수 있다.")
    public void parseLasPageNumber() throws IOException {
        // given
        Document document = Jsoup.parse(naverPriceHtml, null, "https://example.com");

        // when
        int answer = naverPageParser.parseLastPageNumber(document);

        // then
        assertEquals(701, answer);
    }
}
