<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>param 액션태그</h1>
	<h3>페이지를 이동하면서 파라미터 전달</h3>
	<!-- 액션태그 param, -->
	<jsp:forward page="detail.jsp">
		<jsp:param value="김조은" name="id"/>
		<jsp:param value="20" name="age"/>
	</jsp:forward>
</body>
</html>