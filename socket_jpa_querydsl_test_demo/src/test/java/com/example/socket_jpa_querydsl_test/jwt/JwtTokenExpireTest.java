package com.example.socket_jpa_querydsl_test.jwt;

import com.example.socket_jpa_querydsl_test.api.dto.request.RefreshTokenRequest;
import com.example.socket_jpa_querydsl_test.config.security.LoginRequest;
import com.example.socket_jpa_querydsl_test.domain.utils.TokenInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Map;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class JwtTokenExpireTest {

    @Autowired
    private MockMvc mockMvc;

    private TokenInfo loginAndGetTokens() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String loginRequestJson = objectMapper.writeValueAsString(createLoginRequest());

        String responseJson = mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        Map<String, Object> response = objectMapper.readValue(responseJson, new TypeReference<Map<String, Object>>() {});

        String accessToken = (String) response.get("accessToken");
        String refreshToken = (String) response.get("refreshToken");
        return new TokenInfo("Bearer", accessToken, refreshToken);
    }

    @Test
    @DisplayName("토큰 만료시 재발급 테스트")
    public void testExpiredAccessToken() throws Exception {
        /**
         * Work Process
         *
         * 1. Try login and get tokens
         * 2. Access token is expired!
         * 3. Try access to '/test' endpoint.
         * 4. Notice if access token is expired.
         * 5. Request new access token with refresh token.
         * 5. If refresh token is expired, It returns code and message by guiding login agin.
         * */

        // 1. Try login and get tokens
        // 2. Access token is expired!
        TokenInfo tokenInfo = loginAndGetTokens();

        // 3. Try access to '/test' endpoint.
        MvcResult testResult = mockMvc.perform(MockMvcRequestBuilders.get("/test")
                        .header("Authorization", "Bearer " + tokenInfo.getAccessToken()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();

        String contentAsString = testResult.getResponse().getContentAsString();

        // 4. Notice if access token is expired.
        JSONObject jsonObject = new JSONObject(contentAsString);
        String code = jsonObject.getString("code");
        Assertions.assertThat(code).isEqualTo("109");

        // 5. Request new access token with refresh token.
        ObjectMapper objectMapper = new ObjectMapper();
        String accessTokenRequestJson = objectMapper.writeValueAsString(createRefreshTokenRequest(tokenInfo.getRefreshToken()));
        String responseJson = mockMvc.perform(MockMvcRequestBuilders.post("/refreshToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(accessTokenRequestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        log.info("responseJson :: {}", responseJson);

    }

    private LoginRequest createLoginRequest() {
        return new LoginRequest("test1@naver.com", "password1234");
    }

    private RefreshTokenRequest createRefreshTokenRequest(String refreshToken) {
        return new RefreshTokenRequest(refreshToken);
    }

}
