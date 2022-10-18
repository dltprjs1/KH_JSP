<%@page import="com.emp.medel.DeptDTO"%>
<%@page import="com.emp.medel.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<String> joblist = (List<String>)request.getAttribute("joblist");
	List<EmpDTO> mgrlist = (List<EmpDTO>)request.getAttribute("mgrlist");
	List<DeptDTO> deptlist = (List<DeptDTO>)request.getAttribute("deptlist");
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
		<hr width="50%" color="pink">
			<h3>사원 수정 페이지</h3>
		<hr width="50%" color="pink">
		<br>  <br>
		
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
			<input type="hidden" name="emp_num" value="<%=content.getEmpno() %>"> 
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>이 름</th>
					<td><input type="text" name="emp_name" value="<%=content.getEname() %>"></td>
				</tr>
				<tr>
					<th>업 무</th>
					<td><%-- <input type="text" name="emp_job" value="<%=content.getJob() %>"> --%>
							<select name="emp_job">
					<%
						if(joblist.size() == 0){
					%>	
							<option value="">:::담당업무 없음:::</option>
					<%	}else{
							for(int i=0;i<joblist.size();i++){
								String str = joblist.get(i);
					%>		<option value="<%=str %>"><%=str %></option>		
					<% }
					}
					%>
						</select>
					</td>
				</tr>
				<tr>
					<th>관리자No.</th>
					<td><%-- <input type="text" name="emp_mgr" value="<%=content.getMgr() %>"> --%>
						<select name="emp_mgr">
					<%
						if(mgrlist.size() == 0){
					%>	
							<option value="">:::관리자 없음:::</option>
					<%	}else{
							for(int i=0;i<mgrlist.size();i++){
								EmpDTO dto = mgrlist.get(i);
					%>		<option value="<%=dto.getEmpno() %>"><%=dto.getEname() %>[<%=dto.getEmpno() %>]</option>		
					<% }
					}
					%>
						</select>
					</td>
				</tr>
				<tr>
					<th>입사일</th>
					<td><input type="text" name="emp_hiredate" value="<%=content.getHiredate().substring(0,10) %>"></td>
				</tr>
				<tr>
					<th>봉 급</th>
					<td><input type="text" name="emp_sal" value="<%=content.getSal() %>"></td>
				</tr>
				<tr>
					<th>보너스</th>
					<td><input type="text" name="emp_comm" value="<%=content.getComm() %>"></td>
				</tr>
				<tr>
					<th>부서번호</th>
					<td><%-- <input type="text" name="emp_deptno" value="<%=content.getDeptno() %>"> --%>
						<select name="emp_deptno">
					<%
						if(deptlist.size()== 0){
					%>	
							<option value="">:::부서번호 없음:::</option>
					<%	}else{
							for(int i=0;i<deptlist.size();i++){
								DeptDTO dto = deptlist.get(i);
						
					%>		<option value="<%=dto.getDeptno() %>"><%=dto.getDname() %>[<%=dto.getDeptno() %>]</option>
					<%	}
					}
						
					%>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>