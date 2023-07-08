package com.example.socket_jpa_querydsl_test.domain.entity.chatting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNotificationHistory is a Querydsl query type for NotificationHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNotificationHistory extends EntityPathBase<NotificationHistory> {

    private static final long serialVersionUID = -2117843021L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QNotificationHistory notificationHistory = new QNotificationHistory("notificationHistory");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final com.example.socket_jpa_querydsl_test.domain.entity.member.QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final EnumPath<com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus> notiEnabled = createEnum("notiEnabled", com.example.socket_jpa_querydsl_test.domain.customenum.FlagStatus.class);

    public QNotificationHistory(String variable) {
        this(NotificationHistory.class, forVariable(variable), INITS);
    }

    public QNotificationHistory(Path<? extends NotificationHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QNotificationHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QNotificationHistory(PathMetadata metadata, PathInits inits) {
        this(NotificationHistory.class, metadata, inits);
    }

    public QNotificationHistory(Class<? extends NotificationHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.socket_jpa_querydsl_test.domain.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

