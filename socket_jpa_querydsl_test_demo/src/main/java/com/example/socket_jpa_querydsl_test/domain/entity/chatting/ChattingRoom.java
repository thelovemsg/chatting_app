package com.example.socket_jpa_querydsl_test.domain.entity.chatting;

import com.example.socket_jpa_querydsl_test.domain.customenum.ChattingRoomType;
import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverterUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.f4b6a3.tsid.TsidCreator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
@AttributeOverride(name = "id", column = @Column(name = "chatting_room_id"))
public class ChattingRoom extends BaseEntity {

    @Column(name = "name")
    private String name = "CR_" + TsidCreator.getTsid().toLong();

    @JsonIgnore
    @Column(name = "password")
    @Convert(converter = PasswordConverterUtil.class)
    private String password = "OPEN";

    @Column(name = "chatting_room_type")
    @Enumerated(STRING)
    private ChattingRoomType chattingRoomType = ChattingRoomType.INDIVIDUAL;

    @Column(name = "chatting_room_status")
    @Enumerated(STRING)
    private FlagStatus isClosed = FlagStatus.NO;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<>();

    @Column(name = "room_expiration_date")
    private LocalDateTime roomExpirationDate;

    public void addHashtag(Hashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.setChattingRoom(this);
    }

    public void removeHashtag(Hashtag hashtag) {
        hashtags.remove(hashtag);
        hashtag.setChattingRoom(null);
    }

    public boolean isRoomActive() {
        return isClosed == FlagStatus.NO && (roomExpirationDate == null);
    }

    public void closeChattingRoom() {
        this.isClosed = FlagStatus.YES;
    }

    public void expireRoom() {
        this.roomExpirationDate = LocalDateTime.now();
    }

    public static ChattingRoom makeOpenChattingRoom() {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setChattingRoomType(ChattingRoomType.OPEN);
        return chattingRoom;
    }

    public static ChattingRoom makePrivateChattingRoom(String password) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setChattingRoomType(ChattingRoomType.PRIVATE);
        chattingRoom.setPassword(password);
        return chattingRoom;
    }

}
