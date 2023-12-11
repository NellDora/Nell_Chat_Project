package com.nellchat.ncproject.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long number;

    private String userId;

    private String userNickname;

    private String passwordOne;

    private String passwordTwo;

    private String userName;

    private String email;
}
