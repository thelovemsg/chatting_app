package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import static com.example.socket_jpa_querydsl_test.domain.entity.QFriend.friend;


@Repository
public class FriendRepositoryImpl extends SimpleJpaRepository<Friend, Long>{

    private final JPAQueryFactory queryFactory;

    public FriendRepositoryImpl(EntityManager entityManager) {
        super(Friend.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Friend getFriendByfromMemberAndToMember(Member fromMember, Member toMember) {
        return queryFactory.selectFrom(friend)
                .where(friend.fromMember.eq(fromMember)
                        .and(friend.toMember.eq(toMember)))
                        .fetchFirst();
    }

}
