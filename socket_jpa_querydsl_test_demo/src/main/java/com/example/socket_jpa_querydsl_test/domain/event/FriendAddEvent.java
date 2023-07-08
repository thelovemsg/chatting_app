package com.example.socket_jpa_querydsl_test.domain.event;

import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;

public record FriendAddEvent(Friend friend, Member acceptor) {
}
