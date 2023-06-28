package com.example.socket_jpa_querydsl_test.domain.profile;

import com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType;
import com.example.socket_jpa_querydsl_test.domain.entity.BaseEntity;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.entity.ProfileConn;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.ZonedDateTime;
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
    private List<ProfilePermission> profilePermission = new ArrayList<>();

    @Column(name = "content")
    private String name;

    @Column(name = "status_description")
    private String statusDescription;

    @Column(name = "upload_date")
    @Builder.Default
    private ZonedDateTime uploadDateTime = ZonedDateTime.now();

    @Enumerated(STRING)
    @Column(name = "profile_type")
    @Builder.Default
    private ProfileType profileType = ProfileType.MAIN;

    @OneToMany(mappedBy = "profile")
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
