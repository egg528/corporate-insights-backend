package org.example.dbcore.stock;

import org.example.domain.stock.DailyStockPrice;

import java.util.Objects;

public class DailyStockPriceDbCoreMapper {

    private DailyStockPriceDbCoreMapper() {}

    public static DailyStockPriceEntity toEntity(DailyStockPrice domain) {
        Objects.requireNonNull(domain, "DailyStockPrice must not be null");
        return DailyStockPriceEntity.builder()
                .key(new DailyStockPriceKey(domain.getStockCode(), domain.getDate()))
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
                .stockCode(entity.getKey().getStockCode())
                .date(entity.getKey().getDate())
                .closing(entity.getClosing())
                .variation(entity.getVariation())
                .opening(entity.getOpening())
                .high(entity.getHigh())
                .low(entity.getLow())
                .volume(entity.getVolume())
                .build();
    }
}

