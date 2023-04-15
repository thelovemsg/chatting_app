package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "chatting_room_id"))
public class BlockMember extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_target_id")
    private Member memberTarget;

    @CreatedDate
    private LocalDateTime blockDate;

}
