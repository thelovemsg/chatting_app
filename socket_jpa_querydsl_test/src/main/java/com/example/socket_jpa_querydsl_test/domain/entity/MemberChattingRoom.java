package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static jakarta.persistence.FetchType.LAZY;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@AttributeOverride(name = "id", column = @Column(name = "member_chatting_room_id"))
public class MemberChattingRoom extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    public static MemberChattingRoom createMemberChattingRoom(ChattingRoom chattingRoom, Member member) {
        MemberChattingRoom memberChattingRoom = new MemberChattingRoom();
        memberChattingRoom.setMember(member);
        memberChattingRoom.setChattingRoom(chattingRoom);
        return memberChattingRoom;
    }

    public static void withdrawMemberFromChattingRoom(MemberChattingRoom memberChattingRoom) {
        // Implement logic to withdraw a member from a chat room
        memberChattingRoom.setMember(null);
        memberChattingRoom.setChattingRoom(null);
    }


}
