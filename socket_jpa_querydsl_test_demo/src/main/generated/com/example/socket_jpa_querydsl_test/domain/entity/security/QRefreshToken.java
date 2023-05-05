package com.example.socket_jpa_querydsl_test.domain.entity.security;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRefreshToken is a Querydsl query type for RefreshToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRefreshToken extends EntityPathBase<RefreshToken> {

    private static final long serialVersionUID = -580815398L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRefreshToken refreshToken = new QRefreshToken("refreshToken");

    public final com.example.socket_jpa_querydsl_test.domain.entity.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final DateTimePath<java.time.Instant> expiryDate = createDateTime("expiryDate", java.time.Instant.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final com.example.socket_jpa_querydsl_test.domain.entity.QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath token = createString("token");

    public QRefreshToken(String variable) {
        this(RefreshToken.class, forVariable(variable), INITS);
    }

    public QRefreshToken(Path<? extends RefreshToken> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRefreshToken(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRefreshToken(PathMetadata metadata, PathInits inits) {
        this(RefreshToken.class, metadata, inits);
    }

    public QRefreshToken(Class<? extends RefreshToken> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.socket_jpa_querydsl_test.domain.entity.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

