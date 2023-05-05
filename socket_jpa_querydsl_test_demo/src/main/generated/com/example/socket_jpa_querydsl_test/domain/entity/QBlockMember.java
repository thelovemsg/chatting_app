package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBlockMember is a Querydsl query type for BlockMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBlockMember extends EntityPathBase<BlockMember> {

    private static final long serialVersionUID = -636070699L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBlockMember blockMember = new QBlockMember("blockMember");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> blockDate = createDateTime("blockDate", java.time.LocalDateTime.class);

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

    public final QMember memberTarget;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public QBlockMember(String variable) {
        this(BlockMember.class, forVariable(variable), INITS);
    }

    public QBlockMember(Path<? extends BlockMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBlockMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBlockMember(PathMetadata metadata, PathInits inits) {
        this(BlockMember.class, metadata, inits);
    }

    public QBlockMember(Class<? extends BlockMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.memberTarget = inits.isInitialized("memberTarget") ? new QMember(forProperty("memberTarget"), inits.get("memberTarget")) : null;
    }

}

