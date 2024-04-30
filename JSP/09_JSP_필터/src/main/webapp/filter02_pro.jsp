<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인 성공</title>
</head>
<body>
	<%
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
    %>
    <p> 입력된 id 값 : <%=id%>
    <p> 입력된 pw 값 : <%=passwd%>
</body>
</html>