package com.example.socket_jpa_querydsl_test.domain;

import com.example.socket_jpa_querydsl_test.domain.utils.PasswordConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints =  @UniqueConstraint(columnNames = {"id","email", "phoneNumber"}))
@EntityListeners(AuditingEntityListener.class)
@Data
public class Member extends BaseEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "name")
    private String name;
    @Column(name = "phoneNumber")
    @Pattern(regexp = "\\d+", message = "Must contain only digits")
    private String phoneNumber;

    @Column(name = "password")
    @Convert(converter = PasswordConverter.class)
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

}
