package com.nellchat.ncproject.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityLoginController {

    @GetMapping("/securityLogin")
    public String securityLoginGET(){

        return "/login/login";
    }

    @PostMapping("/securityLoginPro")
    public String securityLoginProPost(){

        return "redirect:/main";
    }
}
