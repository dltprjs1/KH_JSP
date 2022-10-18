<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>EL 표현식으로 scope 내용 출력</h2>
	<h3>
		pageContext : ${pageScope.res } == ${res }<br>
		request : ${requestScope.r } == ${r }<br>
		session : ${sessionScope.s} == ${s }<br>
		application : ${applicationScope.a} == ${a }<br>
	</h3>
	
</body>
</html>