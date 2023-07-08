package com.example.socket_jpa_querydsl_test.domain.entity.chatting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMessage is a Querydsl query type for Message
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMessage extends EntityPathBase<Message> {

    private static final long serialVersionUID = 2070815121L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMessage message = new QMessage("message");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    public final QChattingRoom chattingRoom;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final com.example.socket_jpa_querydsl_test.domain.entity.file.QFile file;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final com.example.socket_jpa_querydsl_test.domain.entity.member.QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final DateTimePath<java.time.ZonedDateTime> sendTime = createDateTime("sendTime", java.time.ZonedDateTime.class);

    public QMessage(String variable) {
        this(Message.class, forVariable(variable), INITS);
    }

    public QMessage(Path<? extends Message> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMessage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMessage(PathMetadata metadata, PathInits inits) {
        this(Message.class, metadata, inits);
    }

    public QMessage(Class<? extends Message> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingRoom = inits.isInitialized("chattingRoom") ? new QChattingRoom(forProperty("chattingRoom")) : null;
        this.file = inits.isInitialized("file") ? new com.example.socket_jpa_querydsl_test.domain.entity.file.QFile(forProperty("file"), inits.get("file")) : null;
        this.member = inits.isInitialized("member") ? new com.example.socket_jpa_querydsl_test.domain.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

