package com.example.socket_jpa_querydsl_test.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberChattingRoom is a Querydsl query type for MemberChattingRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberChattingRoom extends EntityPathBase<MemberChattingRoom> {

    private static final long serialVersionUID = 1912404205L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberChattingRoom memberChattingRoom = new QMemberChattingRoom("memberChattingRoom");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QChattingRoom chattingRoom;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createdDate = _super.createdDate;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath description = createString("description");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.status.FlagStatus> isExpired = createEnum("isExpired", com.example.socket_jpa_querydsl_test.domain.status.FlagStatus.class);

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.status.FlagStatus> isRoomClosed = createEnum("isRoomClosed", com.example.socket_jpa_querydsl_test.domain.status.FlagStatus.class);

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifiedDate = _super.modifiedDate;

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.status.FlagStatus> withdrawalStatus = createEnum("withdrawalStatus", com.example.socket_jpa_querydsl_test.domain.status.FlagStatus.class);

    public QMemberChattingRoom(String variable) {
        this(MemberChattingRoom.class, forVariable(variable), INITS);
    }

    public QMemberChattingRoom(Path<? extends MemberChattingRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberChattingRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberChattingRoom(PathMetadata metadata, PathInits inits) {
        this(MemberChattingRoom.class, metadata, inits);
    }

    public QMemberChattingRoom(Class<? extends MemberChattingRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chattingRoom = inits.isInitialized("chattingRoom") ? new QChattingRoom(forProperty("chattingRoom")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

