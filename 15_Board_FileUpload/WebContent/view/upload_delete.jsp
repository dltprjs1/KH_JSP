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
		<hr width="50%" color="pink">
			<h3>UPLOAD 테이블 자료실 게시글 삭제 폼 페이지</h3>
		<hr width="50%" color="pink">
		<br>
		<form method="post" action="<%=request.getContextPath()%>/upload_deleteOk.do">
		<input type="hidden" name="upload_no" value="${param.no }">
		<input type="hidden" name="db_pwd" value="${param.db_pwd }">
			<table border="1" cellspacing="0" width="500">
				<tr>
					<th>게시글 비밀번호</th>
					<td><input type="password" name="upload_pwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="삭제하기">
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>