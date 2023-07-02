package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.customenum.ChattingRoomType;
import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.Hashtag;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;

    public ChattingRoom addChattingRoom(List<Hashtag> hashtags, ChattingRoomType chattingRoomType) {
        ChattingRoom chattingRoom = new ChattingRoom();
        AtomicInteger sequence = new AtomicInteger(1);

        hashtags.forEach(hashtag -> {
            hashtag.setSeq(sequence.getAndIncrement());
            chattingRoom.addHashtag(hashtag);
        });

        return chattingRoomRepository.save(chattingRoom);
    }

}
