package com.example.socket_jpa_querydsl_test.domain.entity.security;

import com.example.socket_jpa_querydsl_test.domain.entity.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refresh_tokens")
@Getter
@AttributeOverride(name = "id", column = @Column(name = "refresh_token_id"))
public class RefreshToken extends BaseEntity {

    @Column(nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    private LocalDateTime expiryDate;
}