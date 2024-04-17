package org.example.dbcore.corporate;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CorporateJdbcRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<CorporateEntity> findAll() {
        String sql = "SELECT * FROM CORPORATES";
        return jdbcTemplate.query(sql, CorporateEntity.getRowMapper());
    }
}
