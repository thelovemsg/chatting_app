package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.example.socket_jpa_querydsl_test.domain.profile.ProfileConn;
import com.example.socket_jpa_querydsl_test.domain.event.FriendAddEvent;
import com.example.socket_jpa_querydsl_test.repository.friend.ProfileConnRepository;
import com.example.socket_jpa_querydsl_test.repository.friend.ProfileRepository;
import com.example.socket_jpa_querydsl_test.repository.friend.ProfileRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileRepositoryImpl profileRepositoryImpl;
    private final ProfileConnRepository profileConnRepository;

    public List<Profile> getProfiles(Long memberId) {
        return profileRepository.getProfilesByMemberId(memberId);
    }

    public Profile addProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getOneProfileById(Profile profile) {
        return profileRepository.findById(profile.getId()).orElseThrow();
    }

    // test method for init db. member
    public Profile getOneMultiProfileTest(Member member) {
        return profileRepositoryImpl.getSpecificProfilesByMember(member, ProfileType.MULTI).get(0);
    }

    // test method for init db. member
    public Profile getOneMultiProfile(Long profileId) {
        return profileRepositoryImpl.getProfilesByProfileId(profileId).get(0);
    }

    public Profile getMainProfile(Member member) {
        return profileRepositoryImpl.getMainProfileByMember(member);
    }

    @EventListener
    public void handleFriendAddedEvent(FriendAddEvent event) {
        Profile acceptorProfile = profileRepositoryImpl.getMainProfileByMember(event.acceptor());
        linkProfileToFriend(event.friend(), acceptorProfile);
    }

    public void linkProfileToFriend(Friend targetFriend, Profile profile) {
        profileConnRepository.save(ProfileConn.builder()
                .profile(profile)
                .friend(targetFriend)
                .build());
    }
}
