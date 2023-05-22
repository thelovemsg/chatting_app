package com.example.socket_jpa_querydsl_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(exclude = { ThymeleafAutoConfiguration.class })
public class SocketJpaQuerydslTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocketJpaQuerydslTestApplication.class, args);
    }

}
