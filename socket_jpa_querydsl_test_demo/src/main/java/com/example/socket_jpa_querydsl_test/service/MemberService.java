package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberDto;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.QMember;
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
    private static final String EMAIL_CHECK = "emailCheck";
    private static final String NICKNAME_CHECK = "nicknameCheck";
    private static final String PHONE_NUMBER_CHECK = "phoneNumberCheck";
    private static final String EMAIL = "email";
    private static final String NICKNAME = "email";
    private static final String PHONE_NUMBER = "email";

    public Member getMember(String name, String password){
        return memberRepository.getMemberByNameAndPassword(name, password);
    }

    public String findExistMember(MemberDto memberDto){
        String result = findDuplicateFieldName(memberDto.getValue(), null, null);

        if(result == null){
            return null;
        }

        switch(memberDto.getName()){
            case EMAIL_CHECK :
                if(result.equals(EMAIL))
                    return EMAIL_CHECK;
            case NICKNAME_CHECK:
                if(result.equals(NICKNAME));
                    return NICKNAME_CHECK;
            case PHONE_NUMBER_CHECK:
                if(result.equals(PHONE_NUMBER));
                    return PHONE_NUMBER_CHECK;
            default:
                throw new RuntimeException("there's no proper error. please check again");
        }
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
