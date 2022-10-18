<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 한글 깨짐 방지 설정 코드 작성
	request.setCharacterEncoding("UTF-8");
	//String memid = request.getParameter("memid").trim();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--MemberDTO member = new MemberDTO();--%>
	<jsp:useBean id="member" class="khie.MemberDTO"></jsp:useBean>
	
	<%-- member.setMemid() --%>
	<%-- 
	<jsp:setProperty property="memid" name="member"/>
	<jsp:setProperty property="memname" name="member"/>
	<jsp:setProperty property="mempwd" name="member"/>
	<jsp:setProperty property="memage" name="member"/>
	<jsp:setProperty property="mileage" name="member"/>
	<jsp:setProperty property="job" name="member"/>
	<jsp:setProperty property="addr" name="member"/>
	 --%>
	 
	<%-- 
	 form태그의 name 속성에 있는 변수명과 빈 객체(DTO)의 변수 이름을 비교하여
	 이름이 같으면 아래와 같이 * (와일드 카드)를 작성 시 해당 빈의 멤버변수에 자동으로 값을 설정하라는 의미를 가진다
  	--%>
	  
	 <jsp:setProperty property="*" name="member"/>
	 
	<div align="center">
		<hr width="50%" color="red">
			<h3> <jsp:getProperty property="memname" name="member"/>
				님의 회원 정보 </h3>
		<hr width="50%" color="red">
		<br>
		<table border="1" cellspacing="0">
			<tr>
				<th>회원 아이디</th>
				<td> <jsp:getProperty property="memid" name="member"/>
			</tr>
			
			<tr>
				<th>회원 비밀번호</th>
				<td> <jsp:getProperty property="mempwd" name="member"/>
			</tr>
			
			<tr>
				<th>회원 이름</th>
				<td> <jsp:getProperty property="memname" name="member"/>
			</tr>
			
			<tr>
				<th>회원 나이</th>
				<td> <jsp:getProperty property="memage" name="member"/>
			</tr>
			
			<tr>
				<th>회원 마일리지</th>
				<td> <jsp:getProperty property="mileage" name="member"/>
			</tr>
			
			<tr>
				<th>회원 직업</th>
				<td> <jsp:getProperty property="job" name="member"/>
			</tr>
			
			<tr>
				<th>회원 아이디</th>
				<td> <jsp:getProperty property="addr" name="member"/>
			</tr>
		</table>
	</div>
	
</body>
</html>