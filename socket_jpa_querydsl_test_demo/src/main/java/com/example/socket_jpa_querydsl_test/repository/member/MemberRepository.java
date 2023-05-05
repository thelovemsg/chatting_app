package com.example.socket_jpa_querydsl_test.repository.member;

import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.repository.custom.MemberRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>, MemberRepositoryCustom , QuerydslPredicateExecutor<Member> {
    Member getMemberByNameAndPassword(String name, String password);
    Member getMemberByEmailAndPassword(String email, String password);
    Optional<Member> getMemberByEmail(String email);
    Long countMemberBy();
}
