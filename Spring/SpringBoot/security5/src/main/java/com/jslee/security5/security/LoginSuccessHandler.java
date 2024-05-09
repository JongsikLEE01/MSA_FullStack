package com.jslee.security5.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.jslee.security5.dto.CustomUser;

import lombok.extern.slf4j.Slf4j;

/**
 * ✔ 로그인 성공 처리 클래스
 */
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        log.info("로그인 인증 성공...");

        // 아이디 저장
        String remeberId = request.getParameter("remeber-id");  // 아이디 저장 여부
        String username = request.getParameter("id");           // 아이디
        
        // 아이디 저장 체크
        if(remeberId != null && remeberId.equals("on")){
            Cookie cookie = new Cookie("remeber-id", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);      // 유효기간 : 7일
            cookie.setPath("/");                 // 쿠키 적용 경로 지정
            response.addCookie(cookie);              // 응답에 쿠키 등록
        }
        // 아이디 저장 체크 X
        else{
            Cookie cookie = new Cookie("remeber-id", "");
            cookie.setMaxAge(0);              // 유효기간 : 만료
            cookie.setPath("/");                 // 쿠키 적용 경로 지정
            response.addCookie(cookie);              // 응답에 쿠키 등록
        }

        request.getParameter("remember-id");
        request.getParameter("id");

        // 인증된 사용자 정보 - (아이디 / 패스워드 / 권한)
        // User user = (User) authentication.getPrincipal();
        CustomUser user = (CustomUser) authentication.getPrincipal();

        log.info("아이디? "+user.getUsername());
        log.info("비밀번호? "+user.getPassword());  // 보안상 노출 X
        log.info("권한? "+user.getAuthorities());

        super.onAuthenticationSuccess(request, response, authentication);
    }

}