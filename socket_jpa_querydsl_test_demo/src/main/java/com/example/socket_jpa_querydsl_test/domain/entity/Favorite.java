package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.customenum.EnrollType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "favorite_id"))
public class Favorite extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    private Friend friend;

    @Column(name = "enroll_type")
    @Enumerated(STRING)
    private EnrollType enrollType = EnrollType.Add;

    @Column(name = "enroll_time")
    private ZonedDateTime enrollTime = ZonedDateTime.now();

}
