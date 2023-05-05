package com.example.socket_jpa_querydsl_test.config.exception;

public class JwtTokenUnsupportedException extends RuntimeException{
    public JwtTokenUnsupportedException() {
        super();
    }
    public JwtTokenUnsupportedException(String message) {
        super(message);
    }
    public JwtTokenUnsupportedException(String message, Throwable cause) {
        super(message, cause);
    }
    public JwtTokenUnsupportedException(Throwable cause) {
        super(cause);
    }
    protected JwtTokenUnsupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

