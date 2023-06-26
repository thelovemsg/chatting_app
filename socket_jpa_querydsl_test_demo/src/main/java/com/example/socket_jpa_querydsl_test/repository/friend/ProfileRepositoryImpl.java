package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepositoryImpl extends SimpleJpaRepository<Profile, Long>{

    private final JPAQueryFactory queryFactory;

    public ProfileRepositoryImpl(EntityManager entityManager) {
        super(Profile.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }



}
