package org.example.dbcore.financialstatements;

import org.example.domain.financialstatements.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {
}
