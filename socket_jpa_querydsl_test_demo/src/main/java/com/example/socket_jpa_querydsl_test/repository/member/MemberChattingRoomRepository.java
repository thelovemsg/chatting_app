package com.example.socket_jpa_querydsl_test.repository.member;

import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MemberChattingRoomRepository extends JpaRepository<MemberChattingRoom, Long> {
    Stream<MemberChattingRoom> getMemberChattingRoomByMemberId(Long id);
    Stream<MemberChattingRoom> getMemberChattingRoomInValidStatus();
    Stream<MemberChattingRoom> getMemberChattingRoomByChattingRoomId(Long id);
}
