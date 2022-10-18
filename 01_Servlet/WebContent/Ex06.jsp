<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
	[문제] 성적 처리 폼 페이지 만들기 
		  이름,국어점수,영어점수,수학점수,자바점수를 입력받을 수 있는 폼을 만들고 정보를
		  입력받아서 성적을 처리해여 웹 브라우저 화면에 출력해 보세요 
	-->
	
	<div>
		<form method="post" action="score">
			<table border="1" cellspacing="0" >
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name">
					</td>
				</tr>
				
				<tr>
					<th>국어점수</th>
					<td>
						<input type="text" name="kor">
					</td>
				</tr>
				
				<tr>
					<th>영어점수</th>
					<td>
						<input type="text" name="eng">
					</td>
				</tr>
				
				<tr>
					<th>수학점수</th>
					<td>
						<input type="text" name="mat">
					</td>
				</tr>
				
				<tr>
					<th>자바점수</th>
					<td>
						<input type="text" name="java">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="제출">&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>