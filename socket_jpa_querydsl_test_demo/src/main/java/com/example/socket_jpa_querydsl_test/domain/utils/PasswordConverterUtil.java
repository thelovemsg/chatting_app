package com.example.socket_jpa_querydsl_test.domain.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Converter
@Component
@RequiredArgsConstructor
public class PasswordConverterUtil implements AttributeConverter<String, String> {

    private final ApplicationContext applicationContext;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        BCryptPasswordEncoder passwordEncoder = applicationContext.getBean(BCryptPasswordEncoder.class);
        return passwordEncoder.encode(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }

}
