package com.example.socket_jpa_querydsl_test.domain.entity.member;

import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.member.MemberChattingRoom;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "banish_history_id"))
public class BanishHistory extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_chatting_rood_id")
    private MemberChattingRoom memberChattingRoom;

    @Column(name = "banish_datetime")
    private LocalDateTime banishDatetime = LocalDateTime.now();

    public BanishHistory(MemberChattingRoom memberChattingRoom) {
        this.memberChattingRoom = memberChattingRoom;
    }
}
