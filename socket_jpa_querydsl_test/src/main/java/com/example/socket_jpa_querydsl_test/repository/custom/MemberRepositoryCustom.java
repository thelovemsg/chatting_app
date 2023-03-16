package com.example.socket_jpa_querydsl_test.repository.custom;

import com.example.socket_jpa_querydsl_test.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findAllMemberList();
}
