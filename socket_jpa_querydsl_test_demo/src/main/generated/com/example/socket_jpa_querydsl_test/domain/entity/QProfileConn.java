package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfileConn is a Querydsl query type for ProfileConn
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfileConn extends EntityPathBase<ProfileConn> {

    private static final long serialVersionUID = -1157945405L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfileConn profileConn = new QProfileConn("profileConn");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final QFriend friend;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final com.example.socket_jpa_querydsl_test.domain.profile.QProfile profile;

    public QProfileConn(String variable) {
        this(ProfileConn.class, forVariable(variable), INITS);
    }

    public QProfileConn(Path<? extends ProfileConn> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfileConn(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfileConn(PathMetadata metadata, PathInits inits) {
        this(ProfileConn.class, metadata, inits);
    }

    public QProfileConn(Class<? extends ProfileConn> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend = inits.isInitialized("friend") ? new QFriend(forProperty("friend"), inits.get("friend")) : null;
        this.profile = inits.isInitialized("profile") ? new com.example.socket_jpa_querydsl_test.domain.profile.QProfile(forProperty("profile"), inits.get("profile")) : null;
    }

}

