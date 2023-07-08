package com.example.socket_jpa_querydsl_test.api.dto.response;

import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String name;
    private String nickname;

    public MemberResponseDto(Member member){
        this.name = member.getName();
        this.nickname = member.getNickname();
    }
}
