package com.nellchat.ncproject.publicChat.service;

import com.nellchat.ncproject.publicChat.domain.PublicChat;
import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.PublicChatUser;
import com.nellchat.ncproject.publicChat.exception.PublicChatUserDuplicationException;
import com.nellchat.ncproject.publicChat.repository.PublicChatRepository;
import com.nellchat.ncproject.publicChat.repository.PublicChatRoomRepository;
import com.nellchat.ncproject.publicChat.repository.PublicChatUserRepository;
import com.nellchat.ncproject.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CombinePublicChatService {

    private final PublicChatRoomRepository publicChatRoomRepository;
    private final PublicChatUserRepository publicChatUserRepository;

    private final PublicChatRepository publicChatRepository;

    //---------------------------ChatRoom---------------------------------
    @Transactional
    public void saveForPublicChatRoom(PublicChatRoom publicChatRoom){

        //채팅방 등록
        publicChatRoomRepository.save(publicChatRoom);

        //채팅방 등록한 사람 채팅유저로 등록
        PublicChatUser newPublicChatUser =
                PublicChatUser.createPublicChatUser(publicChatRoom, publicChatRoom.getMaster());
        publicChatUserRepository.save(newPublicChatUser);

        //SQLGrammerExceptiond은 RuntimeException을 상속받고 있으므로 문제 발생시 전체 롤백되므로 따로 예외처리 안했음
    }

    public PublicChatRoom findByIdForPublicChatRoom(Long id){
        return publicChatRoomRepository.findById(id);
    }

    public PublicChatRoom findByRoomCodeForPublicChatRoom(String roomCode){
        return publicChatRoomRepository.findByRoomCode(roomCode);
    }

    public List<PublicChatRoom> findByAllForPublicChatRoom(){
        return publicChatRoomRepository.findByAll();
    }

    public List<PublicChatRoom> findByAllWithPagingForPublicChatRoom(int start, int limit){
        return publicChatRoomRepository.findByAllWithPaging(start, limit);
    }

    //---------------------------ChatRoom---------------------------------

    //---------------------------ChatUser---------------------------------

    public void saveForPublicChatUser(PublicChatUser publicChatUser){

        try {
            //유저 저장 전 검증로직
            CheckDuplicationChatUser(publicChatUser.getUser(),publicChatUser.getChatRoom());


            publicChatUserRepository.save(publicChatUser);

        } catch (PublicChatUserDuplicationException e) {
            log.info(e.toString());
        }

    }

    public PublicChatUser findByIdForPublicChatUser(Long id){
        return publicChatUserRepository.findById(id);
    }

    //채팅방내 특정유저 중복 검증
    public void CheckDuplicationChatUser(User user, PublicChatRoom publicChatRoom) throws PublicChatUserDuplicationException {
        if(publicChatUserRepository.findByUserAndPublicChatRoom(user,publicChatRoom)==null){
            log.info("CombinePublicChatService : 중복 검증 문제 없음");
        }else{
            throw new PublicChatUserDuplicationException("이미 해당 채팅방에 존재하는 유저입니다");
        }
    }

    public PublicChatUser findByUserAndPublicChatRoomForPublicChatUser(User user , PublicChatRoom publicChatRoom){
        return publicChatUserRepository.findByUserAndPublicChatRoom(user, publicChatRoom);
    }
    //---------------------------ChatUser---------------------------------



    //---------------------------Chat--------------------------------------
    public void saveForPublicChat(PublicChat publicChat){
        publicChatRepository.save(publicChat);
    }
    //---------------------------Chat--------------------------------------
}

