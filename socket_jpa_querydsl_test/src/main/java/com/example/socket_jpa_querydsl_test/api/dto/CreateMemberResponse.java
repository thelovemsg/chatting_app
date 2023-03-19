package com.example.socket_jpa_querydsl_test.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberResponse {
    private String id;
    private String name;
}
