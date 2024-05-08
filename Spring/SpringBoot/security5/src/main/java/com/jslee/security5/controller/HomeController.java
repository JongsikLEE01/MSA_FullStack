package com.jslee.security5.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jslee.security5.Service.UserService;
import com.jslee.security5.dto.Users;


@Slf4j
@Controller
public class HomeController {

    @Autowired
    UserService userService;

    /**
     * 메인 화면
     * @param model
     * @param principal
     * @return
     */
    @GetMapping({"","/"})
    public String home(Model model, Principal principal) {
        // Principal : 현재 로그인한 사용자 정보를 확인하는 인터페이스
        String loginId = principal != null ? principal.getName() : "guest";
        model.addAttribute("loginId", loginId);
        
        log.info(loginId);
        return "index";
    }
    
    /**
     * 인증 예외 화면
     * @param auth
     * @param model
     * @return
     */
    @GetMapping("/exception")
    public String exception(Authentication auth, Model model) {
        log.info("인증 예외 처리...");
        log.info("auth? "+auth.toString());
        model.addAttribute("msg", "인증 거부 : " + auth.toString());
        return "/exception";
    }

    /**
     * 로그인 화면
     * @return
     */
    @GetMapping("/login")
    public String login() {
        log.info("로그인 페이지...");
        return "/login";
    }

    /**
     * 회원 가입
     * @return
     */
    @GetMapping("/join")
    public String join() {
        log.info("회원가입 페이지...");
        return "/join";
    }

    /**
     * 회원가입 처리
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/join")
    public String joinPro(Users user) throws Exception{
        log.info(user.toString());

        int result = userService.join(user);

        if (result == 0) 
            return "redirect:/join";
        
        log.info("회원가입 성공");
        return "redirect:/login";
    }
}