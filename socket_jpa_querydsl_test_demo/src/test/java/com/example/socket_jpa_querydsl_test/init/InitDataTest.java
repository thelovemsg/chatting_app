package com.example.socket_jpa_querydsl_test.init;

import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.repository.custom.MemberRepositoryImpl;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class InitDataTest {

    @Autowired
    private MemberRepositoryImpl memberRepository;
    @Autowired
    private MemberChattingRoomRepositoryImpl memberChattingRoomRepository;

    @Test
    public void checkInitDBData() {
        // 1. 멤버A, B 검색
        Member findMemberA = memberRepository.findMemberByEmail("test1@naver.com");
        Member findMemberB = memberRepository.findMemberByEmail("test2@naver.com");

        // 2. 멤버A, B의 memberChattingRoom 정보 검색
        var memberChattingRoomsA = memberChattingRoomRepository
                                    .getMemberChattingRoomByMemberId(findMemberA.getId()).collect(Collectors.toList());
        var memberChattingRoomsB = memberChattingRoomRepository
                                    .getMemberChattingRoomByMemberId(findMemberB.getId()).collect(Collectors.toList());

        MemberChattingRoom memberChattingRoom = memberChattingRoomsA.get(0);
        Assertions.assertThat(memberChattingRoomsA.get(0).getChattingRoom().getId())
                .isEqualTo(memberChattingRoomsB.get(0).getChattingRoom().getId());

    }

}
