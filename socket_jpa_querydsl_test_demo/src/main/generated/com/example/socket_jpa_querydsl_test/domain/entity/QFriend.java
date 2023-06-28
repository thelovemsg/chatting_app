package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFriend is a Querydsl query type for Friend
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriend extends EntityPathBase<Friend> {

    private static final long serialVersionUID = 736410256L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFriend friend = new QFriend("friend");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.ZonedDateTime> applyDate = createDateTime("applyDate", java.time.ZonedDateTime.class);

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus> areWeFriend = createEnum("areWeFriend", com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus.class);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final QMember fromMember;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final ListPath<com.example.socket_jpa_querydsl_test.domain.profile.ProfilePermission, com.example.socket_jpa_querydsl_test.domain.profile.QProfilePermission> profilePermission = this.<com.example.socket_jpa_querydsl_test.domain.profile.ProfilePermission, com.example.socket_jpa_querydsl_test.domain.profile.QProfilePermission>createList("profilePermission", com.example.socket_jpa_querydsl_test.domain.profile.ProfilePermission.class, com.example.socket_jpa_querydsl_test.domain.profile.QProfilePermission.class, PathInits.DIRECT2);

    public final NumberPath<Long> toMemberId = createNumber("toMemberId", Long.class);

    public QFriend(String variable) {
        this(Friend.class, forVariable(variable), INITS);
    }

    public QFriend(Path<? extends Friend> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFriend(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFriend(PathMetadata metadata, PathInits inits) {
        this(Friend.class, metadata, inits);
    }

    public QFriend(Class<? extends Friend> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromMember = inits.isInitialized("fromMember") ? new QMember(forProperty("fromMember"), inits.get("fromMember")) : null;
    }

}

