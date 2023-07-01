package com.example.socket_jpa_querydsl_test.config.jpa;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
    @Bean
    public CustomIdGenerator customIdGenerator() {
        return new CustomIdGenerator();
    }
}