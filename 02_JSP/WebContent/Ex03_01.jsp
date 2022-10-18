<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String id = request.getParameter("id").trim();

	String pwd = request.getParameter("pwd").trim();
	
	// .trim() : 맨 앞과 맨 뒤의 공백(space bar)을 제거해 주는 함수 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<h2>
			입력받은 아이디 : <%=id %><br>
			입력받은 비밀번호 : <%=pwd %>	
		</h2>
	
	</div>
	
</body>
</html>