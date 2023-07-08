package com.example.socket_jpa_querydsl_test.service;

import com.example.socket_jpa_querydsl_test.config.exception.ErrorMessage;
import com.example.socket_jpa_querydsl_test.domain.customenum.PositionType;
import com.example.socket_jpa_querydsl_test.domain.dto.MemberChattingRoomDto;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.ChattingRoom;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Hashtag;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.member.MemberChattingRoom;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepository;
import com.example.socket_jpa_querydsl_test.repository.member.MemberChattingRoomRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberChattingRoomService {

    private final ChattingRoomService chattingRoomService;
    private final BanishHistoryService banishHistoryService;
    private final MemberChattingRoomRepository memberChattingRoomRepository;
    private final MemberChattingRoomRepositoryImpl memberChattingRoomRepositoryImpl;

    public void makeNewIndividualChattingRoom(Member memberA, Member memberB) {
        ChattingRoom chattingRoom = chattingRoomService.addChattingRoomForIndividual();
        addMemberChattingRoomMember(chattingRoom, memberA, memberB);
    }

    public void makeNewPrivateChattingRoom(List<Hashtag> hashtags, String password, Member manager, Member... member) {
        ChattingRoom newRoom = chattingRoomService.addChattingRoomForPrivate(hashtags, password);

        MemberChattingRoom managerChattingRoom = MemberChattingRoom.joinMemberToChattingRoom(manager, newRoom);
        managerChattingRoom.changePosition(PositionType.MANAGER);
        addMemberChattingRoomMember(newRoom, member);

    }

    public void makeNewOpenChattingRoom(List<Hashtag> hashtags, Member manager, Member... members) {
        ChattingRoom newRoom = chattingRoomService.addChattingRoomForOpen(hashtags);

        MemberChattingRoom managerChattingRoom = MemberChattingRoom.joinMemberToChattingRoom(manager, newRoom);
        managerChattingRoom.changePosition(PositionType.MANAGER);
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

    public void banishMemberFromMemberChattingRoom(Long memberChattingRoomId, Long managerId, Long targetMemberId) {
        MemberChattingRoom resultMemberChattingRoom = memberChattingRoomRepositoryImpl.getMemberChattingRoomByIdAndMemberId(memberChattingRoomId, managerId);

        if(checkIfManager(memberChattingRoomId, managerId)){
            throw new IllegalArgumentException("NO_AUTH_FOR_BANISH",
                    new ErrorMessage("NO_AUTH_FOR_BANISH", "NO_AUTH_FOR_BANISH"));
        }

        MemberChattingRoom targetMemberChattingRoom = getBanishMemberChattingRoomTarget(resultMemberChattingRoom.getId(), targetMemberId);
        banishHistoryService.addBanishHistory(targetMemberChattingRoom);
    }

    public MemberChattingRoom getBanishMemberChattingRoomTarget(Long memberChattingRoomId, Long targetMemberId) {
        return memberChattingRoomRepositoryImpl.getMemberChattingRoomByIdAndMemberId(memberChattingRoomId, targetMemberId);
    }

    private boolean checkIfManager(Long memberChattingRoomId, Long managerId) {
        return memberChattingRoomRepositoryImpl.getMemberChattingRoomByIdAndMemberId(memberChattingRoomId, managerId)
                    .getPosition() == PositionType.MANAGER;
    }

}
