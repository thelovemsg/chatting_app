package com.example.socket_jpa_querydsl_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "hashtags")
@Getter
@Setter
public class Hashtag extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    private ChattingRoom chattingRoom;

    @Column(name = "sequence")
    private Integer seq;


}
