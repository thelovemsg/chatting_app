package com.example.socket_jpa_querydsl_test.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class TokenInfo {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}