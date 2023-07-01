package com.example.socket_jpa_querydsl_test.domain.entity;

import com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus;
import com.example.socket_jpa_querydsl_test.domain.profile.ProfileConn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "friend_id"))
@Builder
public class Friend extends BaseEntity{

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "from_member_id")
    private Member fromMember;

    @OneToMany(mappedBy = "friend")
    @Builder.Default
    private List<ProfileConn> profileConns = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JsonIgnore
    @JoinColumn(name = "to_member_id")
    private Member toMember;

    @Column(name = "is_accepted")
    @Builder.Default
    @Enumerated(STRING)
    private FlagStatus isAccepted = FlagStatus.YES;

    @Column(name = "apply_date")
    @CreatedDate
    @Builder.Default
    private LocalDateTime applyDate = LocalDateTime.now();

}

