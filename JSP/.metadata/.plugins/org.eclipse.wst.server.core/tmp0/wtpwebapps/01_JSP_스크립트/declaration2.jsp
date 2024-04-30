<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		int sum(int a, int b){
			return a+b;
		}
	%>
	<!-- html 주석 -->
	<%-- jsp 주석 --%>
	<%-- out : jsp 출력을 하는 내장 객체 --%>
	<h1><% out.print("a+b? "+sum(2,3)); %></h1>
</body>
</html>