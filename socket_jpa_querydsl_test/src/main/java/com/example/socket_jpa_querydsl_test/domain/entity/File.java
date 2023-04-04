package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Builder
@Table(name = "file")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class File {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "file_id")
    private String id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String storedFileName;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private String contentType;

    private LocalDateTime uploadTimestamp;

    private LocalDateTime deleteTimeStamp;

    @Column
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
