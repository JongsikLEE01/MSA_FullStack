<%@page import="theJoeunUniversity.MyCalendar"%>
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
	<title>학사 일정</title>
	<jsp:include page="/layout/link.jsp"/>
</head>
<body>
	<!-- 헤더 -->
	<jsp:include page="/layout/header.jsp"/>

	<!-- 컨텐츠 -->
	<div class="container">
		<h1>학사 일정</h1>
		<div class="month">
			<a>전체</a>
			<a>1월</a>
			<a>2월</a>
			<a>3월</a>
			<a>4월</a>
			<a>5월</a>
			<a>6월</a>
			<a>7월</a>
			<a>8월</a>
			<a>9월</a>
			<a>10월</a>
			<a>11월</a>
			<a>12월</a>
		</div>
		<div class="calendar">
			<!-- 달력 -->
			<%
				// 컴퓨터 시스템의 년, 월 받아오기
					Date date = new Date();
					int year = date.getYear() +1900;
					int month = date.getMonth() +1;
				
					//	오류사항 걸러주기	
					try{
						year = Integer.parseInt(request.getParameter("year"));
						month = Integer.parseInt(request.getParameter("month"));
						
						if(month>=13){
							year++;
							month =1;
						}else if(month <=0){
							year--;
							month =12;
						}
					}catch(Exception e){
						
					}
				
				%>
				<!-- 달력 만들기 -->
				<table width ="700" align ="center" border ="1" cellpadding="5" cellspacing="0">
					<tr>
				<!-- 이전달 버튼 만들기 -->
						<th>
						<%-- <a href ="?year=<%=year%>&month=<%month-1%>">이전 달</a> --%>
						<input type="button" value="이전 달" onclick="location.href='?year=<%=year%>&month=<%=month-1%>'">
						</th>
						
				<!-- 제목 만들기 -->
						<th id = "title" colspan = "5">
						<p>2024 학사 일정</p>
						</th>
						
				<!-- 다음달 버튼 만들기 -->
						<th>
						<%-- <a href ="?year=<%=year%>&month=<%month+1%>">다음 달</a> --%>
						<input type="button" value="다음 달" onclick="location.href='?year=<%=year%>&month=<%=month+1%>'">
						
						</th>
					</tr>
				<!-- 요일 표시칸 만들어주기(단, 토,일요일은 색을 다르게 하기위해 구분해주기) -->
					<tr>
						<td class = "sunday">일</td>
						<td class = "etcday">월</td>
						<td class = "etcday">화</td>
						<td class = "etcday">수</td>
						<td class = "etcday">목</td>
						<td class = "etcday">금</td>
						<td class = "saturday">토</td>
					</tr>
					
				<!-- 날짜 집어 넣기 -->
					<tr>
					<%
				//	1일의 요일을 계산한다(자주 쓰이기 때문에 변수로 선언해두기)
						int first = MyCalendar.weekDay(year, month, 1);
					
				//	1일이 출력될 위치 전에 전달의 마지막 날짜들을 넣어주기위해 전 달날짜의 시작일을 계산한다.
						int start = 0 ;
						start = month ==1? MyCalendar.lastDay(year-1, 12)- first : MyCalendar.lastDay(year, month-1)- first;
				
						//	1일이 출력될 위치를 맞추기 위해 1일의 요일만큼 반복하여 전달의날짜를 출력한다.
						for(int i= 1; i<= first; i++){
							if(i==1){
							// 일요일 구분
								out.println("<td class = 'redbefore'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
							}else{
								out.println("<td class = 'before'>"+(month ==1? 12 : month-1)+"/"+ ++start +"</td>");
								
							}
						}
				
						/* 1일부터 달력을 출력한 달의 마지막 날짜까지 반복하며 날짜를 출력 */
						for(int i = 1; i <= MyCalendar.lastDay(year, month); i++){
							/* 요일별로 색깔 다르게 해주기위해 td에 class 태그걸어주기 */
							switch(MyCalendar.weekDay(year, month, i)){
								case 0 :
									out.println("<td class ='sun'>" +i +"</td>");
									break;
								case 6 :
									out.println("<td class ='sat'>" +i +"</td>");
									break;
								default :
									out.println("<td class ='etc'>" +i +"</td>");
									break;
							}
							
							/* 출력한 날짜(i)가 토요일이고 그달의 마지막 날짜이면 줄을 바꿔주기 */
							if(MyCalendar.weekDay(year, month, i) == 6 && i != MyCalendar.lastDay(year, month)){
								out.println("</tr><tr>");			
							}
						}
						if(MyCalendar.weekDay(year, month, MyCalendar.lastDay(year, month)) !=6){
							for(int i = MyCalendar.weekDay(year, month, MyCalendar.lastDay(year, month))+1; i < 7; i++){
								out.println("<td></td>");	
							}
						}
					%>
				</tr>
			</table>
		</div>
		<div class="info">
		</div>
	</div>
	<!-- 푸터 -->
	<jsp:include page="/layout/footer.jsp"/>

	<!-- 스크립트 -->
</body>
</html>