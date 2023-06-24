package com.example.socket_jpa_querydsl_test.config.security.filter;

import com.example.socket_jpa_querydsl_test.api.util.JsonUtil;
import com.example.socket_jpa_querydsl_test.config.customenum.ContentTypeEnum;
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
            }
        } catch (JwtTokenExpiredException e) {
            /// Handle token expiration: issue a new access token using the refresh token
            String refreshToken = resolveToken((HttpServletRequest) request, REFRESH_TOKEN);
            try {
                jwtTokenProvider.validateToken(refreshToken);
                // refresh token is valid, use it to create a new access token
                String newAccessToken = jwtTokenProvider.refreshAccessToken(refreshToken);

                // Set new access token in the http-only cookie
                Cookie newAccessTokenCookie = new Cookie("accessToken", newAccessToken);
                newAccessTokenCookie.setHttpOnly(true);
                ((HttpServletResponse) response).addCookie(newAccessTokenCookie);
            } catch (Exception ex) {
                // Refresh token is also expired/invalid, need re-authentication
                returnFailResponse((HttpServletResponse) response
                        ,"Both access token and refresh token are expired. Please log in again."
                        ,"110");
                return;  // IMPORTANT! Stop further filter processing and return the response
            }
        } catch (Exception e) {
            // Refresh token is also expired/invalid, need re-authentication
            returnFailResponse((HttpServletResponse) response
                    ,"Unhandled Exception occur!"
                    ,"110");
            return;
        }

        // 2. validateToken 으로 토큰 유효성 검사
        chain.doFilter(request, response);
    }

    private static void returnFailResponse(HttpServletResponse response, String message, String code) throws IOException {
        var httpResponse = response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType(ContentTypeEnum.JSON.getContentType());
        ErrorMessage errorMessage = new ErrorMessage(message, code);
        httpResponse.getWriter().write(JsonUtil.toJson(errorMessage));
        httpResponse.getWriter().flush();
    }

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
