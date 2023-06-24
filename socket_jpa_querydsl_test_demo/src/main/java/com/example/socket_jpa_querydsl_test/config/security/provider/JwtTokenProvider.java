package com.example.socket_jpa_querydsl_test.config.security.provider;

import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenEmptyClaimsException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenExpiredException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenInvalidException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenUnsupportedException;
import com.example.socket_jpa_querydsl_test.config.security.custom.CustomUserDetails;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.security.RefreshToken;
import com.example.socket_jpa_querydsl_test.domain.utils.TokenInfo;
import com.example.socket_jpa_querydsl_test.repository.etc.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey, RefreshTokenRepository refreshTokenRepository) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.refreshTokenRepository = refreshTokenRepository;
    }


    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date accessTokenExpireDate = new Date(now + 60*60*1000);
        Date refreshTokenExpireDate = new Date(now + 60*60*60*1000);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getMemberId();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("id", memberId) // set the least of information of user
                .setIssuedAt(new Date(now))
                .setExpiration(accessTokenExpireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .claim("id", memberId) // set the least of information of user
                .setIssuedAt(new Date(now))
                .setExpiration(refreshTokenExpireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        saveRefreshToken(refreshToken, Member.returnMemberWithId(memberId));

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
    
    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public String getAuthentication(String accessToken) {
        log.info("getAuthentication");
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        return (String) claims.get("auth");
    }

    /**
     *
     * @param token
     * @return
     * @description this method check if the token is valid.
     * Original one just checked the validation of token.
     * But, I came to know we need to issue the new access token for user
     * when it's expired. For this, return value must be changed!
     */
    public void validateToken(String token) {
        log.info("validateToken");
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
            throw new JwtTokenInvalidException("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            throw new JwtTokenExpiredException("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
            throw new JwtTokenUnsupportedException("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
            throw new JwtTokenEmptyClaimsException("JWT claims string is empty");
        }
    }

    private Claims parseClaims(String accessToken) {
        log.info("parseClaims...");
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private void saveRefreshToken(String refreshToken, Member member) {
        refreshTokenRepository.save(new RefreshToken().createRefreshToken(refreshToken, member, Instant.now().plusSeconds(86400000)));
    }

    // A method to generate a new access token using a refresh token
    public String refreshAccessToken(String refreshToken) {
        // Step 1: Check if the refresh token exists in the database
        Optional<RefreshToken> refreshTokenOpt = refreshTokenRepository.findByToken(refreshToken);
        if (refreshTokenOpt.isEmpty()) {
            throw new JwtTokenInvalidException("Invalid refresh token");
        }

        RefreshToken storedRefreshToken = refreshTokenOpt.get();

        // Step 2: Check if the refresh token is expired
        if (storedRefreshToken.getExpiryDate().isBefore(Instant.now())) {
            throw new JwtTokenExpiredException("Expired refresh token");
        }

        // Step 3: Validate the refresh token using the `validateToken` method
        Claims claims = parseClaims(refreshToken);

        // If all validations pass, create and return a new access token
        String newAccessToken = createAccessToken(storedRefreshToken.getMember(), claims);
        return newAccessToken;
    }

    public String createAccessToken(Member member, Claims claims) {
        // Generate a new access token with the necessary claims and expiration
        long now = (new Date()).getTime();
        Date accessTokenExpireDate = new Date(now + 8640000); // or any other expiration time

        String accessToken = Jwts.builder()
                .setSubject(claims.getSubject())
                .claim("auth", claims.get("auth"))
                .claim("id", member.getId())
                .setIssuedAt(new Date(now))
                .setExpiration(accessTokenExpireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return accessToken;
    }

}
