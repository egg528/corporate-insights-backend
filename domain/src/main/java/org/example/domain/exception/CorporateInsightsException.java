package org.example.domain.exception;

import lombok.Getter;

@Getter
public class CorporateInsightsException extends RuntimeException {
    private final ExceptionType exceptionType;
    private final Object data;

    public CorporateInsightsException(ExceptionType exceptionType, Object data) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.data = data;
    }

    public CorporateInsightsException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType = exceptionType;
        this.data = null;
    }
}
