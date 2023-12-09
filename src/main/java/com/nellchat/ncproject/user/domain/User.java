package com.nellchat.ncproject.user.domain;


import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long number;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nickname")
    private String userNickname;

    private String email;

//
//    @OneToMany(mappedBy = "master")
//    private List<PublicChatRoom> publicChatRooms = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user")
//    private List<PublicChatUser> publicChatUsers = new ArrayList<>();
//
//    @OneToMany(mappedBy = "relationUser")
//    private List<Friend> friends = new ArrayList<>();
    protected User() {
    }

    public static User createUser(String userId, String password, String userName ,String userNickname ,String email){
        User user = new User();
        user.userId = userId;
        user.password = password;
        user.userNickname = userNickname;
        user.userName = userName;
        user.email = email;
        return  user;
    }

    public void updateInfo(String userName, String userNickname, String email){
        this.userName = userName;
        this.userNickname = userNickname;
        this.email = email;
    }
}
