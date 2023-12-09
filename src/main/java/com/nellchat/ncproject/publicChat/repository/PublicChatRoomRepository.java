package com.nellchat.ncproject.publicChat.repository;

import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;

import java.util.List;

public interface PublicChatRoomRepository{

    public void save(PublicChatRoom publicChatRoom);

    public PublicChatRoom findById(Long id);

    public PublicChatRoom findByRoomCode(String roomCode);

    public List<PublicChatRoom> findByAll();

    public List<PublicChatRoom> findByAllWithPaging(int start, int limit);
}
