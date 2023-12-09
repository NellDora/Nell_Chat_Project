package com.nellchat.ncproject.publicChat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPublicChat is a Querydsl query type for PublicChat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPublicChat extends EntityPathBase<PublicChat> {

    private static final long serialVersionUID = -1643302246L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPublicChat publicChat = new QPublicChat("publicChat");

    public final EnumPath<com.nellchat.ncproject.publicChat.vo.ChatState> chatState = createEnum("chatState", com.nellchat.ncproject.publicChat.vo.ChatState.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final QPublicChatUser publicChatUser;

    public final ListPath<PublicChatUser, QPublicChatUser> publicChatUsers = this.<PublicChatUser, QPublicChatUser>createList("publicChatUsers", PublicChatUser.class, QPublicChatUser.class, PathInits.DIRECT2);

    public final DatePath<java.sql.Date> sendTime = createDate("sendTime", java.sql.Date.class);

    public QPublicChat(String variable) {
        this(PublicChat.class, forVariable(variable), INITS);
    }

    public QPublicChat(Path<? extends PublicChat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPublicChat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPublicChat(PathMetadata metadata, PathInits inits) {
        this(PublicChat.class, metadata, inits);
    }

    public QPublicChat(Class<? extends PublicChat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.publicChatUser = inits.isInitialized("publicChatUser") ? new QPublicChatUser(forProperty("publicChatUser"), inits.get("publicChatUser")) : null;
    }

}

