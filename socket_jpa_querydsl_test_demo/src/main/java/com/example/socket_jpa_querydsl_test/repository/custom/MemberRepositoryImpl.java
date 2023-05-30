package com.example.socket_jpa_querydsl_test.repository.custom;

import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.socket_jpa_querydsl_test.domain.entity.QMember.member;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    final JPQLQueryFactory queryFactory;

    @Autowired
    public MemberRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Member> findAllMemberList() {
        return queryFactory.selectFrom(member).fetch();
    }

    @Override
    public Member findMemberByEmail(String email) {
        return queryFactory.selectFrom(member).where(member.email.eq(email)).fetchOne();
    }

}
