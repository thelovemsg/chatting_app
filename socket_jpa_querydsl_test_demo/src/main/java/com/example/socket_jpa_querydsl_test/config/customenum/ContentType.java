package com.example.socket_jpa_querydsl_test.config.customenum;

public enum ContentType {
    JSON("application/json"),
    HTML("text/html"),
    TEXT("text/plain"),
    XML("application/xml");

    private final String contentType;

    ContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
