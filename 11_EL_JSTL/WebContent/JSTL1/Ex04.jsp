<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		<fmt:formatData> 태그의 여러 가지 속성
		- value : 포맷팅될 날짜를 지정하는 속성
		- type : 포맷팅할 타입을 지정하는 속성
			* date : 날짜를 지정
			* time : 시간을 지정
			* both : 날짜 / 시간을 모두 지정
		- dateStyle : 날짜의 출력 양식을 지정하는 속성, 값에는 full, long, medium, short 등을 지정
		- timeStyle : 시간 출력 형식을 지정
		- pattern : 직접 출력 형식을 지정
		- timeZone : 특정 나라 시간대로 시간을 지정
	--%>
	 
	 <h2>fotmatDate 예제</h2>
	 
	 <c:set var="now" value="<%=new Date() %>"/>
	 <fmt:formatDate value="${now }" type ="date" dateStyle="full"/><br>
	 결과값 : 2022년 9월 30일 금요일
	 
	 <fmt:formatDate value="${now }" type ="date" dateStyle="long"/><br>
	 결과값 : 2022년 9월 30일
	 
	 <fmt:formatDate value="${now }" type ="date" dateStyle="medium"/><br>
	 결과값 : 2022. 9. 30.
	 
	 <fmt:formatDate value="${now }" type ="date" dateStyle="short"/><br>
	 결과값 : 22. 9. 30.
	 
	 <hr>
	 
	 <fmt:formatDate value="${now }" type="time" timeStyle="full"/><br>
	 결과값 : 오후 3시 16분 12초 대한민국 표준시
	 
	 <fmt:formatDate value="${now }" type="time" timeStyle="long"/><br>
	 결과값 : 오후 3시 16분 12초 KST
	 
	 <fmt:formatDate value="${now }" type="time" timeStyle="medium"/><br>
	 결과값 : 오후 3:16:12
	 
	 <fmt:formatDate value="${now }" type="time" timeStyle="short"/><br>
	 결과값 : 오후 3:16
	 
	 <hr>
	 
	 <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
	 결과값 : 2022년 9월 30일 금요일 오후 3시 17분 13초 대한민국 표준시
	 
	 <fmt:formatDate value="${now }" type="both" dateStyle="long" timeStyle="long"/><br>
	 결과값 : 2022년 9월 30일 오후 3시 17분 13초 KST
	 
	 <fmt:formatDate value="${now }" type="both" dateStyle="medium" timeStyle="medium"/><br>
	 결과값 : 2022. 9. 30. 오후 3:17:13
	 
	 <fmt:formatDate value="${now }" type="both" dateStyle="short" timeStyle="short"/><br>
	 결과값 : 22. 9. 30. 오후 3:17
	 
	 <hr>
	 
	 <fmt:formatDate value="${now }" pattern="yyyy-MM-dd hh:mm:ss"/><br>
	 결과값 : 2022-09-30 03:19:54
	 
	 <hr>
	 
	 한국 현재 시간 : 
	 <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
	 
	 <fmt:timeZone value="America/LA">
	 미국 현재 시간 : 
	 <fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/><br>
	 </fmt:timeZone>
</body>
</html>