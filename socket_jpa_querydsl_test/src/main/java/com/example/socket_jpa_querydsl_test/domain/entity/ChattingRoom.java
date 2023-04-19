package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "chatting_room_id"))
public class ChattingRoom extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "chatting_room_status")
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

    private String generateDefaultName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @PrePersist
    public void onPrePersist() {
        if (name == null) {
            name = generateDefaultName();
        }
    }

}
