package org.example.dbcore.corporate;

import org.example.domain.Corporate;

import java.util.Objects;

public class CorporateDbCoreMapper {
    public static CorporateEntity toEntity(Corporate domain) {
        Objects.requireNonNull(domain, "Corporate must not be null");
        return CorporateEntity.builder()
                .code(domain.getCode())
                .stockCode(domain.getStockCode())
                .name(domain.getName())
                .lastModified(domain.getLastModified())
                .build();
    }

    public static Corporate toDomain(CorporateEntity entity) {
        Objects.requireNonNull(entity, "CorporateEntity must not be null");
        return Corporate.builder()
                .code(entity.getCode())
                .stockCode(entity.getStockCode())
                .name(entity.getName())
                .lastModified(entity.getLastModified())
                .build();
    }
}
