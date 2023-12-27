package com.nellchat.ncproject.publicChat.repository;

import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.PublicChatUser;

public interface PublicChatUserRepository {


    public void save(PublicChatUser publicChatUser);

    public PublicChatUser findById(Long id);

    public PublicChatUser findByUserAndPublicChatRoom(Member member, PublicChatRoom publicChatRoom);

}