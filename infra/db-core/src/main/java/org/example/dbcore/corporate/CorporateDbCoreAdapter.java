package org.example.dbcore.corporate;

import lombok.RequiredArgsConstructor;
import org.example.domain.Corporate;
import org.example.domain.CorporateQueryPort;
import org.example.domain.CorporateCommandPort;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CorporateDbCoreAdapter implements CorporateCommandPort, CorporateQueryPort {
    private final CorporateJpaRepository jpaRepository;
    private final CorporateJdbcRepository jdbcRepository;

    @Override
    public void save(Corporate domain) {
        jpaRepository.save(CorporateDbCoreMapper.toEntity(domain));
    }

    @Override
    public Map<String, Corporate> findAll() {
        return jdbcRepository.findAll()
                .stream()
                .collect(Collectors.toMap(CorporateEntity::getCode, CorporateDbCoreMapper::toDomain));
    }
}
