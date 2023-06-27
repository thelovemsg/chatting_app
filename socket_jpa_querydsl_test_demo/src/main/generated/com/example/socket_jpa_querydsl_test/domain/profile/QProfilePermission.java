package com.example.socket_jpa_querydsl_test.domain.profile;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfilePermission is a Querydsl query type for ProfilePermission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfilePermission extends EntityPathBase<ProfilePermission> {

    private static final long serialVersionUID = -2031266790L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfilePermission profilePermission = new QProfilePermission("profilePermission");

    public final com.example.socket_jpa_querydsl_test.domain.entity.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final com.example.socket_jpa_querydsl_test.domain.entity.QFriend friend;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final QProfile profile;

    public QProfilePermission(String variable) {
        this(ProfilePermission.class, forVariable(variable), INITS);
    }

    public QProfilePermission(Path<? extends ProfilePermission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfilePermission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfilePermission(PathMetadata metadata, PathInits inits) {
        this(ProfilePermission.class, metadata, inits);
    }

    public QProfilePermission(Class<? extends ProfilePermission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.friend = inits.isInitialized("friend") ? new com.example.socket_jpa_querydsl_test.domain.entity.QFriend(forProperty("friend"), inits.get("friend")) : null;
        this.profile = inits.isInitialized("profile") ? new QProfile(forProperty("profile"), inits.get("profile")) : null;
    }

}

