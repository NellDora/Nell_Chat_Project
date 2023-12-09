package com.nellchat.ncproject.login.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String userId;
    private String password;

    public LoginDTO() {
    }
}
