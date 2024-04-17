package org.example.dbcore.corporate;


import org.springframework.data.repository.Repository;

public interface CorporateJpaRepository extends Repository<CorporateEntity, Long> {
    void save(CorporateEntity entity);
}
