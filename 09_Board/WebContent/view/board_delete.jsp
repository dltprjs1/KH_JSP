<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int board_no = Integer.parseInt(request.getParameter("num").trim());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="marmoon">
			<h3>BOARD 테이블 게시글 삭제 폼 페이지</h3>
		<hr width="50%" color="marmoon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/delete_ok.do">
			<input type="hidden" name="num" value="<%=board_no%>">
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 삭제">
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>		
		</form>
	</div>
</body>
</html>