package com.nellchat.ncproject.publicChat.domain;

import com.nellchat.ncproject.member.domain.Member;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "public_chatuser")
@Getter
@Slf4j
public class PublicChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "public_chatuser_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "public_chatroom_id")
    private PublicChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "user_number")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "last_view_chat")
    private PublicChat lastViewChat;

    @OneToMany(mappedBy = "publicChatUser")
    private List<PublicChat> publicChats = new ArrayList<>();

    protected PublicChatUser() {
    }

    public static PublicChatUser createPublicChatUser(PublicChatRoom chatRoom, Member member){
        PublicChatUser publicChatUser = new PublicChatUser();
        publicChatUser.chatRoom = chatRoom;
        publicChatUser.member = member;
        return publicChatUser;
    }
}

