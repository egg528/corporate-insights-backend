package org.example.dbcore.stock;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DailyStockPriceJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
}
