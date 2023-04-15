package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class ChattingRoom extends BaseEntity {

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.socket_jpa_querydsl_test.config.CustomIdGenerator")
    @Column(name = "chatting_room_id")
    private String id;

    @Column(name = "name", columnDefinition = "default")
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
