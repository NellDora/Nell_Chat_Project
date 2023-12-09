package com.nellchat.ncproject.publicChat.domain;

import com.nellchat.ncproject.publicChat.vo.ChatState;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "public_chat")
@Getter
@Slf4j
public class PublicChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "public_chat_id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "send_time")
    private Date sendTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "public_chat_user")
    private PublicChatUser publicChatUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "chat_state")
    private ChatState chatState;



    @OneToMany(mappedBy = "lastViewChat")
    private List<PublicChatUser> publicChatUsers = new ArrayList<>();

    protected PublicChat() {
    }

    public static PublicChat createPublicChat(String message, PublicChatUser publicChatUser){
        PublicChat publicChat = new PublicChat();
        publicChat.message = message;
        publicChat.publicChatUser = publicChatUser;
        publicChat.chatState = ChatState.NORMAL;
        publicChat.sendTime = Date.valueOf(LocalDate.now());
        return publicChat;

    }


}