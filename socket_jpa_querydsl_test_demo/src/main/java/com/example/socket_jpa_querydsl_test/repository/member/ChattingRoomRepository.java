package com.example.socket_jpa_querydsl_test.repository.member;

import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, String> {
    ChattingRoom getChattingRoomById(Long id);
}
