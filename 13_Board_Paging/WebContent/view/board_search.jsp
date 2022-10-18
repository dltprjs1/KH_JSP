<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.pagination{
		justify-content: center;
	}
</style>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lightblue">
			<h3>BOARD 테이블 검색 목록 페이지</h3>
		<hr width="50%" color="lightblue">
		<br>
		
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>글 번호</th><th>작성자</th><th>제목</th><th>조회수</th><th>게시일자</th>
			</tr>
			<c:set var="list" value="${List }"/>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getBoard_no() }</td>
						<td>${dto.getBoard_writer() }</td>
						<td>
							<a href="<%=request.getContextPath()%>/board_content.do?no=${dto.getBoard_no()}&page=${page}">
								${dto.getBoard_title() }
							</a>
						</td>
						<td>${dto.getBoard_hit() }</td>
						<td>${dto.getBoard_date() }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="5" align="center">
						<h4>검색된 게시물 리스트가 없습니다.</h4>
					</td>
				</tr>
			</c:if>
		</table>
		<br>
		<%-- 페이징(bootstrap)처리 영역 --%>
		<nav>
		  <ul class="pagination">
		  	<%-- <c:if test="${page > block }"> --%>
			    <li class="page-item">
			      <a class="page-link" href="board_search.do?page=1&search_field=${field }&search_keyword=${keyword}">First</a>
			    </li >
			    <li class="page-item">
			    	<c:if test="${page != 1 }">
			    		<a class="page-link" href="board_search.do?page=${page - 1 }&search_field=${field }&search_keyword=${keyword}">Previous</a>
			    	</c:if>
			    	<c:if test="${page == 1 }">
			    		<a class="page-link" href="board_search.do?page=1&search_field=${field }&search_keyword=${keyword}">Previous</a>
			    	<%-- </c:if> --%>
			    </li>
			 </c:if>
		    <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
				<c:if test="${i == page }">
					<li class="page-item active" aria-current="page">
						<a class="page-link" href="board_search.do?page=${i }&search_field=${field }&search_keyword=${keyword}">${i }</a>
					</li>
				</c:if>
				<c:if test="${i != page }">
					<li class="page-item ">
						<a class ="page-link" href="board_search.do?page=${i }&search_field=${field }&search_keyword=${keyword}">${i }</a>
					</li>
				</c:if>
			</c:forEach>
			<c:if test="${endBlock < allPage }">
				<li class="page-item">
					<a class="page-link" href="board_search.do?page=${page+1 }&search_field=${field }&search_keyword=${keyword}">Next</a>
				</li>
				<li class="page-item">
					<a class="page-link" href="board_search.do?page=${allPage }&search_field=${field }&search_keyword=${keyword}">End</a>
				</li>
			</c:if> 
		  </ul>
		</nav>
		<br>
		<%-- 검색 기능 처리 --%>
		<hr width="50%" color="lightblue">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/board_search.do">
			<select name="search_field">
				<option value="title">제목</option>
				<option value="cont">내용</option>
				<option value="title_cont">제목+내용</option>
				<option value="writer">작성자</option>
			</select>
			<input name="search_keyword">
			
			<input type="submit" value="검색">
		</form>

	</div>
</body>
</html>