package com.example.socket_jpa_querydsl_test.config.exception.token;

public class JwtTokenEmptyClaimsException extends RuntimeException{
    public JwtTokenEmptyClaimsException() {
        super();
    }
    public JwtTokenEmptyClaimsException(String message) {
        super(message);
    }
    public JwtTokenEmptyClaimsException(String message, Throwable cause) {
        super(message, cause);
    }
    public JwtTokenEmptyClaimsException(Throwable cause) {
        super(cause);
    }
    protected JwtTokenEmptyClaimsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

