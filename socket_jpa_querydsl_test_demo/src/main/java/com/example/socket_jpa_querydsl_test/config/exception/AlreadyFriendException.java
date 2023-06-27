package com.example.socket_jpa_querydsl_test.config.exception;

public class AlreadyFriendException extends RuntimeException{

    private Object data;

    public AlreadyFriendException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

}
