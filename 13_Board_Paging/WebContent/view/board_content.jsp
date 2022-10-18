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
		<hr width="50%" color="lightgreen">
			<h3>${dto.getBoard_writer() }님 게시물 상세 내역</h3>
		<hr width="50%" color="lightgreen">
		<br>
		<table border="1" cellspacing="0">
			<c:if test="${!empty dto }">
			<tr>
				<th>작성자</th>
				<td>${dto.getBoard_writer() }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.getBoard_title() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="20" cols="100" readonly>${dto.getBoard_cont() }</textarea>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<c:if test="${dto.getBoard_pwd().length() != 0 }">
						<c:forEach begin="1" end="${dto.getBoard_pwd().length() }">
							*
						</c:forEach>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.getBoard_hit() }</td>
			</tr>
			<tr>
				<c:if test="${empty dto.getBoard_update() }">
					<th>등록일</th>
					<td>${dto.getBoard_date() }</td>
				</c:if>
				<c:if test="${!empty dto.getBoard_update() }">
					<th>수정일</th>
					<td>${dto.getBoard_update() }</td>
				</c:if>
			</tr>
			</c:if>
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">	
						<h3>조회된 게시글이 없습니다.</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2" align="center">
					<input type="button"  value="수정하기" onclick="location.href='board_update.do?no=${dto.getBoard_no()}&page=${Page}'">
					<input type="button"  value="삭제하기" onclick="if(confirm('정말록 삭제하시겠습니까?')){
																		location.href='board_delete.do?no=${dto.getBoard_no()}&page=${Page}'
																		}else{return;}">					
					<input type="button"  value="전체목록" onclick="location.href='board_list.do'">					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>