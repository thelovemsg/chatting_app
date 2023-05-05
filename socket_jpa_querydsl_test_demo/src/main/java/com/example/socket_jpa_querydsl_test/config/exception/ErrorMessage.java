package com.example.socket_jpa_querydsl_test.config.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ErrorMessage implements Serializable {

    @JsonProperty
    private String message;

    @JsonProperty
    private String code;

    public ErrorMessage(String message, String code) {
        this.message = message;
        this.code = code;
    }

    // Getters and setters (optional)
}