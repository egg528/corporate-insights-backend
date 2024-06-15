package org.example.domain.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class DailyStockPrice {
    private Long id;
    private String stockCode;
    private LocalDate date;
    private int closing;
    private int variation;
    private int opening;
    private int high;
    private int low;
    private long volume;
}
