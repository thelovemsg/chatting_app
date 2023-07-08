package com.example.socket_jpa_querydsl_test.domain.entity.file;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFile is a Querydsl query type for File
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFile extends EntityPathBase<File> {

    private static final long serialVersionUID = 1798213052L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFile file = new QFile("file");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final DateTimePath<java.time.ZonedDateTime> deleteTime = createDateTime("deleteTime", java.time.ZonedDateTime.class);

    public final StringPath description = createString("description");

    public final StringPath fileName = createString("fileName");

    public final NumberPath<Long> fileSize = createNumber("fileSize", Long.class);

    public final StringPath fileType = createString("fileType");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final com.example.socket_jpa_querydsl_test.domain.entity.member.QMember member;

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final StringPath storedFileName = createString("storedFileName");

    public final DateTimePath<java.time.ZonedDateTime> uploadTime = createDateTime("uploadTime", java.time.ZonedDateTime.class);

    public QFile(String variable) {
        this(File.class, forVariable(variable), INITS);
    }

    public QFile(Path<? extends File> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFile(PathMetadata metadata, PathInits inits) {
        this(File.class, metadata, inits);
    }

    public QFile(Class<? extends File> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.socket_jpa_querydsl_test.domain.entity.member.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

