package com.example.socket_jpa_querydsl_test.domain.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import sun.security.x509.CertificateAlgorithmId;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {
    private static String encryption_key ="thelovemsg-secret-key";

    @Override
    public String convertToDatabaseColumn(String password) {
        return encode(password);
    }

    @Override
    public String convertToEntityAttribute(String encodedPassword) {
        return encodedPassword;
    }

    public static String encode(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            return String.format("%0128x", new BigInteger(1, md.digest()));
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
