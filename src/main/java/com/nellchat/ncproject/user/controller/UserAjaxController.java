package com.nellchat.ncproject.user.controller;

import com.nellchat.ncproject.user.exception.IdDuplicationException;
import com.nellchat.ncproject.user.service.UserService;
import com.nellchat.ncproject.user.vo.IdDuplicateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserAjaxController {

    private final UserService userService;

    @ResponseBody
    @PostMapping(value = "/idCheck")
    public IdDuplicateResult AjaxIdDuplicateCheck(String checkId){
        log.info("UserServiceAjax : 중복확인 동작");
        log.info("UserServiceAjax : 전달 받은 ID 값 = {}", checkId);
        IdDuplicateResult idDuplicateResult;

        try {
            userService.duplicateCheckId(checkId);
            idDuplicateResult= IdDuplicateResult.PASS;
        } catch (IdDuplicationException e) {
            idDuplicateResult=IdDuplicateResult.NOPASS;
        }
        return idDuplicateResult;

    }

}
