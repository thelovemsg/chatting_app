package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus;
import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverter;
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
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = "password")
@AttributeOverride(name = "id", column = @Column(name = "chatting_room_id"))
public class ChattingRoom extends BaseEntity {

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Column(name = "password")
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column(name = "chatting_room_status")
    @Enumerated(STRING)
    private ChattingRoomStatus isClosed = ChattingRoomStatus.OPEN;

    @OneToMany(mappedBy = "chattingRoom", cascade = CascadeType.ALL)
    private List<Hashtag> hashtags = new ArrayList<>();

    @Column(name = "room_expire_date")
    private LocalDateTime roomExpireDate;

    public void addHashtag(Hashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.setChattingRoom(this);
    }

    public void removeHashtag(Hashtag hashtag) {
        hashtags.remove(hashtag);
        hashtag.setChattingRoom(null);
    }

    @PrePersist
    public void onPrePersist() {
        if (name == null) {
            name = "CR_" + TsidCreator.getTsid().toLong();
        }
        if (password == null) {
            password = "OPEN"; // Se t the default password value here
        }
    }

    public boolean isRoomActive() {
        return isClosed == ChattingRoomStatus.OPEN && (roomExpireDate == null || LocalDateTime.now().isBefore(roomExpireDate));
    }

    public void changeStatus(ChattingRoomStatus isClosed) {
        this.isClosed = isClosed;
    }

    public void expireRoom() {
        this.roomExpireDate = LocalDateTime.now();
    }

    public static ChattingRoom createChattingRoom(String name, String password) {
        ChattingRoom chattingRoom = new ChattingRoom();
        chattingRoom.setName(name);
        chattingRoom.setPassword(password);
        return chattingRoom;
    }

}
