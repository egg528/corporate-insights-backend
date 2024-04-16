package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class Corporate {
    private String code;

    private String stockCode;

    private String name;

    private LocalDate lastModified;
}
