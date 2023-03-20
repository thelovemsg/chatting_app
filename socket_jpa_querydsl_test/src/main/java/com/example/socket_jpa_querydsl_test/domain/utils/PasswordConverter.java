package com.example.socket_jpa_querydsl_test.domain.utils;

import com.example.socket_jpa_querydsl_test.config.EnvironmentData;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Converter
@Component
@RequiredArgsConstructor
public class PasswordConverter implements AttributeConverter<String, String> {

    //혹시 몰라 준비햇어... ♡
    private final EnvironmentData envData;

    @Override
    public String convertToDatabaseColumn(String password) {
        return encode(password);
    }

    @Override
    public String convertToEntityAttribute(String encodedPassword) {
        return encodedPassword;
    }

    public String encode(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
