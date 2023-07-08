package com.example.socket_jpa_querydsl_test.domain.entity.security;

import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Getter
@AttributeOverride(name = "id", column = @Column(name = "refresh_token_id"))
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken extends BaseEntity {

    @Column(name = "token", nullable = false)
    private String token;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "expire_date", nullable = false)
    private Instant expiryDate;


    public RefreshToken createRefreshToken(String refreshToken, Member member, Instant instant){
        return new RefreshToken(refreshToken, member, instant);
    }

}