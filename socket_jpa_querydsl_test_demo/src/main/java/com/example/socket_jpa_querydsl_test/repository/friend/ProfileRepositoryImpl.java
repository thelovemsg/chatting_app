package com.example.socket_jpa_querydsl_test.repository.friend;

import com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType;
import com.example.socket_jpa_querydsl_test.domain.entity.Member;
import com.example.socket_jpa_querydsl_test.domain.profile.Profile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.socket_jpa_querydsl_test.domain.profile.QProfile.profile;

@Repository
public class ProfileRepositoryImpl extends SimpleJpaRepository<Profile, Long> {

    private final JPAQueryFactory queryFactory;

    public ProfileRepositoryImpl(EntityManager entityManager) {
        super(Profile.class, entityManager);
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public Profile getMainProfileByMember(Member targetMember) {
        return queryFactory.selectFrom(profile)
                .where(profile.member.eq(targetMember)
                    .and(profile.profileType.eq(ProfileType.MAIN)))
                .orderBy(profile.uploadDateTime.desc())
                .fetchFirst();
    }

    public List<Profile> getSpecificProfilesByMemberId(Member targetMember, ProfileType profileType) {
        return queryFactory.selectFrom(profile)
                .where(profile.member.eq(targetMember)
                        .and(profile.profileType.eq(profileType))).fetch();
    }

}
