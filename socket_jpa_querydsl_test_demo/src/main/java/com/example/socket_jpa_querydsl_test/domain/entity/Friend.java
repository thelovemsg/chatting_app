package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.domain.profile.ProfilePermission;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.LAZY;
import static lombok.Builder.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "friend_id"))
public class Friend extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "friend")
    private List<ProfilePermission> profilePermission = new ArrayList<>();

    @Column(name = "from_member_id")
    private String fromMemberId;

    @Column(name = "to_member_id")
    private String toMemberId;

    @Column(name = "are_we_friend", columnDefinition = "false")
    @Enumerated(STRING)
    private FlagStatus areWeFriend = FlagStatus.YES;

    @Column(name = "apply_date")
    @CreatedDate
    private ZonedDateTime applyDate = ZonedDateTime.now();

}
