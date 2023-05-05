package com.example.socket_jpa_querydsl_test.controller;

import com.example.socket_jpa_querydsl_test.api.dto.request.RefreshTokenRequest;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenExpiredException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenInvalidException;
import com.example.socket_jpa_querydsl_test.config.security.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/api")
@RequiredArgsConstructor
public class JwtAuthController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            String newAccessToken = jwtTokenProvider.refreshAccessToken(request.getRefreshToken());
            return ResponseEntity.ok(newAccessToken);
        } catch (JwtTokenExpiredException e) {
            // Return an appropriate HTTP response with a relevant message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token expired. Please log in again.");
        } catch (JwtTokenInvalidException e) {
            // Return an appropriate HTTP response with a relevant message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token. Please log in again.");
        }
    }
}
