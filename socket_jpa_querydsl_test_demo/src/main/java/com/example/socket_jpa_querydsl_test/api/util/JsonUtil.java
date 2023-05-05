package com.example.socket_jpa_querydsl_test.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert object to JSON string", e);
        }
    }
}
