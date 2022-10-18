<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int res = 45 + 171;
		pageContext.setAttribute("res", res); 
		// page : 해당 페이지 안에서만 값이 유효하다.
		
		request.setAttribute("r",1000);		  
		// request : getAttribute로 요청 받으면(Ex06.jsp) 그 후 값은 사라진다.
		
		session.setAttribute("s",10000);
		// session : session 설정 시간이 지난 후 값이 사라진다.
		
		application.setAttribute("a", 100000);
		// application : application이 종료 되면 값이 사라진다.
		
		RequestDispatcher rd = request.getRequestDispatcher("Ex06.jsp");
		
		rd.forward(request, response);
	%>
	<h3>
		JSP 표현식 결과 : <%=res %><br>
		
		El 표현식 결과 : ${res }<br>
	</h3>
	
</body>
</html>