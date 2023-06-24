package com.example.socket_jpa_querydsl_test.config.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDao {
    private String email;
    private String password;
}
