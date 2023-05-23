package com.example.socket_jpa_querydsl_test.config.security;

import com.example.socket_jpa_querydsl_test.config.security.custom.CustomAccessDeniedHandler;
import com.example.socket_jpa_querydsl_test.config.security.custom.CustomLogoutSuccessHandler;
import com.example.socket_jpa_querydsl_test.config.security.filter.JwtAuthenticationFilter;
import com.example.socket_jpa_querydsl_test.config.security.provider.JwtTokenProvider;
import com.example.socket_jpa_querydsl_test.domain.entity.RoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private static final List<String> PERMIT_ALL_ENDPOINTS = Arrays.asList(
            "/", "/loginCheck", "/memberSave", "/error", "/users",
            "/user/login/**", "/login", "/logout", "/refreshToken"
    );

    private static final List<String> USER_ROLE_ENDPOINTS = Arrays.asList(
            "/test"
    );

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests(registry -> {
                registry.requestMatchers(PERMIT_ALL_ENDPOINTS.toArray(new String[0])).permitAll()
                        .requestMatchers(USER_ROLE_ENDPOINTS.toArray(new String[0]))
                        .hasRole(RoleEnum.USER.toString())
//                            .requestMatchers("/messages").hasRole("MANAGER")
//                            .requestMatchers("/config").hasRole("ADMIN")
                        .anyRequest().authenticated();
            })
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(customLogoutSuccessHandler)
                .deleteCookies("accessToken", "refreshToken")
                .invalidateHttpSession(true)
                .and()
                .addFilterAfter(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
             .exceptionHandling(exception -> {
                 exception.accessDeniedHandler(customAccessDeniedHandler);
             });

        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        // Set the allowed origins or just use '*' to allow all origins
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3002")); // Replace this with your React app's URL
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
