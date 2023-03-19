package com.example.socket_jpa_querydsl_test;

import com.example.socket_jpa_querydsl_test.domain.Address;
import com.example.socket_jpa_querydsl_test.domain.Member;
import com.example.socket_jpa_querydsl_test.domain.status.AddressStatus;
import com.example.socket_jpa_querydsl_test.service.AddressService;
import com.example.socket_jpa_querydsl_test.service.MemberService;
import jakarta.transaction.Transactional;
import jakarta.validation.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberTest {
    @Autowired
    private MemberService memberService;

    @Autowired
    private AddressService addressService;

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @DisplayName("init data 조회하기")
    @Test
    public void find_init_data() {
        // member데이터는 초기화시 2개
        List<Member> members = memberService.getMembers();
        assertThat(members.size()).isEqualTo(2);

        for (Member member : members) {
            System.out.println(addressService.findAddresses(member.getId()).toString());
        }

        // address데이터는 초기화시 3개
//        assertThat(addressService.getAdress().size()).isEqualTo(2);
    }

    @DisplayName("phone number setting test")
    @Test
    public void saveMember_number_exception() {
        Member member1 = new Member();
        member1.setName("테스트1");
        member1.setPhoneNumber("테스트1");

        Set<ConstraintViolation<Member>> violations = validator.validate(member1);

        if (!violations.isEmpty()) {
            Assertions.assertThrows(ConstraintDeclarationException.class, () -> {
                if(!violations.isEmpty())
                    throw new ConstraintDeclarationException();
            });
        }

//        memberService.saveMember(member1);

//        Address address = new Address();
//        address.setAddress1("주소1");
//        address.setAddress2("주소2");

//        Member member = memberService.saveMember();
//        Assertions.assertThat()
    }

    @DisplayName("save member")
    @Test
    @Rollback(value = false)
    public void saveMember() {
        Member member = new Member();
        member.setName("thelovemsg");
        member.setEmail("thelovemsg@naver.com");
        member.setPassword("1234");
        Member member1 = memberService.saveMember(member);
        System.out.println("member1 = " + member1.getCreatedBy());

        Address address = new Address();
        address.setAddress1("address1");
        address.setAddress2("address2");
        address.setMember(member);
        addressService.saveAddress(address);


    }
}
