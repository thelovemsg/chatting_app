package com.example.socket_jpa_querydsl_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "createdDate")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;

    @Column(name = "createdBy", nullable = false, columnDefinition = "varchar(255) default 'ADMIN'")
    private String createdBy = "ADMIN";
    @Column(name = "lastModifiedBy", nullable = false, columnDefinition = "varchar(255) default 'ADMIN'")
    private String lastModifiedBy  = "ADMIN";

//    @Column(name = "flag", columnDefinition = "TINYINT(1) CHECK (flag in (0,1))")
//    @ColumnDefault("1")
//    private int flag;

}
