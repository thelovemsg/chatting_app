package com.example.socket_jpa_querydsl_test.api.dto.entity;

import com.example.socket_jpa_querydsl_test.domain.customenum.AddressType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AddressDto {
    private String address1;
    private String address2;
    private AddressType addressType;
}
