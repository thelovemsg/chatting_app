package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "hashtags")
@Getter
@Setter
@NoArgsConstructor
public class Hashtag extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "chatting_room_id")
    @JsonIgnore
    private ChattingRoom chattingRoom;

    @Column(name = "sequence")
    private Integer seq;

    public Hashtag(String content, ChattingRoom chattingRoom, Integer seq) {
        this.content = content;
        this.chattingRoom = chattingRoom;
        this.seq = seq;
    }

    public void setChattingRoom(com.example.socket_jpa_querydsl_test.domain.entity.ChattingRoom chattingRoom) {
    }
}
