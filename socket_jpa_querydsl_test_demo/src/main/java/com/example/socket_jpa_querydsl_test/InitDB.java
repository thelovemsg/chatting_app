package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.config.PasswordEncoderConfig;
import com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType;
import com.example.socket_jpa_querydsl_test.domain.entity.*;
import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType.*;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;
    private final ChattingRoomRepository chattingRoomRepository;

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
        private final MemberService memberService;
        private final PasswordEncoderConfig passwordEncoderConfig;

        //set member and address data
        public void initDb1() {
            Member memberA = createMember("test1@naver.com", "testbot1", "samenicknam1","01011112222", "password1234");
            em.persist(memberA);

            Member memberB = createMember("test2@naver.com", "testbot2", "samenicknam2", "01011112222", "password1234");
            em.persist(memberB);

            Member memberC = createMember("test3@naver.com", "testbote", "samenicknam3","01011113333", "password1234");
            em.persist(memberC);

            em.persist(setMemberRole(memberA, null));
            em.persist(setMemberRole(memberB, RoleEnum.MANAGER));
            em.persist(setMemberRole(memberC, null));

            Address addressA = createAddress("address1", "address2");
            addressA.setMember(memberA);
            em.persist(addressA);

            Address addressB = createAddress("address11_1", "address22_1");
            addressB.setMember(memberB);
            em.persist(addressB);

            Address addressC = createAddress("address11_2", "address22_2");
            addressC.setMember(memberB);
            em.persist(addressC);

            Address addressD = createAddress("address33", "address334");
            addressD.setMember(memberC);
            em.persist(addressD);

        }

        // set profile data
        public void initDb2() {
            Member memberA = memberService.getMemberByEmail("test1@naver.com");

            Profile profileA = Profile.builder()
                                .member(memberA)
                                .name("테스트 이름 1")
                                .statusDescription("테스트 이름 설명입니다. 1")
                                .build();
            em.persist(profileA);

            Member memberB = memberService.getMemberByEmail("test2@naver.com");
            Profile profileB = Profile.builder()
                                .member(memberB)
                                .name("테스트 이름 2")
                                .statusDescription("테스트 이름 설명입니다. 2")
                                .build();
            em.persist(profileB);

            Profile profileC = Profile.builder()
                                .member(memberB)
                                .name("멀티 테스트 이름")
                                .profileType(MULTI)
                                .statusDescription("멀티 테스트 이름 설명입니다.").build();
            em.persist(profileC);
        }

        // set friend and chatting room dta
        public void initDb3() {

            /**
             * 1. memberA와 memberB, memberC는 친구다.
             * 2. memberB는 memberC에게 친구 신청을 보냈다.
             * 3. memberB는 memberA에게 멀티프로필을 사용하고 있다.
             * 4. memberA에게 memberC는 즐겨찾기에 추가되어있다.
             */

            Member memberA = memberService.getMemberByEmail("test1@naver.com");
            Member memberB = memberService.getMemberByEmail("test2@naver.com");
            Member memberC = memberService.getMemberByEmail("test2@naver.com");

        }


            private Address createAddress(String address1, String address2) {
            Address address = new Address();
            address.setAddress1(address1);
            address.setAddress2(address2);
            return address;
        }

        private Member createMember(String email, String name, String nickname, String phoneNumber, String password) {
            return Member.builder().email(email)
                    .name(name).nickname(nickname)
                    .phoneNumber(phoneNumber).password(password).build();
        }

        private ChattingRoom createChattingRoom(String name, String password){
            ChattingRoom chattingRoom = new ChattingRoom();
            chattingRoom.setName(name);
            chattingRoom.setPassword(password);
            return chattingRoom;
        }

        private MemberRole setMemberRole(Member member, RoleEnum roleEnum) {
            MemberRole memberRole = new MemberRole();
            memberRole.setMember(member);

            if(roleEnum != null)
                memberRole.setRoleEnum(roleEnum);

            ArrayList mRolesArrayListA = new ArrayList<>();
            mRolesArrayListA.add(memberRole);
            member.setMemberRoles(mRolesArrayListA);
            return memberRole;
        }

    }

}
