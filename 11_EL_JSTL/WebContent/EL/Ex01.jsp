<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int su = 235;

	pageContext.setAttribute("su",su);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현 언어로 여러 가지 데이터 출력 하기</h2>
	
	<h3>
		${100 }<br>
		
		${100 + 50 }<br>
		
		${"안녕하세요" }<br>
		
		${su }<br>
		
		<%-- 숫자형 문자열과 숫자를 더하면 문자열을 자동으로 숫자로 변환하여 출력 --%>
		
		${"20" + 50 }<br>	 <%-- 결과값 : 70 --%>
		
		<%-- ${"20열"+50 }<br> 	  결과값 : 오류 --%>
		
		<%-- 피연산자가 null이면 0으로 처리가 된다. --%>		
		${null + 45 } <br>	 <%-- 결과값 : 45 --%>
		
	</h3>
</body>
</html>