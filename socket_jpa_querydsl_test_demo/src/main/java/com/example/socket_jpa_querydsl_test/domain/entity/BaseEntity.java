package com.example.socket_jpa_querydsl_test.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Where(clause = "deleted = false")
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.socket_jpa_querydsl_test.config.jpa.CustomIdGenerator")
    @Column(name = "id")
    protected Long id;

    @Column(name = "createDate")
    private ZonedDateTime createDate;

    @Column(name = "modifyDate")
    private ZonedDateTime modifyDate;

    @Column(name = "createdBy", nullable = false, columnDefinition = "varchar(255) default 'ADMIN'")
    private String createdBy = "ADMIN";

    @Column(name = "lastModifiedBy", nullable = false, columnDefinition = "varchar(255) default 'ADMIN'")
    private String lastModifiedBy  = "ADMIN";

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    public void delete(){
        deleted = true;
    }

    @PrePersist
    protected void prePersist() {
        createDate = ZonedDateTime.now();
        modifyDate = ZonedDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        modifyDate = ZonedDateTime.now();
    }

}
