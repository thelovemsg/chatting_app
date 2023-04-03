package com.example.socket_jpa_querydsl_test.domain;

import com.example.socket_jpa_querydsl_test.domain.status.AddressStatus;
import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverter;
import com.example.socket_jpa_querydsl_test.api.dto.entity.MemberSaveDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(uniqueConstraints =
            {
                @UniqueConstraint(columnNames = {"nickname"}),
                @UniqueConstraint(columnNames = {"phoneNumber"}),
                @UniqueConstraint(columnNames = {"email"})
            }
        )
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
    @Builder.Default
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
    }

    public static Member createMember(MemberSaveDto memberSaveDto){
        Address address = new Address();
        address.setAddress1(memberSaveDto.getAddress1());
        address.setAddress2(memberSaveDto.getAddress2());
        address.setAddressStatus(AddressStatus.PRIMARY);

        List<Address> addresses = new ArrayList<>();
        addresses.add(address);

        return new Member()
                .builder()
                .name(memberSaveDto.getName())
                .nickname(memberSaveDto.getNickname())
                .email(memberSaveDto.getEmail())
                .addresses(addresses)
                .password(memberSaveDto.getPassword())
                .phoneNumber(memberSaveDto.getPhoneNumber())
                .build();
    }

}
