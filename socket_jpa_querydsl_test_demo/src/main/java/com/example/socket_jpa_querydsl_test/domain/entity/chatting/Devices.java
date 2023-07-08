package com.example.socket_jpa_querydsl_test.domain.entity.chatting;

import com.example.socket_jpa_querydsl_test.domain.customenum.DeviceType;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "device_id"))
public class Devices extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "model")
    private String model;

    @Column(name = "serial_number")
    private String serialNumber;

    @Enumerated(STRING)
    @Column(name = "device_type")
    private DeviceType deviceType = DeviceType.DESKTOP;

    @Column(name = "last_active_date")
    private ZonedDateTime createdDate = ZonedDateTime.now();

}
