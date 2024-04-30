<%@page import="shop.DTO.Board"%>
<%@page import="shop.Service.BoardServiceImpl"%>
<%@page import="shop.Service.BoardService"%>
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
<title>게시글 등록</title>
</head>
<body>
	<h1>게시글 등록</h1>
	<form action="<%= request.getContextPath() %>/board/insert_pro.jsp" method="post">
		<!-- type를 hidden으로 설정하면 화면에 표시하지않고 값을 넘겨줄 수 있음 -->
		<input type="hidden" name="no" value="">
		<table border="1">
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" value="">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="userId" value="">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="5" cols="40" name="content">					
					</textarea>
				</td>
			</tr>
		</table>
		<input type="submit" value="등록"/>
	</form>
	<div>
		<a href="<%= request.getContextPath() %>/board/list.jsp">목록</a>
	</div>
</body>
</html>