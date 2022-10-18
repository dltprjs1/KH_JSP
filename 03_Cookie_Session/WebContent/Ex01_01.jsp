<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	Cookie[] cookies = request.getCookies();
	// 쿠키는 배열로 반환 받는다.	

	if(cookies != null){
		for(int i=0;i<cookies.length;i++){
			out.println("cookies["+i+"] name : "+ cookies[i].getName()+"<br>");
			out.println("cookies["+i+"] value : "+ cookies[i].getValue()+"<br>");
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