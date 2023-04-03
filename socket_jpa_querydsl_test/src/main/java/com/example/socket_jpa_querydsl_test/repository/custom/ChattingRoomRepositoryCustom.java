package com.example.socket_jpa_querydsl_test.repository.custom;

import com.example.socket_jpa_querydsl_test.domain.ChattingRoom;

import java.util.List;

public interface ChattingRoomRepositoryCustom {
    List<ChattingRoom> findChattingRoomsByHashtags(List<String> content);
}
