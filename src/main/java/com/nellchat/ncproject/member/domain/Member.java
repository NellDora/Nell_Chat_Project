package com.nellchat.ncproject.member.domain;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name="user")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long number;

    @Column(name = "user_id")
    private String memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String memberName;

    @Column(name = "user_nickname")
    private String memberNickname;

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
    protected Member() {
    }

    public static Member createUser(String memberId, String password, String memberName , String memberNickname , String email){
        Member member = new Member();
        member.memberId = memberId;
        member.password = password;
        member.memberNickname = memberNickname;
        member.memberName = memberName;
        member.email = email;
        return member;
    }

    public void updateInfo(String memberName, String memberNickname, String email){
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.email = email;
    }

    public void passwordEncoding(String encordingPw){
        this.password = encordingPw;
    }

    public void updatePassword(String password){
        this.password=password;
    }
}
