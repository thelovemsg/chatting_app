package com.example.socket_jpa_querydsl_test.domain.profile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = -1019520021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfile profile = new QProfile("profile");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final com.example.socket_jpa_querydsl_test.domain.entity.member.QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final ListPath<ProfileConn, QProfileConn> profileConnList = this.<ProfileConn, QProfileConn>createList("profileConnList", ProfileConn.class, QProfileConn.class, PathInits.DIRECT2);

    public final ListPath<ProfileConn, QProfileConn> profileConns = this.<ProfileConn, QProfileConn>createList("profileConns", ProfileConn.class, QProfileConn.class, PathInits.DIRECT2);

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType> profileType = createEnum("profileType", com.example.socket_jpa_querydsl_test.domain.customenum.ProfileType.class);

    public final StringPath statusDescription = createString("statusDescription");

    public final DateTimePath<java.time.LocalDateTime> uploadDateTime = createDateTime("uploadDateTime", java.time.LocalDateTime.class);

    public QProfile(String variable) {
        this(Profile.class, forVariable(variable), INITS);
    }

    public QProfile(Path<? extends Profile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfile(PathMetadata metadata, PathInits inits) {
        this(Profile.class, metadata, inits);
    }

    public QProfile(Class<? extends Profile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.socket_jpa_querydsl_test.domain.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

