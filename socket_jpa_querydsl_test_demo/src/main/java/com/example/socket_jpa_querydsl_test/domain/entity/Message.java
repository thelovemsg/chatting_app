package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
@AttributeOverride(name = "id", column = @Column(name = "message_id"))
public class Message extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "message")
    private String content;

    @Column(name = "send_time")
    private ZonedDateTime sendTime = ZonedDateTime.now();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "file_id")
    private File file; // The file, if any

}
