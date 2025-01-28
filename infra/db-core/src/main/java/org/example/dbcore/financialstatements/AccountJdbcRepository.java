package org.example.dbcore.financialstatements;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
}
