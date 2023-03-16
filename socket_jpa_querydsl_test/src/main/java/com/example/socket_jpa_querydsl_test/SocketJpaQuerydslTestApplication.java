package com.example.socket_jpa_querydsl_test;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {"com.example.socket_jpa_querydsl_test"})
@EnableJpaAuditing
public class SocketJpaQuerydslTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketJpaQuerydslTestApplication.class, args);
    }

}
