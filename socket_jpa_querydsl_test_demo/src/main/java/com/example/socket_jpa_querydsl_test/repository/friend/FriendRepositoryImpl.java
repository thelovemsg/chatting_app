package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FriendRepositoryImpl extends SimpleJpaRepository<Friend, Long> implements FriendRepository{

    private final JPAQueryFactory queryFactory;

    public FriendRepositoryImpl(EntityManager entityManager) {
        super(Friend.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

}
