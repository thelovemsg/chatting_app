package com.example.socket_jpa_querydsl_test.domain.profile;

import com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType;
import com.example.socket_jpa_querydsl_test.domain.entity.base.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.member.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "profile_id"))
@Builder
@Where(clause = "deleted = false")
public class Profile extends BaseEntity {

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "profile")
    @Builder.Default
    private List<ProfileConn> profileConns = new ArrayList<>();

    @Column(name = "content")
    private String name;

    @Column(name = "status_description")
    private String statusDescription;

    @Column(name = "upload_date")
    @Builder.Default
    private LocalDateTime uploadDateTime = LocalDateTime.now();

    @Column(name = "profile_type")
    @Builder.Default
    @Enumerated(STRING)
    private ProfileType profileType = ProfileType.MAIN;

    @OneToMany(mappedBy = "profile")
    @Builder.Default
    private List<ProfileConn> profileConnList = new ArrayList<>();

    private void removeProfileConn(ProfileConn profileConn) {
        if(profileConnList.contains(profileConn))
            profileConnList.remove(profileConn);
    }

    private void addProfileConn(ProfileConn profileConn) {
        if(!profileConnList.contains(profileConn))
            profileConnList.add(profileConn);
    }

}
