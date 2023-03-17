package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.Address;
import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.domain.status.AddressStatus;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Transactional
//@Rollback(value = false)
public class MemberTest {
    @Autowired
    private MemberService memberService;


    @DisplayName("phone number saving test")
    @Test
    public void saveMember_number_exception() {
        Member member1 = new Member();
        member1.setName("테스트1");
        member1.setPhoneNumber("테스트1");

//        Assertions.assertThrows(ConstraintViolationException.class, () -> {
        Member member = memberService.saveMember(member1);
        Member member2 = memberService.saveMember(member1);
//        });

//        Assertions.assert memberService.saveMember(member1);

//        Address address = new Address();
//        address.setAddress1("주소1");
//        address.setAddress2("주소2");

//        Member member = memberService.saveMember();
//        Assertions.assertThat()
    }
}
