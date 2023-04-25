package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.status.FlagStatus;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@AttributeOverride(name = "id", column = @Column(name = "member_chatting_room_id"))
public class MemberChattingRoom extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @Column(name = "withdrawal_status")
    private FlagStatus withdrawalStatus = FlagStatus.NO;

    @Column(name = "room_closed")
    private FlagStatus roomStatus = FlagStatus.NO;

    public static MemberChattingRoom createMemberChattingRoom(ChattingRoom chattingRoom, Member member) {
        MemberChattingRoom memberChattingRoom = new MemberChattingRoom();
        memberChattingRoom.setMember(member);
        memberChattingRoom.setChattingRoom(chattingRoom);
        return memberChattingRoom;
    }

    public void withdrawMember() {
        this.withdrawalStatus = FlagStatus.YES;
    }

    public void closeRoom() {
        this.roomStatus = FlagStatus.YES;
    }


}
