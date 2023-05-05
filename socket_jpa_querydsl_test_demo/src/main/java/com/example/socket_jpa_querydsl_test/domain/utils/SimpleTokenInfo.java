package com.example.socket_jpa_querydsl_test.domain.utils;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SimpleTokenInfo {
    private String grantType;
    private String accessToken;
}
