<%@page import="com.emp.medel.EmpDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	EmpDTO content = (EmpDTO)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="gray">
			<h3>사원 상세 정보 페이지</h3>
		<hr width="50%" color="gray">
		<br> <br>
		<table border="1" cellspacing="0" width="400">
			<%
				if (content != null){
			%>
			<tr>
				<th>사원No.</th>
				<td><%=content.getEmpno() %></td>
			</tr>
			<tr>
				<th>이 름</th>
				<td><%=content.getEname() %></td>
			</tr>
			<tr>
				<th>업 무</th>
				<td><%=content.getJob() %></td>
			</tr>
			<tr>
				<th>관리자No.</th>
				<td><%=content.getMgr() %></td>
			</tr>
			<tr>
				<th>입사일자</th>
				<td><%=content.getHiredate() %></td>
			</tr>
			<tr>
				<th>봉 급</th>
				<td><%=content.getSal() %></td>
			</tr>
			<tr>
				<th>보너스</th>
				<td><%=content.getComm() %></td>
			</tr>
			<tr>
				<th>부서No.</th>
				<td><%=content.getDeptno() %></td>
			</tr>
					
			<%	}
			%>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="사원수정" onclick="location.href='<%=request.getContextPath() %>/update.do?num=<%=content.getEmpno()%>'">&nbsp;&nbsp;&nbsp;
					<input type="button" value="사원삭제" onclick="location.href='<%=request.getContextPath() %>/delete.do?num=<%=content.getEmpno()%>'">&nbsp;&nbsp;&nbsp;
					<input type="button" value="사원목록" onclick="location.href='<%=request.getContextPath() %>/select.do'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>