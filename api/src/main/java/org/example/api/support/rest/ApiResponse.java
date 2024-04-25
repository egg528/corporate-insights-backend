package org.example.api.support.rest;

import org.example.exception.ExceptionType;

public record ApiResponse<T> (ResultType resultType, T data, ErrorMessage errorMessage) {
    private record ErrorMessage(String code, String message, Object data) {
        public ErrorMessage(ExceptionType exceptionType) {
            this(exceptionType.getErrorCode(), exceptionType.getMessage(), null);
        }

        public ErrorMessage(ExceptionType exceptionType, Object data) {
            this(exceptionType.getErrorCode(), exceptionType.getMessage(), data);
        }
    }

    public static ApiResponse<?> success() {
        return new ApiResponse<>(ResultType.SUCCESS, null, null);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResultType.SUCCESS, data, null);
    }

    public static ApiResponse<?> fail(ExceptionType exceptionType) {
        return new ApiResponse<>(ResultType.FAIL, null, new ErrorMessage(exceptionType));
    }

    public static ApiResponse<?> fail(ExceptionType exceptionType, Object errorData) {
        return new ApiResponse<>(ResultType.FAIL, null, new ErrorMessage(exceptionType, errorData));
    }
}
