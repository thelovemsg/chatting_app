package com.example.socket_jpa_querydsl_test.api;

import com.example.socket_jpa_querydsl_test.api.dto.CreateMemberResponse;
import com.example.socket_jpa_querydsl_test.dto.MemberDto;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberApiController {

    @PostMapping("/memberSave")
    public CreateMemberResponse saveMember(@Valid @RequestBody MemberDto memberDto, BindingResult result){
        if(result.hasErrors()){
            throw new IllegalArgumentException("에러발견!");
        }
        System.out.println("test");
        return new CreateMemberResponse("test","test");
    }

}
