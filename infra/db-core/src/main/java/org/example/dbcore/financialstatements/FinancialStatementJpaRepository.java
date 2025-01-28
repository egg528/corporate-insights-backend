package org.example.dbcore.financialstatements;

import org.example.domain.financialstatements.FinancialStatement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialStatementJpaRepository extends JpaRepository<FinancialStatement, Long> {
}
