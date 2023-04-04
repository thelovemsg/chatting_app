package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChattingRoom is a Querydsl query type for ChattingRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChattingRoom extends EntityPathBase<ChattingRoom> {

    private static final long serialVersionUID = 410209971L;

    public static final QChattingRoom chattingRoom = new QChattingRoom("chattingRoom");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<Hashtag, com.example.socket_jpa_querydsl_test.domain.QHashtag> hashtags = this.<Hashtag, com.example.socket_jpa_querydsl_test.domain.QHashtag>createList("hashtags", Hashtag.class, com.example.socket_jpa_querydsl_test.domain.QHashtag.class, PathInits.DIRECT2);

    public final StringPath id = createString("id");

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public QChattingRoom(String variable) {
        super(ChattingRoom.class, forVariable(variable));
    }

    public QChattingRoom(Path<? extends ChattingRoom> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChattingRoom(PathMetadata metadata) {
        super(ChattingRoom.class, metadata);
    }

}

