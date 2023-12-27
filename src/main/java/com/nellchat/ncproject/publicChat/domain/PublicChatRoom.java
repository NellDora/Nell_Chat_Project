package com.nellchat.ncproject.publicChat.domain;

import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.publicChat.vo.RoomType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "public_chatroom")
@Slf4j
@Getter
public class PublicChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "public_chatroom_id")
    private Long id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "room_code")
    private String roomCode;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="master")
    private Member master;

    @Column(name = "create_date")
    private Date createDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "password")
    private String password;

    @Transient
    private Map<Member, WebSocketSession> chatRoomSession = new ConcurrentHashMap<>();

    @OneToMany(mappedBy = "chatRoom")
    List<PublicChatUser> chatUserList = new ArrayList<>();

    @OneToMany(mappedBy = "publicChatRoom")
    List<PublicChat> publicChatList = new ArrayList<>();

    protected PublicChatRoom() {
    }

    public static PublicChatRoom createPublicChatRoom(String roomName, String description, Member master, RoomType roomType, String password){
        PublicChatRoom chatRoom = new PublicChatRoom();
        chatRoom.roomName = roomName;
        chatRoom.description=description;
        chatRoom.master =master;
        chatRoom.roomCode = createRoomCode();
        chatRoom.roomType = roomType;
        chatRoom.password = password;
        return chatRoom;
    }

    protected static String createRoomCode(){
        return UUID.randomUUID().toString();
    }




}
