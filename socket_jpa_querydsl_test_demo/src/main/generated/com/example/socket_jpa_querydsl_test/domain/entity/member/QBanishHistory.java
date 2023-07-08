package com.example.socket_jpa_querydsl_test.domain.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBanishHistory is a Querydsl query type for BanishHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBanishHistory extends EntityPathBase<BanishHistory> {

    private static final long serialVersionUID = 179501923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBanishHistory banishHistory = new QBanishHistory("banishHistory");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> banishDatetime = createDateTime("banishDatetime", java.time.LocalDateTime.class);

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

    public final QMemberChattingRoom memberChattingRoom;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public QBanishHistory(String variable) {
        this(BanishHistory.class, forVariable(variable), INITS);
    }

    public QBanishHistory(Path<? extends BanishHistory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBanishHistory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBanishHistory(PathMetadata metadata, PathInits inits) {
        this(BanishHistory.class, metadata, inits);
    }

    public QBanishHistory(Class<? extends BanishHistory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.memberChattingRoom = inits.isInitialized("memberChattingRoom") ? new QMemberChattingRoom(forProperty("memberChattingRoom"), inits.get("memberChattingRoom")) : null;
    }

}

