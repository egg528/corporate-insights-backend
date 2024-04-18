package org.example.domain.exception;


public class BusinessRuleException extends CorporateInsightsException{

    public BusinessRuleException(ExceptionType exceptionType, Object data) {
        super(exceptionType, data);
    }

    public BusinessRuleException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
