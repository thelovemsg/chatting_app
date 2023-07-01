package com.example.socket_jpa_querydsl_test.domain.profile;

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

    private static final long serialVersionUID = -1121611337L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfileConn profileConn = new QProfileConn("profileConn");

    public final com.example.socket_jpa_querydsl_test.domain.entity.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final com.example.socket_jpa_querydsl_test.domain.entity.QFriend friend;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final QProfile profile;

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
        this.friend = inits.isInitialized("friend") ? new com.example.socket_jpa_querydsl_test.domain.entity.QFriend(forProperty("friend"), inits.get("friend")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile"), inits.get("profile")) : null;
    }

}

