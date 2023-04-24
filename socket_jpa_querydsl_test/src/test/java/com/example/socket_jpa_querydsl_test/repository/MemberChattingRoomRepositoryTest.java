package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class MemberChattingRoomRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

    @Autowired
    private MemberChattingRoomRepository memberChattingRoomRepository;

    @Test
    @Transactional
    public void testJoinAndWithdrawMemberFromChattingRoom() {
        // Create and save a new Member
        Member member = Member.builder()
                                .email("thelovemsg@naver.com")
                                .password("12341234")
                                .name("test")
                                .nickname("nickname")
                                .phoneNumber("15181895215")
                                .build();
        // Set member properties
        member = memberRepository.save(member);

        // Create and save a new ChattingRoom
        ChattingRoom chattingRoom = new ChattingRoom();
        // Set chattingRoom properties
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        // Join the member to the chat room
        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.createMemberChattingRoom(chattingRoom, member);
        memberChattingRoom1 = memberChattingRoomRepository.save(memberChattingRoom1);

        // Check if the member has joined the chat room
        Assertions.assertThat(memberChattingRoom1.getId()).isNotNull();

        // Withdraw the member from the chat room
        MemberChattingRoom.withdrawMemberFromChattingRoom(memberChattingRoom1);
        memberChattingRoomRepository.save(memberChattingRoom1);

        // Check if the member is removed from the chat room
        List<MemberChattingRoom> collect = memberChattingRoomRepository.getMemberChattingRoomByMemberId(memberChattingRoom1.getId()).collect(Collectors.toList());
        collect.forEach( result -> {
            Assertions.assertThat(result.isDeleted()).isTrue();
        });

    }
}