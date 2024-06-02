package org.example.dbcore.stock;

import jakarta.persistence.*;
import lombok.*;
import org.example.dbcore.common.BaseEntity;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@Table(name = "DAILY_STOCK_PRICES")
@ToString
public class DailyStockPriceEntity extends BaseEntity {

    @EmbeddedId
    private DailyStockPriceKey key;

    @Column(name = "CLOSING", nullable = false)
    private int closing;

    @Column(name = "VARIATION", nullable = false)
    private int variation;

    @Column(name = "OPENING", nullable = false)
    private int opening;

    @Column(name = "HIGH", nullable = false)
    private int high;

    @Column(name = "LOW", nullable = false)
    private int low;

    @Column(name = "VOLUME", nullable = false)
    private long volume;

    public static RowMapper<DailyStockPriceEntity> getRowMapper() {
        return ((rs, rowNum) ->
                DailyStockPriceEntity.builder()
                        .key(new DailyStockPriceKey(rs.getString("STOCK_CODE"), rs.getObject("DATE", LocalDate.class)))
                        .closing(rs.getInt("CLOSING"))
                        .variation(rs.getInt("VARIATION"))
                        .opening(rs.getInt("OPENING"))
                        .high(rs.getInt("HIGH"))
                        .low(rs.getInt("LOW"))
                        .volume(rs.getLong("VOLUME"))
                        .build());
    }
}
