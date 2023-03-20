package com.example.socket_jpa_querydsl_test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @Email
    private String email;
    private String name;

    private String username;

    @Pattern(regexp = "\\d+", message = "must contain only digits")
    private String phoneNumber;
    @Length(min = 8, message = "must be over 8 lengths")
    private String password;

    private String address1;
    private String address2;

}
