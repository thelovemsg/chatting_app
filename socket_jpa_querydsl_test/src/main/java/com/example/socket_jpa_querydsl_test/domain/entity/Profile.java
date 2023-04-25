package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "profile_id"))
@Where(clause = "deleted = false")
public class Profile extends BaseEntity{

    @Column(name = "content")
    private String content;

}
