package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.domain.QMember;
import com.example.socket_jpa_querydsl_test.repository.AddressRepository;
import com.example.socket_jpa_querydsl_test.repository.MemberRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        String duplicateFieldName = findDuplicateFieldName(member.getEmail(), member.getNickname(), member.getPhoneNumber());
        // 이메일 동일한거 안됌, 닉네임 동일한거 안됌
        if(duplicateFieldName != null)
            throw new IllegalArgumentException("이미 존재하는 회원입니다. 중복된 필드 : " + duplicateFieldName);
        return memberRepository.save(member);
    }

    public String findDuplicateFieldName(String email, String nickname, String phoneNumber) {
        QMember member = QMember.member;
        BooleanBuilder predicate = new BooleanBuilder();

        if(email != null && memberRepository.findOne(member.email.equalsIgnoreCase(email)).isPresent()){
            return "email";
        }

        if(nickname != null && memberRepository.findOne(member.nickname.equalsIgnoreCase(nickname)).isPresent()){
            return "nickname";
        }

        if(phoneNumber != null && memberRepository.findOne(member.phoneNumber.equalsIgnoreCase(phoneNumber)).isPresent()){
            return "phoneNumber";
        }

        return null;
    }

}
