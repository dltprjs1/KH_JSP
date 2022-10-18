<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String id = request.getParameter("id").trim();
	String pwd = request.getParameter("pwd").trim();
	
	
	// 원래는 DB의 회원 관리 테이블에서 입력한 아이디와 비밀번호가 맞는지 확인 절차를 거쳐 
	// 회원이면 메인페이지로 이동한다.
	
	String dbid = "hong";
	String dbpwd = "1234";

	if(id.equals(dbid)){
		if(pwd.equals(dbpwd)){
			
			// 회원일 경우 메인 페이지로 이동 ==> 페이지 이동
			// 정보를 이동하는 페이지(메인페이지)로 전달하는 방법
			request.setAttribute("name", "홍길동");
			request.setAttribute("addr", "서울특별시 중구 남대문로");
			//setAttribute() : 정보를 저장하는 메서드	,	name = "홍길동";
			
			RequestDispatcher rd = request.getRequestDispatcher("Ex07.jsp");
			//RequsetDispatcher : 경로를 지정해 저장해 놓은 정보를 이동시키는 객체
			//getRequestDispatcher() : 경로를 지정하는 함수
			
			rd.forward(request, response);
			// forward() : getRequestDispatcher()로 지정된 경로로 저장된 정보를 이동시키는 함수
		}
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>