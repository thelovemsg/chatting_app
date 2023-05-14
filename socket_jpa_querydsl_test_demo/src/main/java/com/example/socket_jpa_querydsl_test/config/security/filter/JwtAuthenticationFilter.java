package com.example.socket_jpa_querydsl_test.config.security.filter;

import com.example.socket_jpa_querydsl_test.api.util.JsonUtil;
import com.example.socket_jpa_querydsl_test.config.customenum.ContentType;
import com.example.socket_jpa_querydsl_test.config.exception.*;
import com.example.socket_jpa_querydsl_test.config.security.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * Spring Security doesn't automatically handle JSON login requests by default.
 * It expects login requests from an HTML form with parameters named "username" and "password".
 * But, you can configure Spring Security to accept JSON login requests.
 *
 * Also, I use email, not username to find the member.
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {

        // 1. Request Header 에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request);

        try {
            if (token != null) {
                jwtTokenProvider.validateToken(token);
                // 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtTokenExpiredException e) {
            // Handle token expiration: issue a new access token using the refresh token
            var httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType(ContentType.JSON.getContentType());

            ErrorMessage errorMessage = new ErrorMessage("Access token expired. Please use your refresh token to request a new access token.", "109");
            httpResponse.getWriter().write(JsonUtil.toJson(errorMessage));
            httpResponse.getWriter().flush();
            return;
        } catch (JwtTokenInvalidException | JwtTokenUnsupportedException | JwtTokenEmptyClaimsException e) {
            // Handle other JWT token errors, e.g., invalid, unsupported or empty claims tokens
            // You can return an error response to the client or just log the error.
        }

        // 2. validateToken 으로 토큰 유효성 검사
        chain.doFilter(request, response);
    }

    // Request Header 에서 토큰 정보 추출
//    private String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
    private String resolveToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("accessToken")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
