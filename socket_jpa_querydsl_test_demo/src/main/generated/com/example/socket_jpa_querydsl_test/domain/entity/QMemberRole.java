package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRole is a Querydsl query type for MemberRole
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRole extends EntityPathBase<MemberRole> {

    private static final long serialVersionUID = -137361118L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRole memberRole = new QMemberRole("memberRole");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<RoleEnum> roleEnum = createEnum("roleEnum", RoleEnum.class);

    public QMemberRole(String variable) {
        this(MemberRole.class, forVariable(variable), INITS);
    }

    public QMemberRole(Path<? extends MemberRole> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRole(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRole(PathMetadata metadata, PathInits inits) {
        this(MemberRole.class, metadata, inits);
    }

    public QMemberRole(Class<? extends MemberRole> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

