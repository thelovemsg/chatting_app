package com.example.socket_jpa_querydsl_test.controller;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberLoginDto;
import com.example.socket_jpa_querydsl_test.api.dto.response.MemberResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.customResponse;
import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.getErrorMessage;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@Valid @RequestBody MemberLoginDto memberLoginDtoo, BindingResult result) {
        if(result.hasErrors()){
            throw new IllegalArgumentException(getErrorMessage(result));
        }

        return customResponse("success");
    }

}
