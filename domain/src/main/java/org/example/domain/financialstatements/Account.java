package org.example.domain.financialstatements;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class Account {
    private Long id;
    private Long financialStatementId;
    private String name;
    private BigDecimal amount;
}
