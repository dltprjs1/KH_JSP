<%@page import="com.board1.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<BoardDTO> select = (List<BoardDTO>)request.getAttribute("select");
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
			<h3>BOARD 테이블 전체 리스트</h3>
		<hr width="50%" color="skyblue">
		<br>
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>글번호</th><th>작성자</th><th>제목</th><th>조회수</th><th>게시일</th>
			</tr>
			<%
				if(select.size() != 0){
					for(int i=0;i<select.size();i++){
						BoardDTO dto = select.get(i);
			%>
			<tr>
				<td><%=dto.getBoard_no() %></td>
				<td><a href="<%=request.getContextPath()%>/content.do?num=<%=dto.getBoard_no()%>"><%=dto.getBoard_writer() %></a></td>
				<td><%=dto.getBoard_title() %></td>
				<td><%=dto.getBoard_hit() %></td>
				<td><%=dto.getBoard_date() %></td>
			</tr>
			<%		}
				}else{
			%>		
			<tr>
				<td><h3>게시글을 찾을 수 없습니다.</h3></td>
			</tr>
			<%	}  %>
			<tr>
				<td colspan="5" align="center">
					<input type="button" value="글쓰기" onclick="location.href='<%=request.getContextPath() %>/view/board_insert.jsp'">
				</td>
			</tr>
		</table>
			<br>
			<hr width ="50%" color="skyblue">
			<form method="post" action="<%=request.getContextPath() %>/search.do">
				<select name="find">
					<option value="writer">작성자</option>
					<option value="title">제목</option>
					<option value="cont">내용</option>					
					<option value="title_cont">제목+내용</option>
				</select>
				<input type="text" name="find_name">
				<input type="submit" value="검색">
			</form>
			
	</div>
</body>
</html>