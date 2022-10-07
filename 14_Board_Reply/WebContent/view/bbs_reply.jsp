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
			<h3>JSP_BBS테이블 답변형 게시판 폼 페이지</h3>
		<hr width="50%" color="lightyellow">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/bbs_reply_ok.do">
			<c:set var="dto" value="${reply }"/>
			<input type="hidden" name="no" value="${board_no}">
			<input type="hidden" name="group" value="${dto.getBoard_group()}">
			<input type="hidden" name="step" value="${dto.getBoard_step()}">
			<input type="hidden" name="indent" value="${dto.getBoard_indent()}">
			<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
			<table border="1" cellspacing="0">
				<c:if test="${!empty dto }">
					<tr>
						<th>작성자</th>
						<td><input name="reply_board_writer" value="${dto.getBoard_writer() }"></td>
					</tr>
					<tr>
						<th>글 제목</th>
						<td><input name="reply_board_title" value="(Re)${dto.getBoard_title() }"></td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td><textarea rows="20" cols="100" name="reply_board_cont">${dto.getBoard_cont() }</textarea></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input name="reply_board_pwd"></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="답변하기">
							<input type="reset" value="다시작성">						
						</td>
					</tr>
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>