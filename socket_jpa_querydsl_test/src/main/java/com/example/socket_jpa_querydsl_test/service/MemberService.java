package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.Address;
import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.repository.AddressRepository;
import com.example.socket_jpa_querydsl_test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;

    public Member getMember(String name, String password){
        return memberRepository.getMemberByNameAndPassword(name, password);
    }

    public Long getMemberCount() {
        return memberRepository.countMemberBy();
    }

    public List<Member> getMembers() {
        return memberRepository.findAllMemberList();
    }

    public Member saveMember(Member member){
        // 이메일 동일한거 안됌, 닉네임 동일한거 안됌
        return memberRepository.save(member);
    }

}