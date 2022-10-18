<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num").trim());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color ="gray">
			<h3>BOARD테이블 게시글 삭제 페이지</h3>
		<hr width="50%" color ="gray">
		<br>
		<form method="post" action="<%=request.getContextPath()%>/delete.do?num=<%=num%>">
			<table border="1" cellspaing="0">
				<tr>
					<th>비밀번호 확인</th>
					<td><input type="text" name="board_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제하기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>