package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.config.security.MemberRoleEnum;
import com.example.socket_jpa_querydsl_test.domain.entity.*;
import com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.initDb1();
        initService.initDb2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        public void initDb1() {

            Member member = createMember("test1@naver.com", "testbot1", "samenicknam1","01011112222", "password1234");
            em.persist(member);

            MemberRole memberRole = new MemberRole();
            memberRole.setMember(member);
            member.getMemberRoles().add(memberRole);
            em.persist(memberRole);

            Address address = createAddress("address1", "address2");
            address.setMember(member);
            em.persist(address);

        }

        public void initDb2() {

            Member member = createMember("test2@naver.com", "testbot2", "samenicknam2", "01022223333", "password1234");
            em.persist(member);

            MemberRole memberRole = new MemberRole();
            memberRole.setRoleEnum(RoleEnum.MANAGER);
            memberRole.setMember(member);
            member.getMemberRoles().add(memberRole);
            em.persist(memberRole);

            Address address = createAddress("address11_1", "address22_1");
            address.setMember(member);
            em.persist(address);

            Address address1 = createAddress("address11_2", "address22_2");
            address1.setMember(member);
            em.persist(address1);

            ChattingRoom testRoom = createChattingRoom("test_room", "1234");
            MemberChattingRoom memberChattingRoom = new MemberChattingRoom();

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