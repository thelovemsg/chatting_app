package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.domain.customenum.PositionType;
import com.example.socket_jpa_querydsl_test.domain.dto.MemberChattingRoomDto;
import com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.Hashtag;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberChattingRoomService {

    private final ChattingRoomService chattingRoomService;
    private final MemberChattingRoomRepository memberChattingRoomRepository;
    private final MemberChattingRoomRepositoryImpl memberChattingRoomRepositoryImpl;

    public void makeNewIndividualChattingRoom(Member memberA, Member memberB) {
        ChattingRoom chattingRoom = chattingRoomService.addChattingRoomForIndividual();
        addMemberChattingRoomMember(chattingRoom, memberA, memberB);
    }

    public void makeNewPrivateChattingRoom(List<Hashtag> hashtags, Member manager, Member... member) {
        ChattingRoom newRoom = chattingRoomService.addChattingRoomForPrivate(hashtags);

        //add manager as member first
        MemberChattingRoom managerChattingRoom = MemberChattingRoom.joinMemberToChattingRoom(manager, newRoom);
        managerChattingRoom.changePosition(PositionType.MANAGER);

        //add the others as member
        addMemberChattingRoomMember(newRoom, member);
    }

    public void makeNewOpenChattingRoom(List<Hashtag> hashtags, Member manager, Member... members) {
        ChattingRoom newRoom = chattingRoomService.addChattingRoomForOpen(hashtags);

        //add manager as member first
        MemberChattingRoom managerChattingRoom = MemberChattingRoom.joinMemberToChattingRoom(manager, newRoom);
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
            memberChattingRoomRepository.save(MemberChattingRoom.joinMemberToChattingRoom(m, chattingRoom));
        });
    }

}
