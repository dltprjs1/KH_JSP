package com.products.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

@WebServlet("/insert_ok.do")
public class insertOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public insertOkServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 제품 등록 폼 페이지에서 넘어온 데이터들들 DB의 products 테이블에 저장하는 비지니스 로직
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ProductDTO dto = new ProductDTO();
		ProductDAO dao = ProductDAO.getInstance();
		
		String product_category = request.getParameter("product_category");
		String product_name = request.getParameter("product_name");
		String product_code = request.getParameter("product_code");
		int product_input = Integer.parseInt(request.getParameter("product_input"));
		int product_output = Integer.parseInt(request.getParameter("product_output"));
		int product_transcost = Integer.parseInt(request.getParameter("product_transcost"));
		int product_mileage =Integer.parseInt(request.getParameter("product_mileage"));
		String product_company = request.getParameter("product_company");
		
		dto.setCategory_fk(product_category);
		dto.setProducts_name(product_name);
		dto.setEp_code_fk(product_code);
		dto.setInput_price(product_input);
		dto.setOutput_price(product_output);
		dto.setTrans_cost(product_transcost);
		dto.setMileage(product_mileage);
		dto.setCompany(product_company);
		
		int res = dao.insertProduct(dto);
		
		PrintWriter out = response.getWriter();
		if(res > 0) {
			out.println("<script>");
			out.println("alert('제품 등록 성공')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('제품 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}		
	}

}
