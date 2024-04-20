package org.example.api.adapter.rest;

import org.example.api.support.rest.ApiResponse;
import org.example.exception.BusinessRuleException;
import org.example.exception.CorporateInsightsException;
import org.example.exception.ExceptionType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CorporateInsightsException.class)
    public ResponseEntity<ApiResponse<?>> handleCorporateInsightsException(CorporateInsightsException exception) {
        return buildErrorResponse(exception.getExceptionType());
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiResponse<?>> handleCorporateInsightsException(BusinessRuleException exception) {
        return buildErrorResponse(exception.getExceptionType());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception exception) {
        return buildErrorResponse(ExceptionType.DEFAULT);
    }


    private ResponseEntity<ApiResponse<?>> buildErrorResponse(ExceptionType exceptionType) {
        return ResponseEntity.status(HttpStatus.valueOf(exceptionType.getHttpStatus()))
                .body(ApiResponse.fail(exceptionType));
    }
}
