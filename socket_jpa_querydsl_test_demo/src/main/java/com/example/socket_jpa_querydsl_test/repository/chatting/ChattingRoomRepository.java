package com.example.socket_jpa_querydsl_test.repository.chatting;

import com.example.socket_jpa_querydsl_test.domain.entity.chatting.ChattingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChattingRoomRepository extends JpaRepository<ChattingRoom, Long> {
    ChattingRoom getChattingRoomById(Long id);
    List<ChattingRoom> getChattingRoomsByHashtags(List<String> hashtagNames);
}
