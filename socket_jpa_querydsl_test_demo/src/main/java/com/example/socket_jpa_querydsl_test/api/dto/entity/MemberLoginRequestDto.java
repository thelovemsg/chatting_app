package com.example.socket_jpa_querydsl_test.api.dto.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginRequestDto {
    @Email
    @NotBlank
    private String email;

    @Length(min = 8, message = " must be over 8 lengths")
    @NotBlank(message = " needed")
    private String password;
}
