package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.api.Message;
import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberSaveDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Slf4j
public class MemberApiTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("회원가입 - 비밀번호 길이 형식 오류")
    public void saveMemberTest_password_error() throws Exception {
        //password 길이 오류 체크
        MemberSaveDto memberSaveDto = new MemberSaveDto("testtest@naver.com", "testtest", "testtest", "01012345678", "passwo", "address1", "address2");

        String jsonData = objectMapper.writeValueAsString(memberSaveDto);

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/memberSave")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData)
        ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);

        Message responseMessage = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Message.class);
        Assertions.assertEquals("password must be over 8 lengths", responseMessage.getMessage());
    }

    @Test
    @DisplayName("회원가입 시키기")
    public void saveMember() throws Exception {
        MemberSaveDto memberSaveDto = new MemberSaveDto("thelovemsg@naver.com", "test", "nickname", "01012345678", "password1111", "address1", "address2");
        String jsonData = objectMapper.writeValueAsString(memberSaveDto);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/memberSave")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonData)
        ).andReturn();

        log.info("mvcResult :: {} ", mvcResult.getResponse().getContentAsString());

    }

}
