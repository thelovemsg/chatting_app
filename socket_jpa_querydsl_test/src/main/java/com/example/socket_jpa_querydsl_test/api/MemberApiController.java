package com.example.socket_jpa_querydsl_test.api;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberDto;
import com.example.socket_jpa_querydsl_test.api.dto.response.MemberResponseDto;
import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberSaveDto;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping(value = "/memberSave")
    public ResponseEntity<MemberResponseDto> saveMember(@Valid @RequestBody MemberSaveDto memberSaveDto, BindingResult result){
        if(result.hasErrors()){
            throw new IllegalArgumentException(getErrorMessage(result));
        }

        Member member = Member.createMember(memberSaveDto);
        member = memberService.saveMember(member);
        return customResponse(new MemberResponseDto(member));
    }

    @PostMapping(value = "/findMemberByTarget")
    public ResponseEntity<MemberResponseDto> findMemberByEmail(@RequestBody MemberDto memberDto){
        System.out.println(memberDto.toString());
        return customResponse(memberService.findExistMember(memberDto));
    }

}
