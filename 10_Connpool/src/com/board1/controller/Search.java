package com.board1.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

@WebServlet("/search.do")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Search() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String find = request.getParameter("find").trim();
		String name = request.getParameter("find_name").trim();
		BoardDAO dao = BoardDAO.getinstance();
		
		List<BoardDTO> list = dao.search(find,name);
		request.setAttribute("search", list);
		RequestDispatcher rd = request.getRequestDispatcher("view/search.jsp");
		rd.forward(request, response);
		
	}	

}
