<%@page import="com.products.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ProductDTO content = (ProductDTO)request.getAttribute("content");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lightgreen">
			<h3>제품 상세 보기</h3>
		<hr width="50%" color="lightgreen">
		<br> <br>
	
		<table border="1" cellspacing="0" width="400">
			<%
				if(content != null){	//데이터가 있다면
			%>
			<tr>
				<th>제품No.</th>
				<td><%= content.getPnum() %></td>
			</tr>
			<tr>
				<th>제품 카테고리</th>
				<td><%= content.getCategory_fk() %></td>
			</tr>
			<tr>
				<th>제품명</th>
				<td><%= content.getProducts_name() %></td>
			</tr>
			<tr>
				<th>제품코드</th>
				<td><%= content.getEp_code_fk() %></td>
			</tr>
			<tr>
				<th>제품 입고가</th>
				<td><%= content.getInput_price() %></td>
			</tr>
			<tr>
				<th>제품 출고가</th>
				<td><%= content.getOutput_price() %></td>
			</tr>
			<tr>
				<th>제품 배송비</th>
				<td><%= content.getTrans_cost() %></td>
			</tr>
			<tr>
				<th>제품 마일리지</th>
				<td><%= content.getMileage() %></td>
			</tr>
			<tr>
				<th>제품 제조사</th>
				<%
					if(content.getCompany() == null){
				%>		<td> </td>
				<%	}else{ %>
					<td> <%=content.getCompany() %></td>
				<% } %>
			</tr>
			<% } %>
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="제품 수정" 
					onclick="location.href='<%=request.getContextPath() %>/update.do?pnum=<%=content.getPnum() %>'">
				
					<input type="button" value="제품 삭제" 
					onclick="if(confirm('정말로 제품을 삭제 하시겠습니까?')){
						location.href='<%= request.getContextPath() %>/delete.do?pnum=<%=content.getPnum() %>'
						}else { retrun; }">
					
					<input type="button" value="제품 목록" 
					onclick="location.href='select.do'">
					
				</td>
			</tr>
		</table>
	</div>
</body>
</html>