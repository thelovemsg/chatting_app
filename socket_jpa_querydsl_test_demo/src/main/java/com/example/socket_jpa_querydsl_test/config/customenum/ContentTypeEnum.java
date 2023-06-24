package com.example.socket_jpa_querydsl_test.config.customenum;

public enum ContentTypeEnum {
    JSON("application/json"),
    HTML("text/html"),
    TEXT("text/plain"),
    XML("application/xml");


    private final String contentType;

    ContentTypeEnum(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }
}
