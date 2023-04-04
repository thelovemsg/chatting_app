package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, String> {
}
