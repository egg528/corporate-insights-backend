package org.example.dbcore.financialstatements;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ACCOUNTS")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FINANCIAL_STATEMENT_ID", nullable = false)
    private Long financialStatementId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    public static RowMapper<AccountEntity> getRowMapper() {
        return (ResultSet rs, int rowNum) -> AccountEntity.builder()
                .id(rs.getLong("ID"))
                .financialStatementId(rs.getLong("FINANCIAL_STATEMENT_ID"))
                .name(rs.getString("NAME"))
                .amount(rs.getBigDecimal("AMOUNT"))
                .build();
    }
}

