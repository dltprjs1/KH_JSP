<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>BOARD 테이블 삭제 폼 페이지 </h3>
		<hr width="50%" color="red">
		<br>
		<form method="post" action="board_deleteok.do">
		<c:set var="dto" value="${Content }"/>
		<input type="hidden" name="db_pwd" value="${dto.getBoard_pwd() }">
		<input type="hidden" name="board_no" value="${board_no }">	
		
		<!-- ${param.no}  , ${param.page}를 쓰면 action페이지를 거치지 않고 사용 가능-->
		
		<table border="1" cellspacing="0">
			<tr>
				<th>비밀번호</th>
				<td><input name="board_pwd"></td>
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