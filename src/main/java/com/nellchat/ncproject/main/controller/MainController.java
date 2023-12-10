package com.nellchat.ncproject.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/main")
    public String main(){

        return "/main/main";
    }

    @GetMapping("/main/info")
    public String info(){

        return "/info/info";
    }
}