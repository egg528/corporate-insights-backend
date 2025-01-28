package org.example.dbcore.corporate;


import org.springframework.data.repository.Repository;

import java.util.List;

public interface CorporateJpaRepository extends Repository<CorporateEntity, String> {
    void save(CorporateEntity entity);
    List<CorporateEntity> findAll();
}
