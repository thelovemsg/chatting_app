package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.entity.member.BanishHistory;
import com.example.socket_jpa_querydsl_test.domain.entity.member.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.repository.chatting.BanishHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanishHistoryService {

    private final BanishHistoryRepository banishHistoryRepository;

    public void addBanishHistory(MemberChattingRoom memberChattingRoom) {
        banishHistoryRepository.save(new BanishHistory(memberChattingRoom));
    }

}
