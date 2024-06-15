package org.example.dbcore.stock;

import org.example.domain.stock.DailyStockPrice;

import java.util.Objects;

public class DailyStockPriceDbCoreMapper {

    private DailyStockPriceDbCoreMapper() {}

    public static DailyStockPriceEntity toEntity(DailyStockPrice domain) {
        Objects.requireNonNull(domain, "DailyStockPrice must not be null");
        return DailyStockPriceEntity.builder()
                .id(domain.getId())
                .stockCode(domain.getStockCode())
                .date(domain.getDate())
                .closing(domain.getClosing())
                .variation(domain.getVariation())
                .opening(domain.getOpening())
                .high(domain.getHigh())
                .low(domain.getLow())
                .volume(domain.getVolume())
                .build();
    }

    public static DailyStockPrice toDomain(DailyStockPriceEntity entity) {
        Objects.requireNonNull(entity, "DailyStockPriceEntity must not be null");
        return DailyStockPrice.builder()
                .id(entity.getId())
                .stockCode(entity.getStockCode())
                .date(entity.getDate())
                .closing(entity.getClosing())
                .variation(entity.getVariation())
                .opening(entity.getOpening())
                .high(entity.getHigh())
                .low(entity.getLow())
                .volume(entity.getVolume())
                .build();
    }
}

