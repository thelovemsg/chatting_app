package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberSaveDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class MemberApiTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("회원가입_비밀번호_길이_형식_오류")
    @Test
    public void saveMemberTest_password_error() throws Exception {
        //password 길이 오류 체크
        MemberSaveDto memberSaveDto = new MemberSaveDto("thelovemsg@naver.com", "test", "nickname", "01012345678", "passwo", "address1", "address2");

        String jsonData = objectMapper.writeValueAsString(memberSaveDto);

//        IllegalArgumentException을 던지지만 ServletException으로 변환됌 (???)
        Assertions.assertThrows(ServletException.class, () -> {
           mockMvc.perform(
                MockMvcRequestBuilders.post("/memberSave")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData)
            );
        });

    }

}
