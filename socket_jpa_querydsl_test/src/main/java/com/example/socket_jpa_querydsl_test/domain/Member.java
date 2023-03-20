package com.example.socket_jpa_querydsl_test.domain;

import com.example.socket_jpa_querydsl_test.domain.status.AddressStatus;
import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverter;
import com.example.socket_jpa_querydsl_test.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {"member_id","email", "phoneNumber", "nickname"}))
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "addresses")
public class Member extends BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "member_id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @JsonIgnore
    @Column(name = "password")
    @Convert(converter = PasswordConverter.class)
    private String password;

//    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
    }

    public static Member createMember(MemberDto memberDto){
        Address address = new Address();
        address.setAddress1(memberDto.getAddress1());
        address.setAddress2(memberDto.getAddress2());
        address.setAddressStatus(AddressStatus.PRIMARY);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        return new Member()
                .builder()
                .name(memberDto.getUsername())
                .email(memberDto.getEmail())
                .addresses(addresses)
                .password(memberDto.getPassword())
                .phoneNumber(memberDto.getPhoneNumber())
                .build();
    }

}
