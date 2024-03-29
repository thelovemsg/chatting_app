package com.example.socket_jpa_querydsl_test.domain.entity.chatting;

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

    private static final long serialVersionUID = -835313417L;

    public static final QChattingRoom chattingRoom = new QChattingRoom("chattingRoom");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.customenum.ChattingRoomType> chattingRoomType = createEnum("chattingRoomType", com.example.socket_jpa_querydsl_test.domain.customenum.ChattingRoomType.class);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final ListPath<Hashtag, QHashtag> hashtags = this.<Hashtag, QHashtag>createList("hashtags", Hashtag.class, QHashtag.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus> isClosed = createEnum("isClosed", com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> roomExpirationDate = createDateTime("roomExpirationDate", java.time.LocalDateTime.class);

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

