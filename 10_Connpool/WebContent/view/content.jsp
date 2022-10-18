<%@page import="com.board1.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	BoardDTO content = (BoardDTO)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="yellow">
			<h3>BOARD테이블 상세 보기 페이지</h3>
		<hr width="50%" color="yellow">
		<br>
		<table border="1" cellspacing="0">
		<%
			if(content != null){
		%>
			<tr>
				<th>글번호</th>
				<td><%=content.getBoard_no() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=content.getBoard_writer() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=content.getBoard_title() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="100" rows="20" readonly><%=content.getBoard_cont() %></textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><%-- <%=content.getBoard_pwd() %> --%>
					<%
						if(content.getBoard_pwd() != null){
							for(int i=0;i<content.getBoard_pwd().length();i++){			
					%>		
										*					
					<%		}
						}
					%>
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=content.getBoard_hit() %></td>
			</tr>
			<%
				if(content.getBoard_update()==null){
			%>
				<tr>
					<th>게시일</th>
					<td><%=content.getBoard_date() %></td>
				</tr>
			<%	
				}else{
			%>		
				<tr>
					<th>수정일</th>
					<td><%=content.getBoard_update() %></td>
				</tr>
			<%	}  
			
			 } %>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="수정하기" onclick="location.href='<%=request.getContextPath()%>/update.do?num=<%=content.getBoard_no()%>'">
					<input type="button" value="삭제하기" onclick="location.href='<%=request.getContextPath()%>/view/delete.jsp?num=<%=content.getBoard_no()%>'">
					<input type="button" value="전체목록" onclick="location.href='<%=request.getContextPath()%>/select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>