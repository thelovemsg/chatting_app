package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.QFriend;
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

    @Override
    public Friend getFriendByfromMemberIdAndToMemberId(Long fromMemberId, Long toMemberId) {
        return queryFactory.selectFrom(QFriend.friend)
                .where(QFriend.friend.fromMemberId
                    .eq(fromMemberId).and(QFriend.friend.toMemberId.eq(toMemberId)))
                        .fetchFirst();
    }
}
