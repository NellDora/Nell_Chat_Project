package com.nellchat.ncproject.user.controller;

import com.nellchat.ncproject.user.dto.UserDTO;
import com.nellchat.ncproject.user.exception.IdDuplicationException;
import com.nellchat.ncproject.user.exception.PasswordCheckFailException;
import com.nellchat.ncproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String joinView(Model model)
    {
        model.addAttribute("userDTO", new UserDTO());
        return "/join/join";

    }

    //회원가입 처리
    @PostMapping("/join")
    public String joinAction(@ModelAttribute(value = "userDTO") UserDTO userDTO, HttpServletResponse response) throws IOException {

        log.info("입력 받은 값 = {}, {}, {}, {}",userDTO.getUserId(),userDTO.getPasswordOne(), userDTO.getPasswordTwo());
        //우선 아이디 중복 확인
        int nextProcess = 0;
        try {
            userService.duplicateCheckId(userDTO.getUserId());
        } catch (IdDuplicationException e) {
            log.info("User Controller : 아이디 중복 문제 발생 : ={} ", e);
            nextProcess=1;
            response.setContentType("text/html; charset=utf-8");
            String message = "이미 존재하는 아이디입니다..";
            PrintWriter advise = response.getWriter();
            advise.write("<script>alert('"+message+"');</script>");
            advise.write("<script>history.back();</script>");
            advise.flush();
        }

        if(nextProcess!=1){
            //비밀번호 검증 확인
            try {
                userService.joinPasswordCheck(userDTO.getPasswordOne(), userDTO.getPasswordTwo());
            } catch (PasswordCheckFailException e) {
                log.info("User Controller : 비밀번호 검증 문제 발생 : ={} ", e);
                response.setContentType("text/html; charset=utf-8");
                String message = "비밀번호가 다릅니다.";

                PrintWriter advise = response.getWriter();
                advise.write("<script>alert('"+message+"');</script>");
                advise.write("<script>history.back();</script>");
                advise.flush();
            }
            //userDTO -> user 전환 저장
            userService.save(userDTO);



        }
        return "redirect:/main";
    }

}
