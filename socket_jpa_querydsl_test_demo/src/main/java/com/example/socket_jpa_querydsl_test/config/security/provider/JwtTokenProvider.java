package com.example.socket_jpa_querydsl_test.config.security.provider;

import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenEmptyClaimsException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenExpiredException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenInvalidException;
import com.example.socket_jpa_querydsl_test.config.exception.JwtTokenUnsupportedException;
import com.example.socket_jpa_querydsl_test.config.security.custom.CustomUserDetails;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.security.RefreshToken;
import com.example.socket_jpa_querydsl_test.domain.utils.TokenInfo;
import com.example.socket_jpa_querydsl_test.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
        Date accessTokenExpireDate = new Date(now + 8640000);
        Date refreshTokenExpireDate = new Date(now + 86400000);

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long memberId = userDetails.getMemberId();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("id", memberId)
                .setExpiration(accessTokenExpireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpireDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        saveRefreshToken(refreshToken, memberId);

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
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
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private void saveRefreshToken(String refreshToken, Long memberId) {
        Member member = new Member();
        member.setId(memberId);

        log.info("refreshToken : {}", refreshToken);
        log.info("userId : {}", memberId);

        refreshTokenRepository.save(new RefreshToken().createRefreshToken(refreshToken, member, Instant.now().plusSeconds(86400000)));
    }

    // A method to generate a new access token using a refresh token
    public String refreshToken(String refreshToken) {
        // Here you would look up the refresh token in the database, verify that it is
        // valid and associated with the user, and then generate a new access token.
        // For this example, I'll assume that you have a method validateRefreshToken
        // that performs this validation and returns the user's username if the refresh
        // token is valid.
        String username = validateRefreshToken(refreshToken);

        // Now you can generate a new access token for the user. You'll need a method
        // to load the user's details given their username.
        UserDetails userDetails = loadUserByUsername(username);

        // Create a new authentication token
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // Generate a new access token
        return generateToken(authentication).getAccessToken();
    }

    // Method to validate the refresh token
    private String validateRefreshToken(String refreshToken) {
        // Here you would look up the refresh token in the database and verify that
        // it is valid and associated with the user. If the token is valid, return
        // the user's username. For this example, I'll assume that the refresh token
        // is valid and return a hardcoded username.
        //
        // In a real application, you should implement this method to validate the
        // refresh token against your database and return the associated user's
        // username.
        return "user@example.com";
    }

    // Method to load the user's details given their username
    private UserDetails loadUserByUsername(String username) {
        // Here you would load the user's details from your database and return a
        // UserDetails object. For this example, I'll return a hardcoded user with
        // a single authority.
        //
        // In a real application, you should implement this method to load the user's
        // details from your database.
//        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
//        return new User(username, "", authorities);
        return null;
    }

}
