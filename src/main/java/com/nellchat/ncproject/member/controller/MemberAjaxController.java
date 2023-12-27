package com.nellchat.ncproject.member.controller;

import com.nellchat.ncproject.member.exception.IdDuplicationException;
import com.nellchat.ncproject.member.service.MemberService;
import com.nellchat.ncproject.member.vo.IdDuplicateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberAjaxController {

    private final MemberService memberService;


    @PostMapping(value = "/idCheck")
    public IdDuplicateResult AjaxIdDuplicateCheck(String checkId){
        log.info("UserServiceAjax : 중복확인 동작");
        log.info("UserServiceAjax : 전달 받은 ID 값 = {}", checkId);
        IdDuplicateResult idDuplicateResult;

        try {
            memberService.duplicateCheckId(checkId);
            idDuplicateResult= IdDuplicateResult.PASS;
        } catch (IdDuplicationException e) {
            idDuplicateResult=IdDuplicateResult.NOPASS;
        }
        return idDuplicateResult;

    }

}
