package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Corporate {
    private Long id;

    private String code;

    private String stockCode;

    private String name;
}
