package com.nellchat.ncproject.member.controller;

import com.nellchat.ncproject.member.dto.MemberDTO;
import com.nellchat.ncproject.member.exception.IdDuplicationException;
import com.nellchat.ncproject.member.exception.PasswordCheckFailException;
import com.nellchat.ncproject.member.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinView(Model model)
    {
        model.addAttribute("userDTO", new MemberDTO());
        return "/join/join";

    }

    //회원가입 처리
    @PostMapping("/join")
    public String joinAction(@ModelAttribute(value = "userDTO") MemberDTO memberDTO, HttpServletResponse response) throws IOException {

        log.info("입력 받은 값 = {}, {}, {}, {}", memberDTO.getMemberId(), memberDTO.getPasswordOne(), memberDTO.getPasswordTwo());
        //우선 아이디 중복 확인
        int nextProcess = 0;
        try {
            memberService.duplicateCheckId(memberDTO.getMemberId());
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
                memberService.joinPasswordCheck(memberDTO.getPasswordOne(), memberDTO.getPasswordTwo());
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
            memberService.save(memberDTO);



        }
        return "redirect:/main";
    }

}
