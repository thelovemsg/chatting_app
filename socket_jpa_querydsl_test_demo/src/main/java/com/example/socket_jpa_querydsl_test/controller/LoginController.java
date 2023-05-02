package com.example.socket_jpa_querydsl_test.controller;

import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberLoginRequestDto;
import com.example.socket_jpa_querydsl_test.api.dto.request.RefreshTokenRequest;
import com.example.socket_jpa_querydsl_test.config.security.provider.JwtTokenProvider;
import com.example.socket_jpa_querydsl_test.domain.utils.TokenInfo;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.customResponse;
import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.getErrorMessage;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;

    @PostMapping("/login")
    public TokenInfo loginMember(@Valid @RequestBody MemberLoginRequestDto memberLoginRequestDto, BindingResult result) {
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(email, password);
        return tokenInfo;
    }

    @PostMapping("/refresh_token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
//        String refreshToken = request.getRefreshToken();
//        String accessToken = jwtTokenProvider.refreshToken(refreshToken);
//        return ResponseEntity.ok(new TokenResponse(accessToken));
        return null;
    }
}
