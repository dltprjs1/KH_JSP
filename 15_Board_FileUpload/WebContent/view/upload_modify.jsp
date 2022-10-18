<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<c:set var="dto" value="${Content }"/>
		<hr width="50" color="red">
			<h3>${dto.getUpload_writer() }님의 게시글 수정하기 폼 페이지</h3>
		<hr width="50" color="red">
		<br>
		<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath() %>/upload_modify_ok.do">
			<input type="hidden" name="db_pwd" value="${dto.getUpload_pwd() }">
			<input type="hidden" name="upload_no" value="${dto.getUpload_no() }">
			<table border="1" cellspacing="0">
				<tr>
					<th>작성자</th>
					<td><input name="upload_writer" value="${dto.getUpload_writer() }"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input name="upload_title" value="${dto.getUpload_title() }"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="20" cols="100" name="upload_cont">${dto.getUpload_cont() }</textarea></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input name="upload_pwd"></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="upload_file"}"></td>
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