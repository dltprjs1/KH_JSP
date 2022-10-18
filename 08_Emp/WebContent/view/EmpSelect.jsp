<%@page import="com.emp.medel.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<EmpDTO> list = (List<EmpDTO>) request.getAttribute("select");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="pink">
			<h3>EMP테이블 전체목록</h3>
		<hr width="50%" color="pink">
		<br> <br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>사원No.</th> <th>이 름</th> <th>부서No.</th><th>입사일자</th>
			</tr>
			<%
				if(list.size() != 0){
					for(int i=0;i<list.size();i++){
						EmpDTO dto = list.get(i);
			 %>	<tr>
			 		<td><%= dto.getEmpno()%></td>
			 	
			 		<td>
			 		<a href="<%=request.getContextPath()%>/content.do?num=<%=dto.getEmpno()%>">
			 		<%= dto.getEname()%></a>
			 		</td>
			 	
			 		<td><%= dto.getDeptno()%></td>
			 	
			 		<td><%= dto.getHiredate().substring(0,10)%></td>
			 	</tr>
			 
			 <%}
				}else{
			  %> <tr>
			  		<td colspan="4"> <h3>검색 가능한 목록이 존재하지 않습니다.</h3></td>
			  	</tr>
				<% } %>
			
			<tr>
				<td colspan="4" align="right">
				<input type="button" value="사원등록" 
				onclick="location.href='<%= request.getContextPath() %>/insert.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>