package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.example.socket_jpa_querydsl_test.repository.friend.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public List<Profile> getProfiles(Long memberId) {
        return profileRepository.getProfilesByMemberId(memberId);
    }
}
