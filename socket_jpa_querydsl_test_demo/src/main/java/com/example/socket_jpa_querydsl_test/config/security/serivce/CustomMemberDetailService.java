package com.example.socket_jpa_querydsl_test.config.security.serivce;

import com.example.socket_jpa_querydsl_test.config.security.custom.CustomUserDetails;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomMemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.getMemberByEmail(email)
            .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFoundException"));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
    private UserDetails createUserDetails(Member member) {
        /*List<String> roles = member.getMemberRoles().stream()
                .map(memberRole -> memberRole.getRoleEnum().toString())
                .collect(Collectors.toList());
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(roles.toArray(new String[0]))
                .build();*/

        List<GrantedAuthority> roles = member.getMemberRoles().stream()
                .map(memberRole -> new SimpleGrantedAuthority(memberRole.getRoleEnum().toString()))
                .collect(Collectors.toList());

        return new CustomUserDetails(member, roles);

    }
}
