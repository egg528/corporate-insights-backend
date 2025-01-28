package org.example.domain.financialstatements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Year;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FinancialStatement {
    private Long id;
    private String stockCode;
    private Year year;
    private Quarter quarter;
    private FinancialStatementType type;
    private FinancialStatementScope scope;
    private List<Account> accounts;
}
