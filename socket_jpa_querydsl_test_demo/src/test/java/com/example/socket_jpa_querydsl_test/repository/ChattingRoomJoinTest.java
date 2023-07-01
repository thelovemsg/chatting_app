package com.example.socket_jpa_querydsl_test.repository;

import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepositoryImpl;
import com.example.socket_jpa_querydsl_test.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
class ChattingRoomJoinTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ChattingRoomRepository chattingRoomRepository;

    @Autowired
    private MemberChattingRoomRepositoryImpl memberChattingRoomRepositoryImpl;

    @Test
    @DisplayName("많은 회원을 하나의 채팅방에 가입시킴.")
    public void testOneToManyMemberChattingRoom() {
        /**
         * Situation 1
         * Member can attend many chatting room.
         **/
        // Create and save a new Member
        Member member = makeTempMember("thelovemsg@naver.com", "hello1", makeRandomPhoneNumber());

        // Set member properties
        member = memberRepository.save(member);

        // first chatting room.
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        ChattingRoom chattingRoom2 = new ChattingRoom();
        chattingRoom2 = chattingRoomRepository.save(chattingRoom2);

        // Join the member to the first chatting room
        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.joinMemberToChattingRoom(member, chattingRoom);
        memberChattingRoom1 = memberChattingRoomRepositoryImpl.save(memberChattingRoom1);

        // Join the member to the second chatting room
        MemberChattingRoom memberChattingRoom2 = MemberChattingRoom.joinMemberToChattingRoom(member, chattingRoom2);
        memberChattingRoom2 = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        //get all chatting room that one member included.
        List<MemberChattingRoom> chattingRooms = memberChattingRoomRepositoryImpl.getMemberChattingRoomByMemberId(member.getId()).collect(Collectors.toList());
        Assertions.assertThat(chattingRooms.size()).isEqualTo(2);

        for (MemberChattingRoom room : chattingRooms) {
            System.out.println(room.getChattingRoom());
        }

    }

    @Test
    @DisplayName("특정 채팅 룸에서 회원을 탈퇴시킴. 한 회원이 2개의 채팅룸에 들어가 있는 경우!")
    public void withdrawMemberFromChattingRoom() {
        // Create and save a new Member
        Member member = makeTempMember("thelovemsg@naver.com", "hello1", "01011112222");

        // Set member properties
        member = memberRepository.save(member);

        // first chatting room.
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        ChattingRoom chattingRoom2 = new ChattingRoom();
        chattingRoom2 = chattingRoomRepository.save(chattingRoom2);

        // Join the member to the first chatting room
        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.joinMemberToChattingRoom(member, chattingRoom);
        memberChattingRoom1 = memberChattingRoomRepositoryImpl.save(memberChattingRoom1);

        // Join the member to the second chatting room
        MemberChattingRoom memberChattingRoom2 = MemberChattingRoom.joinMemberToChattingRoom(member, chattingRoom2);
        memberChattingRoom2 = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        memberChattingRoom2.withdrawMember();
        MemberChattingRoom memberChattingRoom = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        System.out.println("memberChattingRoom = " + memberChattingRoom);

    }

    @Test
    @DisplayName("한 방에 회원이 2명 이상 가입한 상태에서 방폭(expire) 시키는 경우")
    public void expireChattingRoom() {

        Member member1 = makeTempMember("thelovemsg1@naver.com", "nickname111", makeRandomPhoneNumber());
        Member member2 = makeTempMember("thelovemsg2@naver.com", "nickname222", makeRandomPhoneNumber());

        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);

        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.joinMemberToChattingRoom(member1, chattingRoom);
        memberChattingRoom1 = memberChattingRoomRepositoryImpl.save(memberChattingRoom1);

        MemberChattingRoom memberChattingRoom2 = MemberChattingRoom.joinMemberToChattingRoom(member2, chattingRoom);
        memberChattingRoom2 = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        //get members of chatting room. It must be two!!!
        List<MemberChattingRoom> memberChattingRooms = memberChattingRoomRepositoryImpl
                                                            .getMemberChattingRoomByChattingRoomId(chattingRoom.getId())
                                                                .collect(Collectors.toList());
        Assertions.assertThat(memberChattingRooms.size()).isEqualTo(2);

        /**
         * Then, let's expire our chatting room!!!
         *
         * 1. Expire chatting room first.
         * 2. Save updated data.
         * 3. Check if the data is really updated
         */
        
        Assertions.assertThat(chattingRoom.isRoomActive()).isTrue();
        chattingRoom.expireRoom();
        chattingRoomRepository.save(chattingRoom);

        for (MemberChattingRoom memberChattingRoom : memberChattingRooms) {
            memberChattingRoom.expireRoom();
        }

        memberChattingRoomRepositoryImpl.saveAll(memberChattingRooms);

        List<MemberChattingRoom> updatedMember1List = memberChattingRoomRepositoryImpl.getMemberChattingRoomByMemberId(member1.getId()).collect(Collectors.toList());
        for (MemberChattingRoom memberChattingRoom : updatedMember1List) {
            Assertions.assertThat(memberChattingRoom.getIsExpired()).isEqualTo(FlagStatus.YES);
        }

    }

    @Test
    @DisplayName("한 방에 회원이 2명 이상 가입한 상태에서 방을 닫아버리는 경우")
    void closeChattingRoom() {
        Member member1 = makeTempMember("thelovemsg1@naver.com", "nickname111", makeRandomPhoneNumber());
        Member member2 = makeTempMember("thelovemsg2@naver.com", "nickname222", makeRandomPhoneNumber());

        member1 = memberRepository.save(member1);
        member2 = memberRepository.save(member2);

        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom = chattingRoomRepository.save(chattingRoom);

        MemberChattingRoom memberChattingRoom1 = MemberChattingRoom.joinMemberToChattingRoom(member1, chattingRoom);
        memberChattingRoom1 = memberChattingRoomRepositoryImpl.save(memberChattingRoom1);

        MemberChattingRoom memberChattingRoom2 = MemberChattingRoom.joinMemberToChattingRoom(member2, chattingRoom);
        memberChattingRoom2 = memberChattingRoomRepositoryImpl.save(memberChattingRoom2);

        //check if data is well saved.
//        ChattingRoom selectedChattingRoom = chattingRoomRepository.getChattingRoomById(chattingRoom.getId());
//        Assertions.assertThat(chattingRoom.getId()).isEqualTo(selectedChattingRoom.getId());

        //get members of chatting room. It must be two!!!
        List<MemberChattingRoom> memberChattingRooms = memberChattingRoomRepositoryImpl.getMemberChattingRoomByChattingRoomId(chattingRoom.getId()).collect(Collectors.toList());
        Assertions.assertThat(memberChattingRooms.size()).isEqualTo(2);

        Assertions.assertThat(chattingRoom.isRoomActive()).isTrue();
        chattingRoom.expireRoom();
        chattingRoomRepository.save(chattingRoom);

        for (MemberChattingRoom memberChattingRoom : memberChattingRooms) {
            memberChattingRoom.closeRoom();
        }

        memberChattingRoomRepositoryImpl.saveAll(memberChattingRooms);

        List<MemberChattingRoom> updatedMember1List = memberChattingRoomRepositoryImpl.getMemberChattingRoomByMemberId(member1.getId()).collect(Collectors.toList());
        for (MemberChattingRoom memberChattingRoom : updatedMember1List) {
            Assertions.assertThat(memberChattingRoom.getIsRoomClosed()).isEqualTo(FlagStatus.YES);
        }

        List<MemberChattingRoom> collect = memberChattingRoomRepositoryImpl.getMemberChattingRoomInValidStatus().collect(Collectors.toList());
        Assertions.assertThat(collect.size()).isEqualTo(0);

    }

    private static String makeRandomPhoneNumber() {
        long min = 1_000_000_000L; // Minimum 8-digit number
        long max = 9_999_999_999L; // Maximum 8-digit number

        long randomNumber = ThreadLocalRandom.current().nextLong(min, max + 1);
        return String.valueOf(randomNumber);
    }

    private static Member makeTempMember(String email, String nickname, String phoneNumber) {
        Member member = Member.builder()
                                .email(email)
                                .password("12341234")
                                .name("test")
                                .nickname(nickname)
                                .phoneNumber(phoneNumber)
                                .build();
        return member;
    }
}