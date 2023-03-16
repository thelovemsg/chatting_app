package com.example.socket_jpa_querydsl_test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentDatas {
    @Value("${my.encryption-key}")
    private static String ENCRYPTION_KEY;
}
