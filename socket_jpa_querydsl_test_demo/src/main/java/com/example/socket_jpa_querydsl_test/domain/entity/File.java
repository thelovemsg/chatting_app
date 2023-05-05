package com.example.socket_jpa_querydsl_test.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
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
    private ZonedDateTime uploadTime;

    @Column(name = "delete_time")
    private ZonedDateTime deleteTime;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
