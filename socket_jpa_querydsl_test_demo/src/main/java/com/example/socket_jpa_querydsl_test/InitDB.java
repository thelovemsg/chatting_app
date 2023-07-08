package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.customenum.AddressType;
import com.example.socket_jpa_querydsl_test.domain.entity.*;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Hashtag;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Address;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.member.MemberRole;
import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.example.socket_jpa_querydsl_test.repository.chatting.ChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType.*;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.initDb1();
        initService.initDb2();
        initService.initDb3();
        initService.initDb4();
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
        private final AddressService addressService;
        private final MemberChattingRoomService memberChattingRoomService;
        private final MemberRoleService memberRoleService;

        //set member and address data
        public void initDb1() {

            Member memberA = Member.builder().email("test1@naver.com")
                                .name("testbot1").nickname("samenicknam1")
                                    .phoneNumber("01011111111").password("password1234").build();

            memberService.saveMember(memberA);

            Member memberB = Member.builder().email("test2@naver.com")
                    .name("testbot2").nickname("samenicknam2")
                    .phoneNumber("01022222222").password("password1234").build();

            memberService.saveMember(memberB);

            MemberRole memberRole = new MemberRole();
            memberRole.setRoleEnum(RoleEnum.MANAGER);
            memberRole.setMember(memberB);

            memberRoleService.addNewMemberRole(memberRole);

            Member memberC = Member.builder().email("test3@naver.com")
                    .name("testbot3").nickname("samenicknam3")
                    .phoneNumber("01033333333").password("password1234").build();

            memberService.saveMember(memberC);

            Address addressA = new Address("11111","address1", "address2", AddressType.PRIMARY);
            addressA.setMember(memberA);
            addressService.addAddress(addressA);

            Address addressB = new Address("22222", "address11_1", "address22_1", AddressType.PRIMARY);
            addressB.setMember(memberB);
            addressService.addAddress(addressB);

            Address addressC = new Address("33333","address11_2", "address22_2", AddressType.ETC);
            addressC.setMember(memberB);
            addressService.addAddress(addressC);

            Address addressD = new Address("44444","address33", "address334", AddressType.PRIMARY);
            addressD.setMember(memberC);
            addressService.addAddress(addressD);

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
            profileService.addProfile(profileD);

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
             * 6. memberA와 memberB는 채팅방을 개설했다.
             * 7. memberA, memberB 그리고 memberC는 현재 오픈 톡방에 있다. (PRIVATE 버전)
             *   => 히스토리를 전부 관리해야 하는데 이거는 어떻게 하지?
             */

            Member memberA = memberService.getMemberByEmail("test1@naver.com");
            Member memberB = memberService.getMemberByEmail("test2@naver.com");
            Member memberC = memberService.getMemberByEmail("test3@naver.com");

            friendService.addFriend(memberA, memberB);
            friendService.addFriend(memberA, memberC);
            friendService.addFriend(memberB, memberA);
            friendService.addFriend(memberB, memberC);

            //link member B's multi profile to member A
            Profile multiProfile = profileService.getOneMultiProfileTest(memberB);
            profileService.linkProfileToFriend(friendService.getFriend(memberB, memberA), multiProfile);

            // memberA에게 memberC는 즐겨찾기에 추가되어있다.
            favoriteService.addFavorites(friendService.getFriend(memberA, memberC));

            memberChattingRoomService.makeNewIndividualChattingRoom(memberA, memberB);

            // memberA, memberB 그리고 memberC는 현재 private한 오픈 톡방에 있다. (PRIVATE - 비밀번호가 있음)
            List<Hashtag> hashtags = Arrays.asList(new Hashtag("test1", 1)
                    , new Hashtag("test2", 2)
                    , new Hashtag("test3", 3));

            // 오픈 톡방 만들 때 memberChattingRoomService로 모든 로직을 모음.
            memberChattingRoomService.makeNewPrivateChattingRoom(hashtags, "password", memberA, memberB, memberC);

        }

        public void initDb4() {
            Member memberA = memberService.getMemberByEmail("test1@naver.com");
            Member memberB = memberService.getMemberByEmail("test2@naver.com");
            Member memberC = memberService.getMemberByEmail("test3@naver.com");

            List<Hashtag> hashtags = Arrays.asList(new Hashtag("test1", 1)
                    , new Hashtag("test2", 2)
                    , new Hashtag("test3", 3));

            // memberA와 memberB는 오픈 톡방이 있다. 여기에 추후에 memberC가 검색해서 들어오려고 한다.
            // 그런데 memberC는 이미 추방되었던 사람이라서 초대없이는 들어올 수 없다.
            // 여기서는 memberC를 해당 방의 추방 목록에 추가할 것임.
            // 이부분은 테스트코드로 작성할 것임.
            memberChattingRoomService.makeNewOpenChattingRoom(hashtags, memberA, memberB, memberC);

        }

    }

}
