package com.example.socket_jpa_querydsl_test.repository.chatting;

import com.example.socket_jpa_querydsl_test.domain.entity.chatting.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.member.BanishHistory;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BanishHistoryRepositoryImpl extends SimpleJpaRepository<BanishHistory, Long> {
    private final JPQLQueryFactory queryFactory;

    @Autowired
    public BanishHistoryRepositoryImpl(EntityManager entityManager) {
        super(BanishHistory.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
