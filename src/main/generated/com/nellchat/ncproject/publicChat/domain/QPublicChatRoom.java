package com.nellchat.ncproject.publicChat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicChatRoom is a Querydsl query type for PublicChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublicChatRoom extends EntityPathBase<PublicChatRoom> {

    private static final long serialVersionUID = -1731900779L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicChatRoom publicChatRoom = new QPublicChatRoom("publicChatRoom");

    public final ListPath<PublicChatUser, QPublicChatUser> chatUserList = this.<PublicChatUser, QPublicChatUser>createList("chatUserList", PublicChatUser.class, QPublicChatUser.class, PathInits.DIRECT2);

    public final DatePath<java.sql.Date> createDate = createDate("createDate", java.sql.Date.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.nellchat.ncproject.member.domain.QMember master;

    public final StringPath password = createString("password");

    public final ListPath<PublicChat, QPublicChat> publicChatList = this.<PublicChat, QPublicChat>createList("publicChatList", PublicChat.class, QPublicChat.class, PathInits.DIRECT2);

    public final StringPath roomCode = createString("roomCode");

    public final StringPath roomName = createString("roomName");

    public final EnumPath<com.nellchat.ncproject.publicChat.vo.RoomType> roomType = createEnum("roomType", com.nellchat.ncproject.publicChat.vo.RoomType.class);

    public QPublicChatRoom(String variable) {
        this(PublicChatRoom.class, forVariable(variable), INITS);
    }

    public QPublicChatRoom(Path<? extends PublicChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicChatRoom(PathMetadata metadata, PathInits inits) {
        this(PublicChatRoom.class, metadata, inits);
    }

    public QPublicChatRoom(Class<? extends PublicChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.master = inits.isInitialized("master") ? new com.nellchat.ncproject.member.domain.QMember(forProperty("master")) : null;
    }

}

