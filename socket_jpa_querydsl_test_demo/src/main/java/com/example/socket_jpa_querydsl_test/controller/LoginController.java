package com.example.socket_jpa_querydsl_test.controller;

import com.example.socket_jpa_querydsl_test.api.ApiResponse;
import com.example.socket_jpa_querydsl_test.api.CustomResponseUtils;
import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberLoginRequestDto;
import com.example.socket_jpa_querydsl_test.config.security.provider.JwtTokenProvider;
import com.example.socket_jpa_querydsl_test.domain.utils.TokenInfo;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.example.socket_jpa_querydsl_test.api.CustomResponseUtils.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginMember(@Valid @RequestBody MemberLoginRequestDto memberLoginRequestDto, BindingResult result, HttpServletResponse response) {
        String email = memberLoginRequestDto.getEmail();
        String password = memberLoginRequestDto.getPassword();

        TokenInfo tokenInfo = memberService.login(email, password);

        // Create cookies for both access and refresh tokens
        Cookie accessTokenCookie = new Cookie("accessToken", tokenInfo.getAccessToken());
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(true);
        accessTokenCookie.setPath("/");

        Cookie refreshTokenCookie = new Cookie("refreshToken", tokenInfo.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setPath("/");

        // Add the cookies to the response
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);

        return createJsonResponseWithStatus("", "success", HttpStatus.OK);
    }

    @GetMapping("/loginCheck")
    public ResponseEntity<ApiResponse<String>> loginCheck(
             @CookieValue(value = "accessToken", required = false) String accessToken
            ,@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        log.info("login Check ttes...");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            boolean isValid = jwtTokenProvider.isValidToken(token);
//            if (isValid) {
//                return createJsonResponseWithStatus("You are logged in", "success", HttpStatus.OK);
//            } else if (refreshToken != null && jwtTokenProvider.isValidToken(refreshToken)) {
//                String newToken = jwtTokenProvider.generateAccessToken(refreshToken);
//                // add generateAccessToken for new access token.
//
//                // You should also set this new token in the response headers or cookie as per your design
//                return createJsonResponseWithStatus("You are logged in", "success", HttpStatus.OK);
//            }
//        }
        return createJsonResponseWithStatus("You are not logged in", "failure", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(HttpServletResponse response) {
//        response.addCookie(new Cookie("accessToken", null));
//        response.addCookie(new Cookie("refreshToken", null));
        return createJsonResponseWithStatus("", "success", HttpStatus.OK);
    }

}
