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
@Transactional
class MemberChattingRoomCustomRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

    @Autowired
    private MemberChattingRoomRepositoryImpl memberChattingRoomRepositoryImpl;

    @Test
    public void testOneToManyMemberChattingRoom() {
        /**
         * Situation 1
         * Member can attend many chatting room.
         **/
        // Create and save a new Member
        Member member = makeTempMember();

        // Set member properties
        member = memberRepository.save(member);

        // first chatting room.
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        ChattingRoom chattingRoom2 = new ChattingRoom();
        chattingRoom2 = chattingRoomRepository.save(chattingRoom2);

        // Join the member to the first chatting room
        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.createMemberChattingRoom(chattingRoom, member);
        memberChattingRoom1 = memberChattingRoomRepositoryImpl.save(memberChattingRoom1);

        // Join the member to the second chatting room
        MemberChattingRoom memberChattingRoom2 = MemberChattingRoom.createMemberChattingRoom(chattingRoom2, member);
        memberChattingRoom2 = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        //get all chatting room that one member included.
        List<MemberChattingRoom> chattingRooms = memberChattingRoomRepositoryImpl.getMemberChattingRoomByMemberId(member.getId()).collect(Collectors.toList());
        Assertions.assertThat(chattingRooms.size()).isEqualTo(2);

        for (MemberChattingRoom room : chattingRooms) {
            System.out.println(room.getChattingRoom());
        }

    }

    @Test
    @Transactional
    public void withdrawMemberFromChattingRoom1() {
        // Create and save a new Member
        Member member = makeTempMember();
        // Set member properties
        member = memberRepository.save(member);

        // first chatting room.
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        ChattingRoom chattingRoom2 = new ChattingRoom();
        chattingRoom2 = chattingRoomRepository.save(chattingRoom2);

        // Join the member to the first chatting room
        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.createMemberChattingRoom(chattingRoom, member);
        memberChattingRoom1 = memberChattingRoomRepositoryImpl.save(memberChattingRoom1);

        // Join the member to the second chatting room
        MemberChattingRoom memberChattingRoom2 = MemberChattingRoom.createMemberChattingRoom(chattingRoom2, member);
        memberChattingRoom2 = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        memberChattingRoom2.withdrawMember();
        MemberChattingRoom memberChattingRoom = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        System.out.println("memberChattingRoom = " + memberChattingRoom);

    }

    private static Member makeTempMember() {
        Member member = Member.builder()
                                .email("thelovemsg@naver.com")
                                .password("12341234")
                                .name("test")
                                .nickname("nickname")
                                .phoneNumber("15181895215")
                                .build();
        return member;
    }
}