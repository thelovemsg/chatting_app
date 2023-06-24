package com.example.socket_jpa_querydsl_test.domain.profile;

import com.example.socket_jpa_querydsl_test.domain.customenum.EnrollType;
import com.example.socket_jpa_querydsl_test.domain.entity.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

import static com.example.socket_jpa_querydsl_test.domain.customenum.EnrollType.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Getter
@Setter(PROTECTED)
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "favorite_id"))
public class Favorites extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Enumerated(STRING)
    @Column(name = "enroll_type")
    private EnrollType enrollType = Add;

    @Column(name = "enroll_time")
    private ZonedDateTime enrollTime = ZonedDateTime.now();

    public Favorites makeFriend(Friend friend) {
        Favorites favorites = new Favorites();
        favorites.setFriend(friend);
        return favorites;
    }

}
