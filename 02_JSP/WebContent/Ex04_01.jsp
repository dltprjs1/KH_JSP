<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//한글 반환 받을 경우 깨짐 방지 코드 작성
	request.setCharacterEncoding("UTF-8");

	// Ex04.jsp 페이지에서 넘어온 데이터를 받아 주어야 한다.
	/* String id = request.getParameter("id").trim();
	request.getParameter("pwd").trim();
	request.getParameter("name").trim();
	request.getParameter("phone").trim();
	request.getParameter("addr").trim();
    */
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<h2>가입 회원 정보</h2>
			<table border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><%=request.getParameter("id").trim() %></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><%=request.getParameter("pwd").trim() %></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><%=request.getParameter("name").trim() %></td>
				</tr>
				<tr>
					<th>연락처</th>
					<td><%=request.getParameter("phone").trim() %></td>
				</tr>
				<tr>
					<th>거주지</th>
					<td><%=request.getParameter("addr").trim() %></td>
				</tr>
			</table>
	</div>
</body>
</html>