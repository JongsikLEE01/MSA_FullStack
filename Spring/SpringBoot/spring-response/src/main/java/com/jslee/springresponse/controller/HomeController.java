package com.jslee.springresponse.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Controller
 * String MVC의 HTTP 요청에 응답하는 컨트롤러 클래스로 지정하는 어노테이션
 * - 컨트롤러 메소드로 주로 View를 반환
 */
@Slf4j      // 로그 사용 어노테이션
@Controller
public class HomeController{

    /**
     * void
     * - URL과 동일한 경로의 뷰를 응답
     */
    @GetMapping("/login")
    public void login() {
        log.info("GET - /login - 로그인 페이지");
    }
    
    /**
     * String
     * - 문자열로 응답할 뷰 경로를 지정
     * @return
     */
    @ResponseBody
    @GetMapping("/hello")   // {"/A","/B"} - 여러 경로 매핑
    public String home() {
        log.info("GET - /hello - 뷰가 아닌 문자열 본문을 응답");
        return "Hello Spring Boot";
    }
}