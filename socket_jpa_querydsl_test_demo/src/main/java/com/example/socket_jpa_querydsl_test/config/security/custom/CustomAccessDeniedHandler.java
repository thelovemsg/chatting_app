package com.example.socket_jpa_querydsl_test.config.security.custom;

import com.example.socket_jpa_querydsl_test.api.ApiResponse;
import com.example.socket_jpa_querydsl_test.api.CustomResponseUtils;
import com.example.socket_jpa_querydsl_test.api.Message;
import com.example.socket_jpa_querydsl_test.api.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseEntity<ApiResponse<String>> responseEntity = CustomResponseUtils.createJsonResponseWithStatus(null, Message.LOGOUT_FAILURE, HttpStatus.BAD_REQUEST);
        response.getWriter().write(JsonUtil.toJson(responseEntity.getBody()));
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }
}
