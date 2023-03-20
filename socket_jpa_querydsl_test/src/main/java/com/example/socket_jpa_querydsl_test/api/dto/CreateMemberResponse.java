package com.example.socket_jpa_querydsl_test.api.dto;

import com.example.socket_jpa_querydsl_test.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMemberResponse {
    private String id;
    private String name;
    private String nickname;

    public CreateMemberResponse(Member member){
        this.name = member.getName();
        this.nickname = member.getNickname();
    }
}
