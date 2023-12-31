package com.nellchat.ncproject.security;

import com.nellchat.ncproject.login.loginConst.LoginSession;
import com.nellchat.ncproject.login.service.LoginService;
import com.nellchat.ncproject.member.domain.Member;
import com.nellchat.ncproject.member.repository.MemberRepository;
import com.nellchat.ncproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final MemberRepository memberRepository;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String loginMember = authentication.getName();
        Member findMember = memberRepository.findById(loginMember);

        HttpSession session = request.getSession();
        session.setAttribute(LoginSession.Login_User, findMember.getNumber());

        log.info("로그인 성공 및 부여된 세션의 번호 : {}" , findMember.getNumber());

        response.sendRedirect("/main");
    }
}
