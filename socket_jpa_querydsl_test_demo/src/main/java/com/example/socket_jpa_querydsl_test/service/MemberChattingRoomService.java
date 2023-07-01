package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberChattingRoomService {

    private final MemberChattingRoomRepository memberChattingRoomRepository;

}
