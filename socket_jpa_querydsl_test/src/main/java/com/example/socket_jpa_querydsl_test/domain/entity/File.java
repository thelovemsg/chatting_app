package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@AttributeOverride(name = "id", column = @Column(name = "file_id"))
public class File extends BaseEntity{

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
