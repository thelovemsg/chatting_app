package com.example.socket_jpa_querydsl_test.domain;

import com.example.socket_jpa_querydsl_test.domain.status.AddressStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
@ToString(exclude = "member")
public class Address extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id")
    private String id;

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

    @Enumerated(EnumType.STRING)
    private AddressStatus addressStatus = AddressStatus.ETC;

}
