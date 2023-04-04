package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.entity.Hashtag;
import com.example.socket_jpa_querydsl_test.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ChattingRoomService {

    private final ChattingRoomRepository chattingRoomRepository;

    public ChattingRoom saveChattingRoomWithHashtags(ChattingRoom chattingRoom, List<String> hashtagContents) {
        AtomicInteger sequence = new AtomicInteger(1);

        hashtagContents.forEach(content -> {
            Hashtag hashtag = new Hashtag(content, chattingRoom, sequence.getAndIncrement());
            chattingRoom.addHashtag(hashtag);
        });
    }

}
