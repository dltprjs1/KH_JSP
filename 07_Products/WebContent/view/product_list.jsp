<%@page import="com.products.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<ProductDTO> list = (List<ProductDTO>)request.getAttribute("plist");
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
			<h3>Products 전체 목록</h3>
		<hr width="50%" color="pink">
		<br> <br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>제품No.</th> <th>카테고리 Code</th> <th>제풍명</th>
				<th>제조사</th>
			</tr>
			
			<%
				if(list.size() != 0){
					for(int i=0;i<list.size();i++){
						ProductDTO dto = list.get(i);
			%>
				<tr>
					<td><%=dto.getPnum() %></td>
					<td><%=dto.getCategory_fk() %></td>
					<td>
						<a href="<%=request.getContextPath() %>/content.do?pnum=<%=dto.getPnum() %>">
								 <%=dto.getProducts_name() %></a>
					</td>
					<%
						if(dto.getCompany()== null){
					%>
						<td> </td>
					<%	}else{
						
					%>	<td><%=dto.getCompany() %></td>
					
					<%	}   %>
					
				</tr>
			<%		}	//for문의 end
				}else{	//제품의 목록이 없다면
			%>	
				<tr>
					<td colspan="4" align="center">
						<h3>전체 제품 목록이 없습니다.</h3>
					</td>
				</tr>
			<%	}
			%>
				<tr>
					<td colspan="4" align="right">
						<input type="button" value="상품등록" onclick="location.href='<%= request.getContextPath() %>/insert.do'">
					</td>
				</tr>
		</table>		
	</div>
</body>
</html>