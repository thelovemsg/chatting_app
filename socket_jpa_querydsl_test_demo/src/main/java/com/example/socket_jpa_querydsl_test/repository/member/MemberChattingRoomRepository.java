package com.example.socket_jpa_querydsl_test.repository.member;

import com.example.socket_jpa_querydsl_test.domain.entity.member.MemberChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberChattingRoomRepository extends JpaRepository<MemberChattingRoom, Long> {
}
