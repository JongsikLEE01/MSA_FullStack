package com.jslee.security5.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomeController {
    @GetMapping({"","/"})
    public String home(Model model, Principal principal) {
        // Principal : 현재 로그인한 사용자 정보를 확인하는 인터페이스
        String loginId = principal != null ? principal.getName() : "guest";
        model.addAttribute("loginId", loginId);
        
        log.info(loginId);
        return "index";
    }
}