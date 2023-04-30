package com.example.socket_jpa_querydsl_test.api;

public enum ConditionEnum {
    SUCCESS("SUCCESS"), FAIL("FAIL");
    String condition;
    ConditionEnum(String condition) {
        this.condition = condition;
    }
}
