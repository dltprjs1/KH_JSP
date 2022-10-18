<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h2>메인 페이지</h2>
		<hr width="50%" color="red">
		<br> <br>
		<h3>Servlet에서 넘어온 데이터)(forward 방식)</h3>
		<h3><%=request.getAttribute("name") %>님 환영합니다.</h3>
		<!-- 
		getAttribute() : Servlet 페이지의 setAttribute함수의 값을 반환 받는다 
		Servlet 페이지에 request.setAttribute("name","홍길동") ==> name="홍길동"
		-->
		
		<h4>
			입력받은 아이디 : <%=request.getParameter("id").trim() %><br>
			입력받은 비밀번호 : <%=request.getParameter("pwd").trim() %><br>
			주소 : <%=request.getAttribute("addr") %>
		</h4>
	</div>
</body>
</html>