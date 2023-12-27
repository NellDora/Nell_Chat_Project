package com.nellchat.ncproject.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    private MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService){
        this.memberService = memberService;
    }

    @Test
    void update(){
        memberService.passwordUpdate(1L,"1234");
        memberService.passwordUpdate(2L,"1234");
        memberService.passwordUpdate(3L,"4321");
        memberService.passwordUpdate(4L,"4321");
    }

}