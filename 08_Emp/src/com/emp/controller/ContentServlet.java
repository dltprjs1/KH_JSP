package com.emp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.medel.EmpDAO;
import com.emp.medel.EmpDTO;

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		EmpDAO dao = EmpDAO.getinstance();
		EmpDTO content = dao.Content(num);
		request.setAttribute("content",content);
		RequestDispatcher rd = request.getRequestDispatcher("view/content.jsp");
		
		rd.forward(request, response);
		
	}

}
