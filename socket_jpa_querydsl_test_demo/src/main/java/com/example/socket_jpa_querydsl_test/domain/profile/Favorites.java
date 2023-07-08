package com.example.socket_jpa_querydsl_test.domain.profile;

import com.example.socket_jpa_querydsl_test.domain.customenum.EnrollType;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

import static com.example.socket_jpa_querydsl_test.domain.customenum.EnrollType.ADD;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Setter(PROTECTED)
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "favorite_id"))
public class Favorites extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Enumerated(STRING)
    @Column(name = "enroll_type")
    private EnrollType enrollType = ADD;

    @Column(name = "enroll_time")
    private ZonedDateTime enrollTime = ZonedDateTime.now();

    public static Favorites makeFavoriteFriend(Friend friend) {
        Favorites favorites = new Favorites();
        favorites.setFriend(friend);
        return favorites;
    }

}
