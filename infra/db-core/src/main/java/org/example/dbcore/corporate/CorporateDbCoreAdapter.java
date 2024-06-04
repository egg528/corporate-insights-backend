package org.example.dbcore.corporate;

import lombok.RequiredArgsConstructor;
import org.example.domain.corporate.Corporate;
import org.example.domain.corporate.CorporateQueryPort;
import org.example.domain.corporate.CorporateCommandPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CorporateDbCoreAdapter implements CorporateCommandPort, CorporateQueryPort {
    private final CorporateJpaRepository jpaRepository;

    @Override
    public void save(Corporate domain) {
        jpaRepository.save(CorporateDbCoreMapper.toEntity(domain));
    }

    @Override
    public Map<String, Corporate> findAll() {
        return jpaRepository.findAll()
                .stream()
                .collect(Collectors.toMap(CorporateEntity::getCode, CorporateDbCoreMapper::toDomain));
    }
}
