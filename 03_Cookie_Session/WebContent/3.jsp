<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	//String id = request.getParameter("id").trim();
	
	//String pwd = request.getParameter("pwd").trim();
	
	// Ex02_01.jsp 페이지에서 넘어온 세션 정보도 받을 수 있다.
	String name = (String)session.getAttribute("name");	//반환 타입이 object라서 형변환을 해야한다.
	String addr = (String)session.getAttribute("addr");
	//String name = request.getParameter("name");
	//String addr = request.getParameter("addr");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
	<h2>입력 폼(Ex02.jsp) 페이지에서 넘어온 데이터</h2>
	<h3>
		입력 받은 아이디 : <%=id %><br>
		입력 받은 비밀번호 : <%=pwd %><br>
	</h3>
	 --%>
	 
	<h2>세션으로 넘어온 데이터</h2>
	<h3>	
		세션으로 받은 이름 : <%=name %><br>
		세션으로 받은 주소 : <%=addr %><br>
	</h3>
	<a href="Ex02_04.jsp">다음으로</a>
</body>
</html>