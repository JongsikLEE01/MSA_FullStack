<%@page import="java.util.concurrent.atomic.AtomicInteger"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL - applocation</title>
</head>
<body>
	<h1>EL applocationScope 사용</h1>
	<%
		// applocation 객체에서 접속자 수를 가져오거나 초기화
		// AtomicInteger 사용하여 스레드에 안전한 방식으로 접속자 수를 증가/감소
		AtomicInteger visitrionCount = (AtomicInteger) application.getAttribute("visitrionCount");
		if(visitrionCount == null){
			visitrionCount = new AtomicInteger(0);
			application.setAttribute("visitrionCount", visitrionCount);	
		}
		
		// 접속자 수를 1 증가
		// 세션이 생성될 때 접속자 수를 증가하는 로직으로 변경
		// int currentCount = visitrionCount.incrementAndGet();
	%>
	
	<h1>사이트 접속자 수? ${applicationScope.visitrionCount}</h1>
</body>
</html>