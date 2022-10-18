package com.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/board_content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		
		BoardDTO content = dao.Content(num);
		dao.boardHit(num);
		request.setAttribute("content", content);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/board_content.jsp");
		rd.forward(request, response);
	}
}
