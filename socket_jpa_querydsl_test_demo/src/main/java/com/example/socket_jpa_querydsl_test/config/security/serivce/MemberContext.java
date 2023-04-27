package com.example.socket_jpa_querydsl_test.config.security.serivce;

import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberContext extends User {

    private final Member member;

    public MemberContext(Member member, Collection<? extends GrantedAuthority> authorities) {
        super(member.getEmail(), member.getPassword(), authorities);
        this.member = member;
    }

    public Member getAccount() {
        return member;
    }
}
