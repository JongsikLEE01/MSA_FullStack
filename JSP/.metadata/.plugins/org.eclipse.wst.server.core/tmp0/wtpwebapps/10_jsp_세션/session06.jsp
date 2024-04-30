<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 유효 여부 확인 및 무효화</title>
</head>
<body>
		<p><h4>---- 세션 무효화 전 ----</h4></p>
    <%
        String user_id = (String) session.getAttribute("userID");
    	String user_pw = (String) session.getAttribute("userPW");
    	out.println("설정된 세션 속성 userID? "+user_id+"<br>");
    	out.println("설정된 세션 속성 userPW? "+user_pw+"<br>");
    	
    	// isRequestedSessionIdValid : 세션 아이디가 유효한지 확인
    	if(request.isRequestedSessionIdValid())
    		out.println("세션이 유효함...");
    	else
    		out.println("세션이 유효 하지않음...");
    	
    	session.invalidate();	// 세션 무효화 - 모든 속성을 제거
    %>
    <p><h4>---- 세션 무효화 후 ----</h4></p>
    <%
    	if(request.isRequestedSessionIdValid())
			out.println("세션이 유효함...");
		else
			out.println("세션이 유효 하지않음...");
    %>
</body>
</html>