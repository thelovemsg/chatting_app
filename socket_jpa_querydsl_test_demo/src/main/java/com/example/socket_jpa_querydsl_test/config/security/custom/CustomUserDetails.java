package com.example.socket_jpa_querydsl_test.config.security.custom;

import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {
    private final Long memberId;
    public CustomUserDetails(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getPassword(), authorities);
        this.memberId = member.getId();
    }

}
