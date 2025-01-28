package org.example.dbcore.unit;

import org.example.dbcore.stock.DailyStockPriceDbCoreMapper;
import org.example.dbcore.stock.DailyStockPriceEntity;
import org.example.dbcore.stock.DailyStockPriceKey;
import org.example.domain.stock.DailyStockPrice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DailyStockPriceDbCoreMapper 단위 테스트")
public class DailyStockPriceDbCoreMapperTest {

    private DailyStockPrice domain = DailyStockPrice.builder()
            .stockCode("AAPL")
            .date(LocalDate.of(2023, 6, 1))
            .closing(150)
            .variation(2)
            .opening(148)
            .high(151)
            .low(147)
            .volume(1000000L)
            .build();

    private final DailyStockPriceKey key = new DailyStockPriceKey("AAPL", LocalDate.of(2023, 6, 1));

    private DailyStockPriceEntity entity = DailyStockPriceEntity.builder()
            .key(key)
            .closing(150)
            .variation(2)
            .opening(148)
            .high(151)
            .low(147)
            .volume(1000000L)
            .build();

    @Test
    @DisplayName("Domain을 Entity로 변환")
    void toEntity() {
        // When
        DailyStockPriceEntity result = DailyStockPriceDbCoreMapper.toEntity(domain);

        // Then
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertEquals("AAPL", result.getKey().getStockCode());
        assertEquals(LocalDate.of(2023, 6, 1), result.getKey().getDate());
        assertEquals(150, result.getClosing());
        assertEquals(2, result.getVariation());
        assertEquals(148, result.getOpening());
        assertEquals(151, result.getHigh());
        assertEquals(147, result.getLow());
        assertEquals(1000000L, result.getVolume());
    }

    @Test
    @DisplayName("Entity을 Domain로 변환")
    void toDomain() {
        // When
        DailyStockPrice result = DailyStockPriceDbCoreMapper.toDomain(entity);

        // Then
        assertNotNull(result);
        assertEquals("AAPL", result.getStockCode());
        assertEquals(LocalDate.of(2023, 6, 1), result.getDate());
        assertEquals(150, result.getClosing());
        assertEquals(2, result.getVariation());
        assertEquals(148, result.getOpening());
        assertEquals(151, result.getHigh());
        assertEquals(147, result.getLow());
        assertEquals(1000000L, result.getVolume());
    }
}
