package com.example.socket_jpa_querydsl_test.domain.profile;

import com.example.socket_jpa_querydsl_test.domain.entity.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.Friend;
import jakarta.persistence.*;

import static jakarta.persistence.FetchType.*;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "profile_permission_id"))
public class ProfilePermission extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

}
