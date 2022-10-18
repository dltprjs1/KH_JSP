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
		<h2>회원 가입 폼 페이지</h2>
			
		<form method="post" action="Ex04_01.jsp">
			<table border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td> <input type="text" name="id"> </td>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td> <input type="password" name="pwd"> </td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td> <input type="text" name="name"> </td>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td> <input type="text" name="phone"> </td>
				</tr>
				
				<tr>
					<th>거주지</th>
					<td> 
						<select name="addr">
							<option value="">::선택하세요</option>
							<option value="서울">서울</option>
							<option value="부산">부산</option>
							<option value="대전">대전</option>
							<option value="대구">대구</option>
							<option value="광주">광주</option>
						</select>
					</td>
				</tr>
					
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입">&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>