package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.customenum.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "member")
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class Address extends BaseEntity{

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "regDate")
    @CreatedDate
    private LocalDateTime regDate;

    @Enumerated(STRING)
    @Column(name = "addressStatus")
    private AddressType addressType = AddressType.ETC;

}
