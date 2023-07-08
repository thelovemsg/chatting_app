package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.member.MemberRole;
import com.example.socket_jpa_querydsl_test.repository.member.MemberRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    public MemberRole addNewMemberRole(MemberRole memberRole) {
        return memberRoleRepository.save(memberRole);
    }

}
