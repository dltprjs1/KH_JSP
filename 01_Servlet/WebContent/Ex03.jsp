<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="50%" color="pink">
			<h2>회원 정보 입력 폼</h2>
		<hr width="50%" color="pink">
		<br> <br>
		
		<form action="member">
			<table border="1" cellspacing="0">
				<tr>
					<th>회원 아이디</th>
					<td> <input type="text" name="memId">
				</tr>
				
				<tr>
					<th>회원 비밀번호</th>
					<td> <input type="password" name="memPwd">
				</tr>
				
				<tr>
					<th>회원 이름</th>
					<td><input type="text" name="memName">
				</tr>
				
				<tr>
					<th>회원 나이</th>
					<td><input type="text" name="memAge">
				</tr>
				
				<tr>
					<th>회원 연락처</th>
					<td><input type="text" name="memPhone">
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>