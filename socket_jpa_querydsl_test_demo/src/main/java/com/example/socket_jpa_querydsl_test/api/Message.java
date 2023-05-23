package com.example.socket_jpa_querydsl_test.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public static final String LOGOUT_SUCCESS = "Logout successful!";
    public static final String LOGOUT_FAILURE = "Logout failure!";


}
