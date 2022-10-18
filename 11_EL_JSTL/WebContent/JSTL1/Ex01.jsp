<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--
		기본적인 JSTL 태그 ==> 출력할 때는 EL 언어를 사용
		
		1. 변수 선언 태그 :set
			<c:set var="변수명" value="값"> </c:set>
			예) <c:set var="su" value="10" /> == int su =10; 
			   <c:set var="str" value="Hello"/> == String str = "Hello"
		
		2. 출력 태그 : out
			<c:out value=${변수명} />
			예) <c:out value="${str}"/> ==> 결과값 : Hello
			
		3. 삭제 태그 : remove
			<c:remove var="변수명"/>
			예) <c:remove var="str"/>
			
		4. 조건 처리 태그(if문) : if ==> JSTL에서는 else문이 없다.
			<c:if test="조건식" var="변수명"/>
		
		5. 조건 처리 태그(switch~case문) : choose ~ when
		 	<c:choose>
		 		<c:when test="조건식1">
		 			조건식1이 참인 경우 실행문장
		 		</c:when>
		 		
		 		<c:when test="조건식2">
		 			조건식2이 참인 경우 실행문장
		 		</c:when>
		 		
		 		<c:when test="조건식3">
		 			조건식3이 참인 경우 실행문장
		 		</c:when>
		 		
		 		<c:when test="조건식4">
		 			조건식4이 참인 경우 실행문장
		 		</c:when>
		 		
		 		<c:otherwise>
		 			상기 조건식 이외의 경우 실행 문장
		 		</c:otherwise>
		 	</c:choose>
		 
		 6. 반복문(for문) : forEach 
		 	<c:forEach begin="시작값" end="끝값" step="증감값" var="변수명">
		 		반복 실행할 문장
		 	</c:forEach>
		 	
		 	<c:forEach items="객체명" var="변수명"> : 단축 for문
	 --%>
	 
	 <h2>JSTL의 기본적인 태그들</h2>
	 
	 <h3>
	 	<%-- 1. 변수 태그 : set --%>
	 	<c:set var="str" value="Hello JSTL!!"/>
	 	<hr>
	 	
	 	<%-- 2. 출력 태그 : out --%>
	 	JSTL 값 출력 : <c:out value="str"/><br>
	 	JSTL 값 출력 : <c:out value="${str }"/><br>
	 	<hr>
	 	
	 	<%-- 3. 삭제 태그 : remove --%>
	 	<c:remove var="str"/>
	 	삭제 후 값 출력 : <c:out value="${str }"/>
	 	<hr>
	 	
	 	<%-- 4. 조건 처리 태그 : if(else문 없음) --%>
	 	<c:if test="${10 > 5 }" var="k"/>
	 	조건식 처리 후 값 출력 : <c:out value="${k }"/><br>
	 	<hr>
	 	
	 	<%-- 5. 조건 처리 태그 : choose ~ when --%>
	 	<%-- 학점 처리 작업 --%>
	 	<c:set var="grade" value="89"/>
	 	<c:choose>
			<c:when test="${grade >= 90 }">
				결과 : A학점입니다
			</c:when>
			<c:when test="${grade >= 80 }">
				결과 : B학점입니다
			</c:when>
			<c:when test="${grade >= 70 }">
				결과 : C학점입니다
			</c:when>
			<c:when test="${grade >= 60 }">
				결과 : D학점입니다
			</c:when>
			<c:otherwise>
				결과 : F학점입니다.
			</c:otherwise>
	 	</c:choose>
	 	<hr>
	 	
	 	<%-- 6. 반복 처리 태그 : forEach --%>
	 	<%-- 반복문을 이용하여 1 ~ 10까지 출력해 보자 --%>
	 	<c:forEach begin="1" end="10" var="i">	<%-- step을 주지 않으면 1씩 증가 --%>
	 		${i }&nbsp;&nbsp;&nbsp;
	 	</c:forEach>
	 	<hr>
	 	
	 	<%-- 7. 단축 for문 태그 : forEach --%>
	 	
	 	<%
	 		String [] str = {"홍길동","이순신","유관순","김유신","세종대왕"};
	 	
	 		pageContext.setAttribute("List", str);
	 	%>
	 	결과 : 
	 	<c:forEach items="${List}" var="k">
	 		${k}&nbsp;&nbsp;&nbsp;
	 	</c:forEach>
	 </h3>
	 
</body>
</html>