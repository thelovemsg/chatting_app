package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@AttributeOverride(name = "id", column = @Column(name = "noti_hst_id"))
public class NotificationHistory extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(STRING)
    @Column(name = "noti_enabled")
    private FlagStatus notiEnabled = FlagStatus.YES;

}
