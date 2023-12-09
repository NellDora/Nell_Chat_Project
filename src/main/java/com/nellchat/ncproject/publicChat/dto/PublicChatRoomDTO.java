package com.nellchat.ncproject.publicChat.dto;

import com.nellchat.ncproject.publicChat.vo.JoinState;
import com.nellchat.ncproject.publicChat.vo.RoomType;
import lombok.Data;

import java.sql.Date;

@Data
public class PublicChatRoomDTO {

    private Long id;

    private String roomName;

    private String roomCode;

    private String description;

    private String masterId;

    private String masterName;

    private String masterNickName;

    private RoomType roomType;

    private Date createDate;

    private int numberOfPerson;

    private JoinState joinState;
}

