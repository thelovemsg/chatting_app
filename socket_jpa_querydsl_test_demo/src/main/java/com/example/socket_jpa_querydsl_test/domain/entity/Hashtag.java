package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.*;

@Entity
@Table(name = "hashtag")
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
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

    public Hashtag(String content, Integer seq) {
        this.content = content;
        this.seq = seq;
    }

}
