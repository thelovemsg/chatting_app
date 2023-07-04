package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.customenum.ChattingRoomType;
import com.example.socket_jpa_querydsl_test.domain.entity.*;
import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        initService.initDb3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final MemberService memberService;
        private final ProfileService profileService;
        private final FriendService friendService;
        private final FavoriteService favoriteService;
        private final ChattingRoomService chattingRoomService;
        private final MemberChattingRoomService memberChattingRoomService;

        //set member and address data
        public void initDb1() {
            Member memberA = createMember("test1@naver.com", "testbot1", "samenicknam1","01011111111", "password1234");
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
            profileService.addProfile(profileA);

            Member memberB = memberService.getMemberByEmail("test2@naver.com");
            Profile profileB = Profile.builder()
                                .member(memberB)
                                .name("테스트 이름 2")
                                .statusDescription("테스트 이름 설명입니다. 2")
                                .build();
            profileService.addProfile(profileB);

            Profile profileC = Profile.builder()
                                .member(memberB)
                                .name("멀티 테스트 이름")
                                .profileType(MULTI)
                                .statusDescription("멀티 테스트 이름 설명입니다.").build();
            profileService.addProfile(profileC);

            Member memberC = memberService.getMemberByEmail("test3@naver.com");
            Profile profileD = Profile.builder()
                    .member(memberC)
                    .name("테스트 이름 3")
                    .statusDescription("테스트 이름 설명입니다 3").build();
            em.persist(profileD);

        }

        // set friend and chatting room data
        public void initDb3() {

            /**
             * 1. memberA와 memberB, memberC는 친구다.
             * 2. memberB는 memberA와 친구다.
             * 3. memberC는 memberB의 친구이다. memberC는 그를 친구로 등록하지 않았다.
             * 4. memberB는 memberA에게 멀티프로필을 사용하고 있다.
             * 5. memberA에게 memberC는 즐겨찾기에 추가되어있다.
             *
             * 6. memberA와 memberB는 이미 채팅을 했다.(같은 채팅방에 존재한다.)
             * 7. memberA, memberB 그리고 memberC는 현재 오픈 톡방에 있다. (PRIVATE 버전)
             *   => 만약 사용자가 오픈 채팅방에 들어갔다가 퇴장당했다면 다시는 들어올 수 없다.
             */

            Member memberA = memberService.getMemberByEmail("test1@naver.com");
            Member memberB = memberService.getMemberByEmail("test2@naver.com");
            Member memberC = memberService.getMemberByEmail("test3@naver.com");


            friendService.addFriend(memberA, memberB);
            friendService.addFriend(memberA, memberC);
            friendService.addFriend(memberB, memberA);
            friendService.addFriend(memberB, memberC);

            //link member B's multi profile to member A
            Profile multiProfile = profileService.getOneMultiProfile(memberB);
            profileService.linkProfileToFriend(friendService.getFriend(memberB, memberA), multiProfile);

//          List<Profile> profiles = profileService.getProfile(memberA.getId());

            // memberA에게 memberC는 즐겨찾기에 추가되어있다.
            favoriteService.addFavorites(friendService.getFriend(memberA, memberC));

            // individual chatting room is made and has member (memberA and memberB)
            /**
             * add member chatting room event listener
             * when user make chatting room, he doesn't need to know
             * what room we choose because it's the first time with him.
             */
            memberChattingRoomService.openNewIndividualChattingRoom(memberA, memberB);


            //
            // memberA, memberB 그리고 memberC는 현재 오픈 톡방에 있다. (PRIVATE 버전)
            List<Hashtag> hashtagsForOpenRoom = Arrays.asList(new Hashtag("test1", 1)
                    , new Hashtag("test2", 2)
                    , new Hashtag("test3", 3));
            memberChattingRoomService.openNewOpenChattingRoom(memberA, memberB, memberC);
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
