package org.example.dbcore.corporate;

import org.example.domain.Corporate;

public class CorporateDbCoreMapper {
    public static CorporateEntity toEntity(Corporate domain) {
        return CorporateEntity.builder()
                .code(domain.getCode())
                .stockCode(domain.getStockCode())
                .name(domain.getName())
                .lastModified(domain.getLastModified())
                .build();
    }

    public static Corporate toDomain(CorporateEntity entity) {
        return Corporate.builder()
                .code(entity.getCode())
                .stockCode(entity.getStockCode())
                .name(entity.getName())
                .lastModified(entity.getLastModified())
                .build();
    }
}
