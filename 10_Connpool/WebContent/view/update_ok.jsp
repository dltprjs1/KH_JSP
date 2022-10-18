<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
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
			<h3>BOARD 테이블 수정하기 페이지</h3>
		<hr width="50%" color="pink">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do?num=<%=content.getBoard_no()%>">
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td>
						<input type="text" name="board_writer" value="<%=content.getBoard_writer() %>">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="board_title" value="<%=content.getBoard_title() %>">
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						<textarea rows="20" cols="100" name="board_cont"><%=content.getBoard_cont() %></textarea>
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td>
						<input type="text" name="board_pwd" >
					</td>
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