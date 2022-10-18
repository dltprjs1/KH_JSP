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
			<h3>UPLOAD 데이블 전체목록</h3>
		<hr width="50%" color="lightyellow">
		<br>
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>글번호</th>
				<th>글제목</th>
				<th>조회수</th>
				<th>등록일자</th>
			</tr>
			<c:set var = "list" value="${List }"/>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getUpload_no() }</td>
						<td><a href='<%=request.getContextPath()%>/upload_content.do?no=${dto.getUpload_no()}'>${dto.getUpload_title() }</a></td>
						<td>${dto.getUpload_hit() }</td>
						<td>${dto.getUpload_date().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center"><h3>조회 가능한 리스트가 존재하지 않습니다.</h3></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="글쓰기" 
						onclick="location.href = 'upload_insert.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>