package com.example.socket_jpa_querydsl_test.domain;

import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {"member_id","email", "phoneNumber"}))
@EntityListeners(AuditingEntityListener.class)
@Data
//@ToString(exclude = "addresses")
public class Member extends BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "member_id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    @Convert(converter = PasswordConverter.class)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public void addAddress(Address address){
        addresses.add(address);
    }

}
