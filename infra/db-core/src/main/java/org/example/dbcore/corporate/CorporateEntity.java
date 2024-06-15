package org.example.dbcore.corporate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dbcore.common.BaseEntity;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "CORPORATES")
public class CorporateEntity extends BaseEntity {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "STOCK_CODE", nullable = false)
    private String stockCode;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "LAST_MODIFIED", nullable = false)
    private LocalDate lastModified;

    public static RowMapper<CorporateEntity> getRowMapper() {
        return ((rs, rowNum) ->
                new CorporateEntity(
                        rs.getString("CODE"),
                        rs.getString("STOCK_CODE"),
                        rs.getString("NAME"),
                        rs.getObject("LAST_MODIFIED", LocalDate.class)
                ));
    }
}