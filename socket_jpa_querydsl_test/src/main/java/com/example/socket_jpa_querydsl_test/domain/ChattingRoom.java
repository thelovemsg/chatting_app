package com.example.socket_jpa_querydsl_test.domain;

import com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class ChattingRoom extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "chatting_room_id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    @Enumerated(STRING)
    private ChattingRoomStatus status;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<>();

    public void addHashtag(Hashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.setChattingRoom(this);
    }

    public void removeHashtag(Hashtag hashtag) {
        hashtags.remove(hashtag);
        hashtag.setChattingRoom(null);
    }

}
