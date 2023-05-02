package com.example.socket_jpa_querydsl_test.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("올바른 정보로 로그인 시 성공")
    public void successfulAuthentication() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(APPLICATION_JSON)
                .content("{\"email\":\"test1@naver.com\", \"password\":\"password1234\"}"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("잘못된 정보로 로그인 시 실패")
    public void wrongAuthentication() throws Exception {
        mockMvc.perform(post("/login")
                .contentType(APPLICATION_JSON)
                .content("{\"email\":\"test11@naver.com\", \"password\":\"password1234\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/test 에는 role이 User인 사람만 접근 가능.")
    @WithMockUser(username = "test1@naver.com", roles = {"USER"})
    public void accessAllowedForUserToTest() throws Exception {
        mockMvc.perform(post("/test")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("/test 에는 role이 Maneger인 사람은 접근 불가능.")
    @WithMockUser(username = "test2@naver.com", roles = {"MANAGER"})
    public void accessNotAllowedForToTest() throws Exception {
        mockMvc.perform(post("/test")
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().is4xxClientError());
    }


}
