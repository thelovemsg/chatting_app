package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    Friend getFriendByfromMemberIdAndToMemberId(Long fromMemberId, Long toMemberId);
}
