package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QHashtag is a Querydsl query type for Hashtag
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashtag extends EntityPathBase<Hashtag> {

    private static final long serialVersionUID = -1643443686L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QHashtag hashtag = new QHashtag("hashtag");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QChattingRoom chattingRoom;

    public final StringPath content = createString("content");

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

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> seq = createNumber("seq", Integer.class);

    public QHashtag(String variable) {
        this(Hashtag.class, forVariable(variable), INITS);
    }

    public QHashtag(Path<? extends Hashtag> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QHashtag(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QHashtag(PathMetadata metadata, PathInits inits) {
        this(Hashtag.class, metadata, inits);
    }

    public QHashtag(Class<? extends Hashtag> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingRoom = inits.isInitialized("chattingRoom") ? new QChattingRoom(forProperty("chattingRoom")) : null;
    }

}

