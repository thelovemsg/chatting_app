package com.example.socket_jpa_querydsl_test.domain.event;

import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import lombok.Getter;

public record FriendAddEvent(Friend friend, Member acceptor) {
}
