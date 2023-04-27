package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.entity.Address;
import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom.*;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.initDb1();
        initService.initDb2();
//        initService.initDb3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void initDb1() {
            Member member = createMember("test3@naver.com", "testbot3", "samenicknam3","01011112223", "password1234");
            em.persist(member);

            Address address = createAddress("address1", "address2");
            address.setMember(member);
            em.persist(address);
        }

        public void initDb2() {
            Member member = createMember("test2@naver.com", "testbot2", "samenicknam2", "01022223333", "password1234");
            em.persist(member);

            Address address = createAddress("address11_1", "address22_1");
            address.setMember(member);
            em.persist(address);

            Address address1 = createAddress("address11_2", "address22_2");
            address1.setMember(member);
            em.persist(address1);

            Member member1 = createMember("test1@naver.com", "testbot1", "samenicknam1", "01022223311", "password1234");
            em.persist(member1);

            Address address2 = createAddress("address11_11", "address22_11");
            address.setMember(member1);
            em.persist(address2);

            Address address3 = createAddress("address11_22", "address22_22");
            address1.setMember(member1);
            em.persist(address3);

            ChattingRoom testRoom = createChattingRoom("test_room", "1234");
            em.persist(testRoom);

            var memberChattingRoom = joinMemberToChattingRoom(member, testRoom);
            em.persist(memberChattingRoom);

            var memberChattingRoom1 = joinMemberToChattingRoom(member1, testRoom);
            em.persist(memberChattingRoom1);

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
