package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.profile.Favorites;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FavoriteRepositoryImpl extends SimpleJpaRepository<Favorites, Long> {

    private final JPAQueryFactory queryFactory;

    public FavoriteRepositoryImpl(EntityManager entityManager) {
        super(Favorites.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Favorites> getFavorites(Member member) {
//        return queryFactory.selectFrom(QFavorites.favorites);
        return null;
    }

}
