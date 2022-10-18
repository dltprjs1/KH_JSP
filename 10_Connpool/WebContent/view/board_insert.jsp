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
		<hr width="50%" color="pink">
			<h3>BOARD 테이블 글쓰기 페이지</h3>
		<hr width="50%" color="pink">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/insert.do">
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="board_writer"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="board_title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="100" rows="20" name="board_cont"></textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="text" name="board_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="작성하기">
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>