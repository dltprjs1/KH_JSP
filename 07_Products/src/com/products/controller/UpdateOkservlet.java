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

@WebServlet("/update_ok.do")
public class UpdateOkservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateOkservlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("product_num").trim());
		String product_category = request.getParameter("product_category").trim();
		String product_name = request.getParameter("product_name").trim();
		String product_code = request.getParameter("product_code").trim();
		int product_input = Integer.parseInt(request.getParameter("product_input").trim());
		int product_output = Integer.parseInt(request.getParameter("product_output").trim());
		int product_trans = Integer.parseInt(request.getParameter("product_trans").trim());
		int product_mileage = Integer.parseInt(request.getParameter("product_mileage").trim());
		String product_company = request.getParameter("product_company").trim();
		
		ProductDAO dao = ProductDAO.getInstance();
		ProductDTO dto = new ProductDTO();
		
		dto.setPnum(num);
		dto.setCategory_fk(product_category);
		dto.setProducts_name(product_name);
		dto.setEp_code_fk(product_code);
		dto.setInput_price(product_input);
		dto.setOutput_price(product_output);
		dto.setMileage(product_mileage);
		dto.setTrans_cost(product_trans);
		dto.setCompany(product_company);
		
		int res = dao.update(dto);
		PrintWriter out = response.getWriter();
		if(res > 0) {
			out.println("<script>");
			out.println("alert('제품 수정 성공')");
			out.println("location.href='content.do?pnum="+dto.getPnum()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('제품 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}		
	}
}
