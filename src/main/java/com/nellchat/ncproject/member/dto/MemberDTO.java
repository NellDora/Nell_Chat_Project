package com.nellchat.ncproject.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
    private Long number;

    private String memberId;

    private String memberNickname;

    private String passwordOne;

    private String passwordTwo;

    private String memberName;

    private String email;
}
