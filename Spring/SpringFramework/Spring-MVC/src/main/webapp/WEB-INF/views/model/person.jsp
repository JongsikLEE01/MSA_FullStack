<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>이름 : ${person.name}</h1>
	<h1>나이 : ${person.age}</h1>
	
	<hr>
	<h3>person</h3>
	<form action="${ pageContext.request.contextPath }/model/person" method="POST">
		<input type="text" name="name" value="${person.name}" /> <br>
		<input type="text" name="age" value="${person.age}" />  <br>
		<input type="submit" value="등록" /> 
	</form>
	
	<hr>
	<h3>person2</h3>
	<form action="${ pageContext.request.contextPath }/model/person2" method="POST">
		<input type="text" name="name" value="${person.name}" /> <br>
		<input type="text" name="age" value="${person.age}" />  <br>
		<input type="submit" value="등록" /> 
	</form>
</body>
</html>