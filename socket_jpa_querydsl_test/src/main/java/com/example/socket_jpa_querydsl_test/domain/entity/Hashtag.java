package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "hashtag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "hashtag_id"))
public class Hashtag extends BaseEntity {

    @Column(name = "content")
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
