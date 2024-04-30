<%@page import="Calendar.DTO.Calendar"%>
<%@page import="Calendar.Service.CalendarServiceImpl"%>
<%@page import="Calendar.Service.CalendarService"%>
<%@page import="Calendar.MyCalendar"%>
<%@page import="java.util.Date"%>
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
	<title>학사 일정 관리</title>
	<jsp:include page="/layout/link.jsp"/>
	<link href="<%= request.getContextPath()%>/schedule/css/style.css" rel="stylesheet">
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/layout/header.jsp"/>

	<!-- 컨텐츠 -->
	<div class="container">		
		<h1>일정 수정</h1>
		<%
			CalendarService calendarService = new CalendarServiceImpl();
			int no = Integer.parseInt(request.getParameter("no"));
			Calendar cal = calendarService.select(no);
		%>
		<% if(cal != null && cal.getNo() > 0){ %>
		<form name="form" action="<%= request.getContextPath() %>/schedule/schedule_update_pro.jsp" method="post">
			<!-- type를 hidden으로 설정하면 화면에 표시하지않고 값을 넘겨줄 수 있음 -->
			<input type="hidden" id="number" name="no" value="<%= cal.getNo()%>">
			<table border="1">
				<tr>
					<th>시작일</th>
					<td>
						<input type="text" name="strDate" value="<%= cal.getStrDate() %>">
					</td>
				</tr>
				<tr>
					<th>종료일</th>
					<td>
						<input type="text" name="endDate" value="<%= cal.getEndDate() %>">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="5" cols="40" name="content">
							<%= cal.getContent() %>					
						</textarea>
					</td>
				</tr>
			</table>
			<input type="submit" value="수정"/>
			<!-- <button type="button" onclick="deletePro()">삭제</button> -->
		</form>
		<% }else{ %>
			<h3>조회된 게시글이 없습니다.</h3>
		<% } %>
		<div>
			<a href="<%= request.getContextPath() %>/schedule/schedule.jsp">목록</a>
		</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="/layout/footer.jsp"/>

	<!-- 스크립트 -->
</body>
</html>