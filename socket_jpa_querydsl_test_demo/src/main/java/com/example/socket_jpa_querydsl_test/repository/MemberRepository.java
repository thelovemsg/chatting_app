package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom , QuerydslPredicateExecutor<Member> {
    Member getMemberByNameAndPassword(String name, String password);
    Member getMemberByEmailAndPassword(String email, String password);
    Member getMemberByEmail(String email);
    Long countMemberBy();
}
