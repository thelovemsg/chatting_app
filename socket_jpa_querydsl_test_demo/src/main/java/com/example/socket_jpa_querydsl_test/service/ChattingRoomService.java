package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.entity.chatting.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Hashtag;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional
public class ChattingRoomService {

    private final HashtagService hashtagService;
    private final ChattingRoomRepository chattingRoomRepository;

    public ChattingRoom addChattingRoomForIndividual() {
        ChattingRoom chattingRoom = new ChattingRoom();
        return chattingRoomRepository.save(chattingRoom);
    }

    public ChattingRoom addChattingRoomForOpen(List<Hashtag> hashtags) {
        ChattingRoom chattingRoom = ChattingRoom.makeOpenChattingRoom();
        AtomicInteger sequence = new AtomicInteger(1);

        hashtags.forEach(hashtag -> {
            hashtag.setSeq(sequence.getAndIncrement());
            hashtagService.addHashtag(hashtag);
            chattingRoom.addHashtag(hashtag);
        });

        return chattingRoomRepository.save(chattingRoom);
    }

    public ChattingRoom addChattingRoomForPrivate(List<Hashtag> hashtags) {
        ChattingRoom chattingRoom = ChattingRoom.makePrivateChattingRoom("test");
        AtomicInteger sequence = new AtomicInteger(1);

        hashtags.forEach(hashtag -> {
            hashtag.setSeq(sequence.getAndIncrement());
            hashtagService.addHashtag(hashtag);
            chattingRoom.addHashtag(hashtag);
        });

        return chattingRoomRepository.save(chattingRoom);
    }

    public ChattingRoom expireChattingRoom(Long memberId, Long expireMemberChattingRoomId) {
        /**
         * 채팅룸을 폐쇄치키려면 폐쇄하려는 직원의 직위(position)이 Manager 이어야 함.
         * 체크 후 폐쇄함.
         */

        // TODO: do something...

        return null;
    }


}
