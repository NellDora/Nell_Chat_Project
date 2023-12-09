package com.nellchat.ncproject.publicChat.repository;

import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.PublicChatUser;
import com.nellchat.ncproject.user.domain.User;

public interface PublicChatUserRepository {


    public void save(PublicChatUser publicChatUser);

    public PublicChatUser findById(Long id);

    public PublicChatUser findByUserAndPublicChatRoom(User user, PublicChatRoom publicChatRoom);

}