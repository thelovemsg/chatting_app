package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Where(clause = "deleted = false")
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.socket_jpa_querydsl_test.config.CustomIdGenerator")
    @Column(name = "id")
    protected Long id;

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

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.TRUE;

    public void delete(){
        deleted = false;
    }

}
