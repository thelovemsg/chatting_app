package com.example.socket_jpa_querydsl_test.config.exception;

public class JwtTokenInvalidException extends RuntimeException{
    public JwtTokenInvalidException() {
        super();
    }
    public JwtTokenInvalidException(String message) {
        super(message);
    }
    public JwtTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
    public JwtTokenInvalidException(Throwable cause) {
        super(cause);
    }
    protected JwtTokenInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

