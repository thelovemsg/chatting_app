package com.example.socket_jpa_querydsl_test.repository.member;

import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.socket_jpa_querydsl_test.domain.entity.QMemberChattingRoom.memberChattingRoom;
import static com.example.socket_jpa_querydsl_test.domain.status.FlagStatus.NO;


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
        return queryFactory.selectFrom(memberChattingRoom)
                .where(memberChattingRoom.member.id.eq(id))
                .fetch().stream();
    }

    @Override
    public Stream<MemberChattingRoom> getMemberChattingRoomInValidStatus() {
        return queryFactory.selectFrom(memberChattingRoom)
                .where( memberChattingRoom.isExpired.eq(NO),
                        memberChattingRoom.isRoomClosed.eq(NO))
                .fetch().stream();
    }

    @Override
    public Stream<MemberChattingRoom> getMemberChattingRoomByChattingRoomId(Long id) {
        return queryFactory.selectFrom(memberChattingRoom)
                .where(memberChattingRoom.chattingRoom.id.eq(id))
                .fetch().stream();
    }

    @Override
    public int getMemberChattingRoomTotalCnt(Long chattingRoomId) {
        return queryFactory.selectFrom(memberChattingRoom)
                .where(memberChattingRoom.chattingRoom.id.eq(chattingRoomId)
                        , memberChattingRoom.withdrawalStatus.eq(NO)
                        , memberChattingRoom.isRoomClosed.eq(NO)
                        , memberChattingRoom.isExpired.eq(NO))
                            .fetch().stream().distinct().collect(Collectors.toList()).size();
    }

}
