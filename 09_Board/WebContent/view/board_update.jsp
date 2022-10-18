<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* int num = Integer.parseInt(request.getParameter("num").trim()); */
	BoardDTO content = (BoardDTO)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="pink">
			<h3>BOARD 테이블 수정 폼 페이지</h3>
		<hr width="50%" color="pink">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/board_updateok.do">
			<input type="hidden" name="board_no" value="<%=content.getBoard_no() %>">
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="board_writer" value="<%=content.getBoard_writer()%>"></td>
				</tr>
				<tr>
					<th>제 목</th>
					<td><input type="text" name="board_title" value="<%=content.getBoard_title() %>"></td>
				</tr>
				<tr>
					<th>글내용</th>
					<td>
						<textarea name="board_content" cols="100" rows="20"><%=content.getBoard_cont() %></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="text" name="board_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">
						<input type="reset" value="다시작성">						
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>