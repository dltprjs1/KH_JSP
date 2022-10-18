package com.products.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.products.model.CategoryDTO;
import com.products.model.ProductDAO;
import com.products.model.ProductDTO;

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductDAO dao = ProductDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("pnum").trim());
		
		ProductDTO update = dao.updatebefore(num);
		
		List<CategoryDTO> categoryList = dao.getCategoryList();
		
		request.setAttribute("update", update);
		
		request.setAttribute("List",categoryList);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/product_update.jsp");
		rd.forward(request, response);
		
	}

}
