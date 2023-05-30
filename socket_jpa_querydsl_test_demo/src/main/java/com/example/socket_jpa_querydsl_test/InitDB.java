package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.entity.*;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;
    private final ChattingRoomRepository chattingRoomRepository;

    @PostConstruct
    public void init() {
        initService.initDb1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void initDb1() {

            Member memberA = createMember("test1@naver.com", "testbot1", "samenicknam1","01011112222", "password1234");
            em.persist(memberA);

            Member memberB = createMember("test2@naver.com", "testbot2", "samenicknam2", "01022223333", "password1234");
            em.persist(memberB);

            MemberRole memberRoleA = new MemberRole();
            memberRoleA.setMember(memberA);
            memberA.getMemberRoles().add(memberRoleA);
            em.persist(memberRoleA);

            MemberRole memberRoleB = new MemberRole();
            memberRoleB.setRoleEnum(RoleEnum.MANAGER);
            memberRoleB.setMember(memberB);
            memberB.getMemberRoles().add(memberRoleB);
            em.persist(memberRoleB);

            Address addressA = createAddress("address1", "address2");
            addressA.setMember(memberA);
            em.persist(addressA);

            Address addressB = createAddress("address11_1", "address22_1");
            addressA.setMember(memberB);
            em.persist(addressB);

            Address addressC = createAddress("address11_2", "address22_2");
            addressC.setMember(memberB);
            em.persist(addressC);

            ChattingRoom testRoom = createChattingRoom("test_room", "1234");
            em.persist(testRoom);

            MemberChattingRoom memberChattingRoomA = MemberChattingRoom.joinMemberToChattingRoom(memberA, testRoom);
            em.persist(memberChattingRoomA);

            MemberChattingRoom memberChattingRoomB = MemberChattingRoom.joinMemberToChattingRoom(memberB, testRoom);
            em.persist(memberChattingRoomB);
        }

        private Address createAddress(String address1, String address2) {
            Address address = new Address();
            address.setAddress1(address1);
            address.setAddress2(address2);
            return address;
        }

        private Member createMember(String email, String name, String nickname, String phoneNumber, String password) {
            Member member = new Member();
            member.setEmail(email);
            member.setName(name);
            member.setNickname(nickname);
            member.setPhoneNumber(phoneNumber);
            member.setPassword(password);
            return member;
        }

        private ChattingRoom createChattingRoom(String name, String password){
            ChattingRoom chattingRoom = new ChattingRoom();
            chattingRoom.setName(name);
            chattingRoom.setPassword(password);
            return chattingRoom;
        }

    }

}
