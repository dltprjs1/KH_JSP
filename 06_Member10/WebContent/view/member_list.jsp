<%@page import="com.member.model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<MemberDTO> memberList = (List<MemberDTO>)request.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="red">
			<h3>MEMBER10 테이블 회원 전체 목록 </h3>
		<hr width="50%" color="red">
		<br>  <br>
		
		<table border="1" cellspacing="0" width="350">
			<tr>
				<th>회원No.</th> <th>회원 아이디</th>
				<th>회원명</th> <th>회원가입일</th>
			</tr>
			
			<%
				if(memberList.size() != 0){
					// memberList에 데이터가 있다면 데이터 수만큼 반복하여 웹 브라우저에 출력
					for(int i=0;i<memberList.size();i++){
						MemberDTO dto =  memberList.get(i);
			%>
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getMemid() %></td>
					<td>
						<a href="<%=request.getContextPath() %>/content.do?num=<%=dto.getNum() %>">
						<%=dto.getMemname() %></a></td>
					<td><%=dto.getRegdate().substring(0,10) %></td>
				</tr>
				<% 	}
				}else{
					// 데이터가 없는 경우
				%>
				<tr>
					<td colspan="4" align="center">
						<h3>회원 목록이 없습니다.</h3>
					</td>
				</tr>
			<% 	}
			%>
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="회원등록" onclick="location.href='view/member_join.jsp'">
				</td>
			</tr>
		</table>	
	</div>
	
</body>
</html>