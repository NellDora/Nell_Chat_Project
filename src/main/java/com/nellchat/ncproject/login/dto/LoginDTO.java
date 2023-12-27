package com.nellchat.ncproject.login.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String memberId;
    private String password;

    public LoginDTO() {
    }
}
