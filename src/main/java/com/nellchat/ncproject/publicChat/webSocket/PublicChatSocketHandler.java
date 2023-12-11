package com.nellchat.ncproject.publicChat.webSocket;

import com.nellchat.ncproject.publicChat.domain.PublicChat;
import com.nellchat.ncproject.publicChat.domain.PublicChatRoom;
import com.nellchat.ncproject.publicChat.domain.PublicChatUser;
import com.nellchat.ncproject.publicChat.service.CombinePublicChatService;
import com.nellchat.ncproject.user.domain.User;
import com.nellchat.ncproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublicChatSocketHandler extends TextWebSocketHandler {

    private final UserService userService;
    private final CombinePublicChatService combinePublicChatService;

    Map<String, Map<String, WebSocketSession>> chatRoomSession = new HashMap<>();

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("PublicChatSocketHandler 웹소켓 연결 성공");
        log.info("접속 session의 uri 값 : " +session.getUri().toString());

        String roomCode = extURI(session.getUri().toString());
        log.info("추출한 룸코드 : {}", roomCode);

        if(chatRoomSession.get(roomCode)==null){
            Map<String , WebSocketSession> userSession = new HashMap<>();
            userSession.put(session.getId(), session);
            chatRoomSession.put(roomCode,userSession);
        }else{
            Map<String, WebSocketSession> userSession =  chatRoomSession.get(roomCode);
            userSession.put(session.getId(), session);
            chatRoomSession.put(roomCode,userSession);
        }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("입력받은 meesage의 값 : {}", message.getPayload());
        String all = message.getPayload();
        int pos = message.getPayload().indexOf(":");

        //유저 번호 추출
        Long userNum = Long.parseLong(message.getPayload().substring(0,pos));
        String strUsrNum=message.getPayload().substring(0,pos);

        //유저가 작성한 메세지 추출
        String sendMessage = message.getPayload().substring(pos+1);

        log.info("입력한 유저의 유저번호는 : {}" , strUsrNum );
        log.info("입력한 메세지의 내용은 = {}" , sendMessage);
        //룸 코드
        String roomCode = extURI(session.getUri().toString());

        //룸코드로 채팅방 조회
        PublicChatRoom findPublicChatRoom = combinePublicChatService.findByRoomCodeForPublicChatRoom(roomCode);

        User findUser = userService.findByNumber(userNum);

        PublicChatUser findByPublicChatUser =
                combinePublicChatService.findByUserAndPublicChatRoomForPublicChatUser(findUser, findPublicChatRoom);

        PublicChat publicChat = PublicChat.createPublicChat(sendMessage,findByPublicChatUser,findPublicChatRoom);

        combinePublicChatService.saveForPublicChat(publicChat);
        //---------------------해당하는 room에 메세지 전송--------------------

        Map<String, WebSocketSession> userSession = chatRoomSession.get(roomCode);

        String pullMessage = findUser.getUserNickname()+" : " + sendMessage;

        for(String key : userSession.keySet()){
            WebSocketSession miniSession = userSession.get(key);
            miniSession.sendMessage(new TextMessage(pullMessage));
        }


    }

    //Session에서 URI 추출
    private String extURI(String allURI){
        int pos = allURI.lastIndexOf("/");
        String roomCode = allURI.substring(pos+1);
        return roomCode;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
