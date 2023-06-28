package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.config.exception.AlreadyFriendException;
import com.example.socket_jpa_querydsl_test.config.exception.ErrorMessage;
import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.ProfileConn;
import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.example.socket_jpa_querydsl_test.repository.friend.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final FriendRepositoryImpl friendRepositoryImpl;
    private final ProfileConnRepository profileConnRepository;
    private final ProfileRepositoryImpl profileRepositoryImpl;

    public void addFriend(Member proposer, Member acceptor) {
        /**
         * 1. 먼저 친구인지 확인한다.
         * 2. 친구라면 이미 친구라는 에러 반환한다.
         * 3. 친구로 등록한다.
         * 4. 서로 기본 프로젝트 표출로 엮어준다.
         *    (어떤 프로필을 보여줄지는 알아서 정한다.)
         *
         * <주의>
         *   요청멤버 id, 받는 멤버 id가 있지만,
         *   바로 친구로 추가가 가능하며, 친구로 추가되어있지 않더라도 특정 사용자에게 메시지를 전송할 수 있다.
         * </주의>
         *
         */

        if (checkIfFriend(proposer, acceptor)) {
            throw new AlreadyFriendException("Already friends with this member",
                 new ErrorMessage("Already friends with this member", "FRIENDS_ALREADY"));
        }

        Friend friendProposer = friendRepository.saveFriend(
                                                    Friend.builder()
                                                        .fromMember(proposer)
                                                        .toMemberId(acceptor.getId())
                                                            .build());

        Friend friendAcceptor = friendRepository.saveFriend(
                                                    Friend.builder()
                                                        .fromMember(acceptor)
                                                        .toMemberId(proposer.getId())
                                                        .isAccepted(FlagStatus.HANG)
                                                            .build());

        Profile mainProfileA = profileRepositoryImpl.getMainProfileByMemberId(proposer);
        Profile mainProfileB = profileRepositoryImpl.getMainProfileByMemberId(acceptor);

        // Warning!
        // Each friend don't want to see their own profile!
        profileConnRepository.save(ProfileConn.builder()
                                                .profile(mainProfileA)
                                                .friend(friendAcceptor)
                                                    .build());

        profileConnRepository.save(ProfileConn.builder()
                                                .profile(mainProfileB)
                                                .friend(friendProposer)
                                                    .build());


    }

    private boolean checkIfFriend(Member processor, Member acceptor) {
        return friendRepositoryImpl.
                    getFriendByfromMemberAndToMemberId(processor, acceptor.getId()) != null ? true: false;
    }

}