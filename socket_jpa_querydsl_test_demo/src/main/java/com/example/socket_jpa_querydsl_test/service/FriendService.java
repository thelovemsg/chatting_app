package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.config.exception.AlreadyFriendException;
import com.example.socket_jpa_querydsl_test.config.exception.ErrorMessage;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.repository.friend.FriendRepository;
import com.example.socket_jpa_querydsl_test.repository.friend.FriendRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepositoryImpl friendRepositoryImpl;

    public Long addFriend(Member proposer, Member acceptor) {
        /**
         * 1. 먼저 친구인지 확인한다.
         * 2. 친구라면 이미 친구라는 에러 반환한다.
         */

        if (checkIfFriend(proposer, acceptor)) {
            throw new AlreadyFriendException("Already friends with this member",
                        new ErrorMessage("Already friends with this member", "FRIENDS_ALREADY"));
        }

//        friendRepositoryImpl.addFriend(proposer, acceptor);

        return 0L;
    }

    private boolean checkIfFriend(Member processor, Member acceptor) {
        return friendRepositoryImpl.
                getFriendByfromMemberIdAndToMemberId(processor.getId(), acceptor.getId()) != null ? true: false;
    }

}
