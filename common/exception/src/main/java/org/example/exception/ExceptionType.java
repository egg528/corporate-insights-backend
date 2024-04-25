package org.example.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionType {
    DEFAULT(500, "DEFAULT", "예기치 못한 오류가 발생했습니다.");
    private final int httpStatus;
    private final String errorCode;
    private final String message;
}
