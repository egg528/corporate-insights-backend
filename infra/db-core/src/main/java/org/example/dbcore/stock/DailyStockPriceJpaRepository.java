package org.example.dbcore.stock;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface DailyStockPriceJpaRepository extends Repository<DailyStockPriceEntity, DailyStockPriceKey> {
    void save(DailyStockPriceEntity entity);

    Optional<DailyStockPriceEntity> findById(DailyStockPriceKey key);
}
