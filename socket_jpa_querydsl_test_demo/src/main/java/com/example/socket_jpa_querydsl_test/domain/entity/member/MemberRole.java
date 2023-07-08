package com.example.socket_jpa_querydsl_test.domain.entity.member;

import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "member_role_id"))
public class MemberRole extends BaseEntity {

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum = RoleEnum.USER;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
