package com.nellchat.ncproject.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    private UserService userService;

    @Autowired
    public UserServiceTest(UserService userService){
        this.userService = userService;
    }

    @Test
    void update(){
        userService.passwordUpdate(1L,"1234");
        userService.passwordUpdate(2L,"1234");
        userService.passwordUpdate(3L,"4321");
        userService.passwordUpdate(4L,"4321");
    }

}