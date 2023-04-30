package com.example.socket_jpa_querydsl_test.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class EnvironmentData {
    @Value("${password.encryption_key}")
    public String ENCRYPTION_KEY;
}
