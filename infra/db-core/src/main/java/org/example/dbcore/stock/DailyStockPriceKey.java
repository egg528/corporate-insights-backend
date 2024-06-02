package org.example.dbcore.stock;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
@EqualsAndHashCode
@Embeddable
public class DailyStockPriceKey implements Serializable {

    @Column(name = "STOCK_CODE", nullable = false)
    private String stockCode;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;
}