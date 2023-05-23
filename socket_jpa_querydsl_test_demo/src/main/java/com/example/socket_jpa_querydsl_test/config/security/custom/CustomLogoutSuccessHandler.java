package com.example.socket_jpa_querydsl_test.config.security.custom;

import com.example.socket_jpa_querydsl_test.api.ApiResponse;
import com.example.socket_jpa_querydsl_test.api.CustomResponseUtils;
import com.example.socket_jpa_querydsl_test.api.Message;
import com.example.socket_jpa_querydsl_test.api.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseEntity<ApiResponse<String>> responseEntity = CustomResponseUtils.createJsonResponseWithStatus(null, Message.LOGOUT_SUCCESS, HttpStatus.OK);
        response.getWriter().write(JsonUtil.toJson(responseEntity.getBody()));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}