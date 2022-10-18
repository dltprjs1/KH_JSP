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
		<hr width="50%" color="skyblue">
			<h3>UPLOAD 테이블 자료실 게시판 글쓰기 폼 페이지</h3>
		<hr width="50%" color="skyblue">
		<br>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/upload_write_ok.do">
		<%--
			enctype : 파일을 업로드하기 위한 속성이다.
					  enctype 속성을 사용할 경우 반드시 method는 post여야한다.
		 --%>
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input name="upload_writer"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input name="upload_title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="upload_cont" rows="20" cols="100"></textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upload_file"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="등록하기">
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>