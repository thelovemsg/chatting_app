package com.example.socket_jpa_querydsl_test.domain.entity.member;

import com.example.socket_jpa_querydsl_test.domain.customenum.AddressType;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
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
public class Address extends BaseEntity {

    @Column(name = "zipCode")
    private String zipCode;

    @Column(name = "address")
    private String address;

    @Column(name = "addressDetails")
    private String addressDetail;

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

    public Address(String address, String addressDetail) {
        this.address = address;
        this.addressDetail = addressDetail;
    }

}
