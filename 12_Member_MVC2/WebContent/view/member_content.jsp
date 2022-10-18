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
		<hr width="50%" color="skyblue">
			<h3>MEMBER10 테이블 상세보기</h3>
		<hr width="50%" color="skyblue">
		<br>
		<table border="1" cellspacing="0" width="800">
			<c:set var="content" value="${content }"></c:set>
			<c:if test="${!empty content }">
			<tr>
				<th>회원No.</th>
				<td>${content.getNum() }</td>
			</tr>
			<tr>
				<th>회원 아이디</th>
				<td>${content.getMemid() }</td>
			</tr>
			<tr>
				<th>회원 이름</th>
				<td>${content.getMemname() }</td>
			</tr>
			<tr>
				<th>회원 비밀번호</th>
				<td><%-- ${content.getPwd() } --%>
					<c:if test="${content.getPwd().length() !=0 }">
						<c:forEach begin="1" end="${content.getPwd().length() }">
							*
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>회원 나이</th>
				<td>${content.getAge() }</td>
			</tr>
			<tr>
				<th>회원 마일리지</th>
				<td><%-- ${content.getMileage() } --%>
					<fmt:formatNumber value="${content.getMileage() }"/>
				</td>
			</tr>
			<tr>
				<th>회원 가입일</th>
				<td>${content.getRegdate() }</td>
			</tr>
			<tr>
				<th>회원 주소</th>
				<td>${content.getAddr() }</td>
			</tr>
			<tr>
				<th>회원 직업</th>
				<td>${content.getJob() }</td>
			</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="location.href='<%=request.getContextPath() %>/update.do?num=${content.getNum() }'">
					<input type="button" value="삭제하기" onclick="if(confirm('정말로 회원을 삭제하시겠습니까?')){
																	location.href='<%=request.getContextPath()%>/delete.do?num=${content.getNum() }'
																	}else{return;}">
					<input type="button" value="회원목록" onclick="location.href='select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>