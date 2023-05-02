package com.example.socket_jpa_querydsl_test.login;

import com.example.socket_jpa_querydsl_test.config.security.provider.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtUserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    @DisplayName("/test는 'USER' 만 접근 가능")
    public void testSecuredTestUrl1() throws Exception {
        // Load the test user
        UserDetails userDetails = userDetailsService.loadUserByUsername("test1@naver.com");

        // Generate a JWT for the test user
        String jwt = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())).getAccessToken();

        // Use the JWT in a request to the secured endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/test")
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("/test는 'MANAGER' 는 접근 불가!")
    public void testSecuredEndpointWithJwt() throws Exception {
        // Load the test user
        UserDetails userDetails = userDetailsService.loadUserByUsername("test2@naver.com");

        // Generate a JWT for the test user
        String jwt = jwtTokenProvider.generateToken(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())).getAccessToken();

        // Use the JWT in a request to the secured endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/test")
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
