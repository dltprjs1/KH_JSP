<%@page import="com.products.model.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.products.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ProductDTO update =  (ProductDTO)request.getAttribute("update");
	List<CategoryDTO> list = (List<CategoryDTO>)request.getAttribute("List");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<hr width="50%" color="lightyellow">
			<h3>제품 수정하기</h3>
		<hr width="50%" color="lightyellow">
		<br> <br>
		<form method="post" action="<%=request.getContextPath() %>/update_ok.do">
		<input type="hidden" name="product_num" value="<%=update.getPnum() %>">
		
			<table border="1" cellspacing="0" width="400">
				<%
					if(update != null){	
				%>
				<%-- <tr>
					<th>제품No.</th>
					<td><input type="text" name="product_num" value="<%=update.getPnum()%>" readonly></td>
				</tr> --%>
				<tr>
					<th>제품 카테고리</th>
					<td><%-- <input type="text" name="product_category" value="<%=update.getCategory_fk()%>" readonly> --%>
						<select name="product_category">
							<%
								if(list.size() == 0){
							%>
							<option value="">:::카테고리 코드 없음:::</option>
							<% 	}else{
									for(int i=0;i<list.size();i++){
										CategoryDTO dto = list.get(i);
										
										if(dto.getCategory_code().equals(update.getCategory_fk())){
											
							%>
											<option
											 value="<%=dto.getCategory_code() %>" selected>
												<%=dto.getCategory_name() %>[<%=dto.getCategory_code() %>]
											</option>
							<%			}else{
							%>				
											<option
											 value="<%=dto.getCategory_code() %>" disabled>
												<%=dto.getCategory_name() %>[<%=dto.getCategory_code() %>]
											</option>
							<%			}
								}
							}
							%>
							
						</select>
					</td>
				</tr>
				<tr>
					<th>제품명</th>
					<td><input type="text" name="product_name" value="<%=update.getProducts_name()%>"></td>
				</tr>
				<tr>
					<th>제품코드</th>
					<td><input type="text" name="product_code" value="<%=update.getEp_code_fk()%>"></td>
				</tr>
				<tr>
					<th>제품 입고가</th>
					<td><input type="text" name="product_input" value="<%=update.getInput_price()%>"></td>
				</tr>
				<tr>
					<th>제품 출고가</th>
					<td><input type="text" name="product_output" value="<%=update.getOutput_price()%>"></td>
				</tr>
				<tr>
					<th>제품 배송비</th>
					<td><input type="text" name="product_trans" value="<%=update.getTrans_cost()%>"></td>
				</tr>
				<tr>
					<th>제품 마일리지</th>
					<td><input type="text" name="product_mileage" value="<%=update.getMileage()%>"></td>
				</tr>
				<tr>
					<th>제품 제조사</th>
					<td><input type="text" name="product_company" value="<%=update.getCompany()%>"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input type="submit" value="수정하기">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
					</td>
				</tr>
				<% } %>
			</table>
		</form>
	</div>
</body>
</html>