package com.jslee.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/pay")
public class HomeController {
    @GetMapping("/payScreen")
    public String moveToPay() {
        return "/pay/payScreen";
    }
    
}
