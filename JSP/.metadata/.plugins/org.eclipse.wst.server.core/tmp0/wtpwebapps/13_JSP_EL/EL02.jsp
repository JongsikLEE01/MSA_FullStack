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
<title>EL - 내장 객체</title>
</head>
<body>
	<h1>EL - 내장 객체</h1>
	<h2>request Scope 속성? ${requestScope.name}</h2>
	<% 
		// 현재 페이지 범위
		pageContext.setAttribute("pname", "page");
		// 다음 페이지로 전달할 수 있는  범위
		request.setAttribute("name", "김조은");
		request.getRequestDispatcher("EL02_pro.jsp").forward(request, response);
	%>
</body>
</html>