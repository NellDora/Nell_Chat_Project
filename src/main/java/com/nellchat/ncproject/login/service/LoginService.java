package com.nellchat.ncproject.login.service;

import com.nellchat.ncproject.login.exception.IdValidationException;
import com.nellchat.ncproject.login.exception.PasswordValidationException;
import com.nellchat.ncproject.user.domain.User;
import com.nellchat.ncproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public void loginCheck(String loginId, String password) throws IdValidationException, PasswordValidationException {

        if(userService.findById(loginId)==null){
            throw new IdValidationException("LoginService : 존재하지 않는 ID 입니다.");
        }else{
            User findUser = userService.findById(loginId);
            if(passwordEncoder.matches(password,findUser.getPassword())){
                log.info("LoginService : 로그인 유저 검증 성공");
            }else {
                throw new PasswordValidationException("LoginService : 입력한 비밀번호가 다름");
            }
        }

    }
}