package com.nellchat.ncproject.login.controller;

import com.nellchat.ncproject.login.dto.LoginDTO;
import com.nellchat.ncproject.login.exception.IdValidationException;
import com.nellchat.ncproject.login.exception.PasswordValidationException;
import com.nellchat.ncproject.login.loginConst.LoginSession;
import com.nellchat.ncproject.login.service.LoginService;
import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.member.service.MemberService;
import jdk.jfr.Label;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@Controller
@Slf4j
public class LoginController {

    private final LoginService loginService;
    private final MemberService memberService;

    public LoginController(LoginService loginService, MemberService memberService){
        this.loginService = loginService;
        this.memberService =memberService;
    }

    @GetMapping("/login")
    public String loginView(Model model){

        model.addAttribute("loginDTO", new LoginDTO());
        return "/login/login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response){
//
//        String view ="";
//
//        try {
//            loginService.loginCheck(loginDTO.getMemberId(),loginDTO.getPassword());
//
//            Member findMember = memberService.findById(loginDTO.getMemberId());
//
//            HttpSession session = request.getSession();
//            session.setAttribute(LoginSession.Login_User, findMember.getNumber());
//
//            log.info("로그인 성공");
//
//        } catch (IdValidationException e) {
//            log.info("로그인 실패 : 아이디 문제");
//            return "redirect:/login";
//        } catch (PasswordValidationException e) {
//            log.info("로그인 실패 : 비밀번호 문제");
//            return "redirect:/login";
//        }
//
//        return "redirect:/main";
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response){
        session.invalidate();
        response.setContentType("text/html; charset=utf-8");
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter writer = response.getWriter();

            String msg = "<script>alert('로그아웃 되었습니다.');</script>";
            msg += "<script>location.href='/main'</script>;";
            writer.print(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
