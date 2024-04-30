<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 아이디 저장, 자동 로그인 쿠키 삭제
	Cookie cookieRememberMe = new Cookie("rememberId", "");
	Cookie cookieToken =  new Cookie("token", "");
	cookieRememberMe.setPath("/");
	cookieToken.setPath("/");
	cookieRememberMe.setMaxAge(0);
	cookieToken.setMaxAge(0);

	// 세션 무효화
	session.invalidate();
	
	// 쿠키 전달
	response.addCookie(cookieRememberMe);
	response.addCookie(cookieToken);

	// 메인으로 이동
	// jsp를 지정해 리다이렉트 방식으로 이동
	response.sendRedirect("index.jsp");
%>