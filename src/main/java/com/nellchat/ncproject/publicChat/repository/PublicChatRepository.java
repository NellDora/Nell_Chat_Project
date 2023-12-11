package com.nellchat.ncproject.publicChat.repository;

import com.nellchat.ncproject.publicChat.domain.PublicChat;

import java.util.List;

public interface PublicChatRepository {

    public void save(PublicChat publicChat);

    public List<PublicChat> findByChatroomId(Long id);
}