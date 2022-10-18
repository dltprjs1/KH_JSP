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
		<c:set var="dto" value="${Content }" />
		<hr width="50%" color="lightgreen">
		<h3>${dto.getUpload_writer() }님의게시물상세보기</h3>
		<hr width="50%" color="lightgreen">
		<br>
		<table border="1" cellspacing="0">
			<tr>
				<th>작성자</th>
				<td>${dto.getUpload_writer() }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.getUpload_title() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="20" cols="100" readonly>${dto.getUpload_cont() }</textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><c:forEach begin="1" end="${dto.getUpload_pwd().length() }">
						*
					</c:forEach></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.getUpload_hit() }</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<c:if test="${!empty dto.getUpload_file() }">
					<td><a
						href="<%=request.getContextPath() %>/upload/${dto.getUpload_file()}"
						target="_blank">${dto.getUpload_file() }</a></td>
				</c:if>
				<c:if test="${emptydto.getUpload_file() }">
					<td></td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${empty dto.getUpload_update() }">
					<th>등록일자</th>
					<td>${dto.getUpload_date() }</td>
				</c:if>
				<c:if test="${!empty dto.getUpload_update() }">
					<th>수정일자</th>
					<td>${dto.getUpload_update() }</td>
				</c:if>
			</tr>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>게시물이 존재하지 않습니다.</h3>
					</td>
				</tr>
			</c:if>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="수정하기" onclick="location.href='upload_modify.do?no=${dto.getUpload_no() }'">
						<input type="button" value="삭제하기" onclick="if(confirm('게시글을 정말 삭제하시겠습니까?')){
																		location.href='upload_delete.do?no=${dto.getUpload_no() }&db_pwd=${dto.getUpload_pwd() }'
																		}else{return;}">
						<input type="button" value="전체목록" onclick="location.href='upload_list.do'">
					</td>
				</tr>
		</table>
	</div>
</body>
</html>