package com.example.socket_jpa_querydsl_test.controller;

import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/memberCnt")
    public Long getMemberCnt() {
        return memberService.getMemberCount();
    }
    @GetMapping("/member")
    public Member getMember(@RequestParam String name, @RequestParam String password) {
        return memberService.getMember(name, password);
    }

    @GetMapping("/members")
    public List<Member> getMembers() {
        return memberService.getMembers();
    }

}
