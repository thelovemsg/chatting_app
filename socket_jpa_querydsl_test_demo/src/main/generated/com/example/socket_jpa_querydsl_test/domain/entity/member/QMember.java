package com.example.socket_jpa_querydsl_test.domain.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1609890940L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity _super = new com.example.socket_jpa_querydsl_test.domain.entity.base.QBaseEntity(this);

    public final ListPath<Address, QAddress> addresses = this.<Address, QAddress>createList("addresses", Address.class, QAddress.class, PathInits.DIRECT2);

    public final ListPath<BlockMember, QBlockMember> blockedMembers = this.<BlockMember, QBlockMember>createList("blockedMembers", BlockMember.class, QBlockMember.class, PathInits.DIRECT2);

    public final ListPath<BlockMember, QBlockMember> blockingMembers = this.<BlockMember, QBlockMember>createList("blockingMembers", BlockMember.class, QBlockMember.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> createDate = _super.createDate;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    //inherited
    public final BooleanPath deleted = _super.deleted;

    public final StringPath email = createString("email");

    public final ListPath<com.example.socket_jpa_querydsl_test.domain.entity.file.File, com.example.socket_jpa_querydsl_test.domain.entity.file.QFile> files = this.<com.example.socket_jpa_querydsl_test.domain.entity.file.File, com.example.socket_jpa_querydsl_test.domain.entity.file.QFile>createList("files", com.example.socket_jpa_querydsl_test.domain.entity.file.File.class, com.example.socket_jpa_querydsl_test.domain.entity.file.QFile.class, PathInits.DIRECT2);

    public final ListPath<com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend, com.example.socket_jpa_querydsl_test.domain.entity.chatting.QFriend> fromFriends = this.<com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend, com.example.socket_jpa_querydsl_test.domain.entity.chatting.QFriend>createList("fromFriends", com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend.class, com.example.socket_jpa_querydsl_test.domain.entity.chatting.QFriend.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath lastModifiedBy = _super.lastModifiedBy;

    public final StringPath memberIndividualId = createString("memberIndividualId");

    public final ListPath<MemberRole, QMemberRole> memberRoles = this.<MemberRole, QMemberRole>createList("memberRoles", MemberRole.class, QMemberRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.ZonedDateTime> modifyDate = _super.modifyDate;

    public final StringPath name = createString("name");

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final com.example.socket_jpa_querydsl_test.domain.entity.security.QRefreshToken refreshToken;

    public final ListPath<com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend, com.example.socket_jpa_querydsl_test.domain.entity.chatting.QFriend> toFriends = this.<com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend, com.example.socket_jpa_querydsl_test.domain.entity.chatting.QFriend>createList("toFriends", com.example.socket_jpa_querydsl_test.domain.entity.chatting.Friend.class, com.example.socket_jpa_querydsl_test.domain.entity.chatting.QFriend.class, PathInits.DIRECT2);

    public final StringPath userOwnId = createString("userOwnId");

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.refreshToken = inits.isInitialized("refreshToken") ? new com.example.socket_jpa_querydsl_test.domain.entity.security.QRefreshToken(forProperty("refreshToken"), inits.get("refreshToken")) : null;
    }

}

