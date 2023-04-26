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

    /**
     * When user get out of the chatting room,
     * the column "withdrawalStatus" should be updated as YES.
     */
    @Column(name = "withdrawal_status")
    private FlagStatus withdrawalStatus = FlagStatus.NO;

    /**
     * When the room is closed by owner,
     * the column "roomStatus" should be updated as YES.
     */
    @Column(name = "room_closed")
    private FlagStatus isRoomClosed = FlagStatus.NO;

    /**
     * When the room is closed by website manager for some reasons,
     * the column "isExpired" should be updated as YES.
     * (Or for some room that they don't use any more for too long time)
     */
    @Column(name = "is_expired")
    private FlagStatus isExpired = FlagStatus.NO;

    @Column(name = "description")
    private String description;

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


}
