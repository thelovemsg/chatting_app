package com.example.socket_jpa_querydsl_test.domain.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER("USER"), MANAGER("MANAGER");

    private String authority;

    RoleEnum(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
