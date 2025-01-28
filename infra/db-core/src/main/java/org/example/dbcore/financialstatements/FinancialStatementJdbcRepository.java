package org.example.dbcore.financialstatements;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FinancialStatementJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
}
