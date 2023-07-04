package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.customenum.PositionType;
import com.example.socket_jpa_querydsl_test.domain.dto.MemberChattingRoomDto;
import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MemberChattingRoomService {

    private final MemberChattingRoomRepository memberChattingRoomRepository;
    private final MemberChattingRoomRepositoryImpl memberChattingRoomRepositoryImpl;

    public void openNewIndividualChattingRoom(Member memberA, Member memberB) {
        addMemberChattingRoomMember(new ChattingRoom(), memberA, memberB);
    }

    public void openNewOpenChattingRoom(Member manager, Member... members) {
        ChattingRoom newRoom = ChattingRoom.makeOpenChattingRoom();

        //add manager as member first
        MemberChattingRoom managerChattingRoom = MemberChattingRoom.joinMemberToChattingRoom(newRoom, manager);
        managerChattingRoom.changePosition(PositionType.MANAGER);

        //add the others as member
        addMemberChattingRoomMember(newRoom, members);
    }

    public MemberChattingRoomDto getMemberChattingRoomInfo(Long id) {
        MemberChattingRoom MemberChattingRoom = memberChattingRoomRepositoryImpl.getMemberChattingRoomById(id);
        return null;
    }

    public List<MemberChattingRoom> getMemberChattingRoomInfoListByMember(Member member) {
        var memberChattingRoomStream = memberChattingRoomRepositoryImpl.getMemberChattingRoomByMemberId(member.getId());
        //TODO : Convert MemberChattingRoom to MemberChattingRoomDto
//        List<MemberChattingRoomDto> collect = memberChattingRoomStream.map(mcr -> new MemberChattingRoomDto()).collect(Collectors.toList());
        return memberChattingRoomStream.collect(Collectors.toList());
    }

    public void addMemberChattingRoomMember(ChattingRoom chattingRoom, Member... member) {
        Arrays.stream(member).forEach(m -> {
            memberChattingRoomRepository.save(MemberChattingRoom.joinMemberToChattingRoom(chattingRoom, m));
        });
    }

}
