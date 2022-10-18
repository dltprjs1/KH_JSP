package com.board1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

@WebServlet("/content.do")
public class Content extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Content() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		BoardDAO dao = BoardDAO.getinstance();
		dao.updatehit(num);
		BoardDTO content = dao.content(num);
		request.setAttribute("content",content);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/content.jsp");
		rd.forward(request, response);
	}

}
