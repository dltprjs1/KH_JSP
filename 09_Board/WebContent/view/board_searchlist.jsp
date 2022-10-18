<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardDTO> search = (List<BoardDTO>)request.getAttribute("search");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="skyblue">
			<h3>BOARD 테이블 SEARCH_LIST 페이지</h3>
		<hr width="50%" color="skyblue">
		<br>
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>게시판No.</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>게시일</th>
			</tr>
			<%
				if(search.size() != 0){
					for(int i=0;i<search.size();i++){
						BoardDTO dto = search.get(i);
			%>
			<tr>
				<td><%=dto.getBoard_no() %></td>
				<td><a href="<%=request.getContextPath()%>/board_content.do?num=<%=dto.getBoard_no()%>"><%=dto.getBoard_title() %></a></td>
				<td><%=dto.getBoard_writer() %></td>
				<td><%=dto.getBoard_hit() %>
				<td><%=dto.getBoard_date().substring(0,10) %></td>
			</tr>
			<%		}
									
				}else{
			%>
			<tr>
				<td><h3>작성한 게시글이 없습니다.</h3></td>
			</tr>	
			<%	}
			%>
			<tr>
				<td colspan="5" align="center">
					<input type="button" value="글쓰기" onclick="location.href='view/board_write.jsp'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>