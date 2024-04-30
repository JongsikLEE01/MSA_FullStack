<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>응답 상태 코드</title>
</head>
<body>
	<% 
		// 404 : 페이지x
		// 500 : 서버에러
		// response.sendError(404, "요청페이지 찾을 수 없음");
		response.sendError(500, "서버 내부 오류 발생...");
	%>
</body>
</html>