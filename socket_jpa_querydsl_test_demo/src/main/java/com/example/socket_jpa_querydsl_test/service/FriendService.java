package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.config.exception.ErrorMessage;
import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.event.FriendAddEvent;
import com.example.socket_jpa_querydsl_test.repository.friend.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final FriendRepositoryImpl friendRepositoryImpl;
    private final ApplicationEventPublisher eventPublisher;

    public void addFriend(Member proposer, Member acceptor) {

        /**
         * 1. 먼저 친구인지 확인한다.
         * 2. 친구라면 이미 친구라는 에러 반환한다.
         * 3. 친구로 등록한다.
         * 4. 서로 기본 프로젝트 표출로 엮어준다. (중요)
         *    (어떤 프로필을 보여줄지는 알아서 정한다.)
         */

        if (getFriend(proposer, acceptor) != null) {
            throw new IllegalArgumentException("Already friends with this member",
                 new ErrorMessage("Already friends with this member", "FRIENDS_ALREADY"));
        }

        Friend friendProposer = friendRepository
                                    .save(Friend.builder()
                                            .fromMember(proposer)
                                            .toMember(acceptor)
                                                .build());

        eventPublisher.publishEvent(new FriendAddEvent(friendProposer, acceptor));

    }

    public Friend getFriend(Member processor, Member acceptor) {
        Friend friend = friendRepositoryImpl.
                getFriendByfromMemberAndToMember(processor, acceptor);

        if(friend == null)
            throw new IllegalArgumentException("NO_FRIEND",
                    new ErrorMessage("NO_FRIEND", "NO_FRIEND"));

        return friend;
    }

}
