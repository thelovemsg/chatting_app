package com.example.socket_jpa_querydsl_test.domain.entity.member;

import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "block_member_id"))
@Where(clause = "deleted = false")
public class BlockMember extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_target_id")
    private Member memberTarget;

    @CreatedDate
    @Column(name = "block_datetime")
    private LocalDateTime blockDatetime;

    @Column(name = "block_type")
    private FlagStatus blockType;

}
