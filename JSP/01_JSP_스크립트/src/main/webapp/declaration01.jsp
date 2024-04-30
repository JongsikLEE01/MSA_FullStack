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
		String makeUpperCase(String a){
			
			return a.toUpperCase();
		}
	%>
	<!-- html 주석 -->
	<%-- jsp 주석 --%>
	<%-- out : jsp 출력을 하는 내장 객체 --%>
	<h1><% out.print(makeUpperCase("hello jsp?")); %></h1>
</body>
</html>