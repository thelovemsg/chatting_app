package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.QMemberChattingRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository
public class MemberChattingRoomRepositoryImpl extends SimpleJpaRepository<MemberChattingRoom, Long> implements MemberChattingRoomRepository {

    private final JPAQueryFactory queryFactory;

    @Autowired
    public MemberChattingRoomRepositoryImpl(EntityManager entityManager) {
        super(MemberChattingRoom.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Stream<MemberChattingRoom> getMemberChattingRoomByMemberId(Long id) {
        QMemberChattingRoom memberChattingRoom = QMemberChattingRoom.memberChattingRoom;
        return queryFactory.selectFrom(memberChattingRoom)
                .where(memberChattingRoom.member.id.eq(id))
                .fetch().stream();
    }

}
