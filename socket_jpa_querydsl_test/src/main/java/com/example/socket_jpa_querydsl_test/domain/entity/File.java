package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class File {

    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.socket_jpa_querydsl_test.config.CustomIdGenerator")
    @Column(name = "file_id")
    private String id;

    @Column(nullable = false, name = "file_name")
    private String fileName;

    @Column(nullable = false, name = "stored_file_name")
    private String storedFileName;

    @Column(nullable = false, name = "file_size")
    private Long fileSize;

    @Column(nullable = false, name = "file_type")
    private String fileType;

    @Column(nullable = false, name = "upload_time")
    private LocalDateTime uploadTime;

    @Column(nullable = false, name = "delete_time")
    private LocalDateTime deleteTime;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
