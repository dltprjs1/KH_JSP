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
		<hr width="50%" color="lightyellow">
			<h3>JSP_BBS테이블 수정하기 폼 페이지</h3>
		<hr width="50%" color="lightyellow">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/bbs_update.do">
			<c:set var="dto" value="${Cont }"/>
			<input type="hidden" name="board_no" value="${board_no}">
			<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
			<table border="1" cellspacing="0">
				<c:if test="${!empty dto }">
					<tr>
						<th>작성자</th>
						<td><input name="board_writer" value="${dto.getBoard_writer() }"></td>
					</tr>
					<tr>
						<th>글 제목</th>
						<td><input name="board_title" value="${dto.getBoard_title() }"></td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td><textarea rows="20" cols="100" name="board_cont">${dto.getBoard_cont() }</textarea></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input name="board_pwd"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="수정하기">
							<input type="reset" value="삭제하기">						
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>