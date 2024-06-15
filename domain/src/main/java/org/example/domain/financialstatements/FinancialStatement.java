package org.example.domain.financialstatements;

import java.time.Year;
import java.util.List;

public class FinancialStatement {
    private Long id;
    private String stockCode;
    private Year year;
    private Quarter quarter;
    private FinancialStatementType type;
    private FinancialStatementScope scope;
    private List<Account> accounts;
}
