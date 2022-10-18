<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lemon">
			<h3>MEMBER10 테이블 수정 폼 페이지</h3>
		<hr width="50%" color="lemon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<c:set var = "content" value="${update}"/>
			<input type="hidden" name="num" value="${content.getNum() }">
			<input type="hidden" name="db_pwd" value="${content.getPwd() }">
			<table border="1" cellspacing="0" width="350">
				<c:if test="${!empty content }">
					<tr>
						<th>회원 아이디</th>
						<td>
							<input name="mem_id" value="${content.getMemid() }" readonly>
						</td>
					</tr>
					<tr>
						<th>회원 이름</th>
						<td>
							<input name="mem_name" value="${content.getMemname() }">
						</td>
					</tr>
					<tr>
						<th>회원 비밀번호</th>
						<td>
							<input type="password" name="mem_pwd">
						</td>
					</tr>
					<tr>
						<th>회원 나이</th>
						<td>
							<input name="mem_age" value="${content.getAge() }">
						</td>
					</tr>
					<tr>
						<th>회원 마일리지</th>
						<td>
							<input name="mem_mileage" value="${content.getMileage() }">
						</td>
					</tr>
					<tr>
						<th>회원 직업</th>
						<td>
							<input name="mem_job" value="${content.getJob() }">
						</td>
					</tr>
					<tr>
						<th>회원 주소</th>
						<td>
							<input name="mem_addr" value="${content.getAddr() }">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="수정하기">
							<input type="reset" value="다시작성">
						</td>
					</tr>
				</c:if>
				<c:if test="${empty content }">
					<td colspan="2" align="center">
						<h3>회원 정보를 찾을 수 없습니다.</h3>
					</td> 
				</c:if>
			</table>
		</form>
	</div>
</body>
</html>