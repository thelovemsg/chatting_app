package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
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
