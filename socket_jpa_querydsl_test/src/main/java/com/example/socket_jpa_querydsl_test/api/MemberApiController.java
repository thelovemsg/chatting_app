package com.example.socket_jpa_querydsl_test.api;

import com.example.socket_jpa_querydsl_test.api.dto.CreateMemberResponse;
import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.dto.MemberDto;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/memberSave")
    public ResponseEntity<CreateMemberResponse> saveMember(@Valid @RequestBody MemberDto memberDto, BindingResult result){
        if(result.hasErrors()){
            throw new IllegalArgumentException(getErrorMessage(result));
        }

        Member member = Member.createMember(memberDto);
        member = memberService.saveMember(member);
        return customResponse(new CreateMemberResponse(member));
    }

}
