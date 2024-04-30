package com.jslee.springnote.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@RequestMapping("/rest")
public class TestRestController {
    
    /**
     * ★ @RestController = @Controller + @ResponseBody
     * - ResponseBody가 없어도, 데이터 응답 본문(body)에 담아서 전송
     * @return
     */
    @GetMapping("/body")
    public String restBody() {
        log.info("GET - /rest/body");
        return "REST";
    }
    
}
