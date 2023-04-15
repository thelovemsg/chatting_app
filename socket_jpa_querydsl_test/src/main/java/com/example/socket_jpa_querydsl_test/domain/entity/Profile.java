package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@AttributeOverride(name = "id", column = @Column(name = "profile_id"))
public class Profile extends BaseEntity{

}
