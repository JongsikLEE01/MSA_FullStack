<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 유효 여부 확인 및 무효화</title>
</head>
<body>
		<p><h4>---- 세션 유효 시간 변경 전 ----</h4></p>
    <%
    	// getMaxInactiveInterval : 세션 유효 시간 반환
        int time = session.getMaxInactiveInterval() / 60;
    
    	out.println("세션 유효 시간? "+time+"분<br>");
    %>
    <p><h4>---- 세션 유효 시간 변경 후 ----</h4></p>
    <%
    	// setMaxInactiveInterval : 초 단위로 세션의 유효 시간을 설정
    	session.setMaxInactiveInterval(60 * 60);
    	time = session.getMaxInactiveInterval() / 60;
    	
    	out.println("세션 유효 시간? "+time+"분<br>");
    %>
</body>
</html>