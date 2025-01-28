package org.example.dbcore.unit;

import org.example.dbcore.corporate.CorporateDbCoreMapper;
import org.example.dbcore.corporate.CorporateEntity;
import org.example.domain.corporate.Corporate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CorporateDbCoreMapper 단위 테스트")
public class CorporateDbCoreMapperTest {
    private final Corporate domain = Corporate.builder()
            .code("12345")
            .stockCode("14342")
            .name("Test Inc.")
            .lastModified(LocalDate.of(2023, 6, 1))
            .build();

    private final CorporateEntity entity = CorporateEntity.builder()
            .code("12345")
            .stockCode("14342")
            .name("Test Inc.")
            .lastModified(LocalDate.of(2023, 6, 1))
            .build();

    @Test
    @DisplayName("Domain을 Entity로 변환")
    void toEntity() {
        // When
        CorporateEntity result = CorporateDbCoreMapper.toEntity(domain);

        // Then
        assertNotNull(result);
        assertEquals("12345", result.getCode());
        assertEquals("14342", result.getStockCode());
        assertEquals("Test Inc.", result.getName());
        assertEquals(LocalDate.of(2023, 6, 1), result.getLastModified());
    }

    @Test
    @DisplayName("Entity을 Domain로 변환")
    void toDomain() {
        // When
        Corporate result = CorporateDbCoreMapper.toDomain(entity);

        // Then
        assertNotNull(result);
        assertEquals("12345", result.getCode());
        assertEquals("14342", result.getStockCode());
        assertEquals("Test Inc.", result.getName());
        assertEquals(LocalDate.of(2023, 6, 1), result.getLastModified());
    }
}
