package com.example.socket_jpa_querydsl_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Data
public class Address extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String address_id;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
