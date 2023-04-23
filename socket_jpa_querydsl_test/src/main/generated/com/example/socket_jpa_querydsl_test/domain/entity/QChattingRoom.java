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

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath flag = _super.flag;

    public final ListPath<Hashtag, QHashtag> hashtags = this.<Hashtag, QHashtag>createList("hashtags", Hashtag.class, QHashtag.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus> status = createEnum("status", com.example.socket_jpa_querydsl_test.domain.status.ChattingRoomStatus.class);

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

