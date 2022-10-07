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
			<h3>JSP_BBS 테이블 답변형 게시판 글쓰기 폼 페이지</h3>
		<hr width="50%" color="pink">
		<br>
		<form method="post" action="<%=request.getContextPath()%>/bbs_write_ok.do">
			<table border="1" cellspacing="0" width="800">
				<tr>
					<td>작성자</td>
					<td>
						<input name="board_writer">
					</td>
				</tr>
				<tr>
					<td>글제목</td>
					<td>
						<input name="board_title">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea rows="20" cols="100" name="board_cont"></textarea>
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="board_pwd">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글쓰기">
						<input type="reset" value="다시작성">
					</td>
			</table>
		</form>
	</div>
</body>
</html>