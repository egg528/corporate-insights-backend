package org.example.dbcore.financialstatements;

import jakarta.persistence.*;
import lombok.*;
import org.example.domain.financialstatements.FinancialStatementScope;
import org.example.domain.financialstatements.FinancialStatementType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.time.Year;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "FINANCIAL_STATEMENTS")
public class FinancialStatementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STOCK_CODE", nullable = false)
    private String stockCode;

    @Column(name = "YEAR", nullable = false)
    private Year year;

    @Column(name = "QUARTER", nullable = false)
    private Integer quarter;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private FinancialStatementType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "SCOPE", nullable = false)
    private FinancialStatementScope scope;

    @OneToMany(mappedBy = "financialStatement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts;

    public static RowMapper<FinancialStatementEntity> getRowMapper() {
        return (ResultSet rs, int rowNum) -> FinancialStatementEntity.builder()
                .id(rs.getLong("ID"))
                .stockCode(rs.getString("STOCK_CODE"))
                .year(Year.of(rs.getInt("YEAR")))
                .quarter(rs.getInt("QUARTER"))
                .type(FinancialStatementType.valueOf(rs.getString("TYPE")))
                .scope(FinancialStatementScope.valueOf(rs.getString("SCOPE")))
                .build();
    }
}