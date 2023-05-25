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
    private final String ACCESS_TOKEN = "accessToken";
    private final String REFRESH_TOKEN = "refreshToken";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, ServletException {

        // 1. Request Header 에서 JWT 토큰 추출
        String token = resolveToken((HttpServletRequest) request, ACCESS_TOKEN);

        try {
            if (token != null) {
                jwtTokenProvider.validateToken(token);
                // token is valid, set the authentication for the context
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtTokenExpiredException e) {
            // Handle token expiration: issue a new access token using the refresh token\

            String refreshToken = resolveToken((HttpServletRequest) request, REFRESH_TOKEN);
            if (jwtTokenProvider.isValidToken(refreshToken)) {
                // refresh token is valid, use it to create a new access token
                String newAccessToken = jwtTokenProvider.refreshAccessToken(refreshToken);
                // Set new access token in the http-only cookie
                Cookie newAccessTokenCookie = new Cookie("accessToken", newAccessToken);
                newAccessTokenCookie.setHttpOnly(true);
                ((HttpServletResponse) response).addCookie(newAccessTokenCookie);
                // Set the new authentication for the context
                Authentication authentication = jwtTokenProvider.getAuthentication(newAccessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                // Refresh token is also expired/invalid, need re-authentication
                var httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setContentType(ContentType.JSON.getContentType());
                ErrorMessage errorMessage = new ErrorMessage("Refresh token expired. Please re-authenticate.", "110");
                httpResponse.getWriter().write(JsonUtil.toJson(errorMessage));
                httpResponse.getWriter().flush();
                return;
            }
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
    private String resolveToken(HttpServletRequest request, String targetCookie) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(targetCookie)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
