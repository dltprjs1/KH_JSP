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
			<h3>BOARD 테이블 전체 목록 페이지</h3>
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
						<h4>전체 게시물 리스트가 없습니다.</h4>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="center">
					<input type="button" value="글쓰기"
						onclick="location.href='board_write.do'">
				</td>
				
			</tr>
		</table>
		<br>
		<%-- 페이징(bootstrap)처리 영역 --%>
		<nav>
		  <ul class="pagination">
		    <li class="page-item">
		      <a class="page-link" href="board_list.do?page=1">First</a>
		    </li >
		    <li class="page-item">
		    	<c:if test="${page != 1 }">
		    		<a class="page-link" href="board_list.do?page=${page - 1 }">Previous</a>
		    	</c:if>
		    	<c:if test="${page == 1 }">
		    		<a class="page-link" href="board_list.do?page=1">Previous</a>
		    	</c:if>
		    </li>
		    <c:forEach begin="${startBlock }" end="${endBlock }" var="i">
				<c:if test="${i == page }">
					<li class="page-item active" aria-current="page">
						<a class="page-link" href="board_list.do?page=${i }">${i }</a>
					</li>
				</c:if>
				<c:if test="${i != page }">
					<li class="page-item ">
						<a class ="page-link" href="board_list.do?page=${i }">${i }</a>
					</li>
				</c:if>
			</c:forEach>
			<c:if test="${endBlock < allPage }">
				<li class="page-item">
					<a class="page-link" href="board_list.do?page=${page+1 }">Next</a>
				</li>
				<li class="page-item">
					<a class="page-link" href="board_list.do?page=${allPage }">End</a>
				</li>
			</c:if> 
		  </ul>
		</nav>
		<%-- 페이징 처리 --%>
		<%--
			<c:if test="${page > block}">
				<a href="board_list.do?page=1">◀◀</a>
				<a href="board_list.do?page=${startBlock - 1 }">◀</a>
			</c:if>
			<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
				<c:if test="${i == page }">
					<b><a href="board_list.do?page=${i }">[${i }]</a></b>
				</c:if>
				<c:if test="${i != page }">
					<a href="board_list.do?page=${i }">[${i }]</a>
				</c:if>
			</c:forEach>
			<c:if test="${endBlock < allPage }">
				<a href="board_list.do?page=${endBlock+1 }">▶</a>
				<a href="board_list.do?page=${allPage }">▶▶</a>
			</c:if> 
		--%>
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