package com.nellchat.ncproject.publicChat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicChatUser is a Querydsl query type for PublicChatUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublicChatUser extends EntityPathBase<PublicChatUser> {

    private static final long serialVersionUID = -1731807867L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicChatUser publicChatUser = new QPublicChatUser("publicChatUser");

    public final QPublicChatRoom chatRoom;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPublicChat lastViewChat;

    public final ListPath<PublicChat, QPublicChat> publicChats = this.<PublicChat, QPublicChat>createList("publicChats", PublicChat.class, QPublicChat.class, PathInits.DIRECT2);

    public final com.nellchat.ncproject.user.domain.QUser user;

    public QPublicChatUser(String variable) {
        this(PublicChatUser.class, forVariable(variable), INITS);
    }

    public QPublicChatUser(Path<? extends PublicChatUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicChatUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicChatUser(PathMetadata metadata, PathInits inits) {
        this(PublicChatUser.class, metadata, inits);
    }

    public QPublicChatUser(Class<? extends PublicChatUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QPublicChatRoom(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.lastViewChat = inits.isInitialized("lastViewChat") ? new QPublicChat(forProperty("lastViewChat"), inits.get("lastViewChat")) : null;
        this.user = inits.isInitialized("user") ? new com.nellchat.ncproject.user.domain.QUser(forProperty("user")) : null;
    }

}

