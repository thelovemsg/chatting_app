package com.example.socket_jpa_querydsl_test.domain.entity.member;

import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.domain.customenum.PositionType;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.ChattingRoom;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
@Entity
@ToString
@AttributeOverride(name = "id", column = @Column(name = "member_chatting_room_id"))
public class MemberChattingRoom extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @Column(name = "withdrawal_status")
    private FlagStatus withdrawalStatus = FlagStatus.NO;

    @Column(name = "room_closed")
    private FlagStatus isRoomClosed = FlagStatus.NO;

    @Column(name = "is_expired")
    private FlagStatus isExpired = FlagStatus.NO;

    @Column(name = "description")
    private String description;

    @Enumerated(STRING)
    @Column(name = "position")
    private PositionType position = PositionType.MEMBER;

    public void withdrawMember() {
        this.withdrawalStatus = FlagStatus.YES;
    }

    public void expireRoom() {
        this.isExpired = FlagStatus.YES;
    }

    public void closeRoom() {
        this.isRoomClosed = FlagStatus.YES;
    }

    public static MemberChattingRoom joinMemberToChattingRoom(Member member, ChattingRoom chattingRoom) {
        MemberChattingRoom memberChattingRoom = new MemberChattingRoom();
        memberChattingRoom.setMember(member);
        memberChattingRoom.setChattingRoom(chattingRoom);
        return memberChattingRoom;
    }

    public void changePosition(PositionType position) {
        this.position = position;
    }

}
