package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

@WebServlet("/insert.do")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String writer = request.getParameter("writer").trim();
		String title = request.getParameter("title").trim();
		String content = request.getParameter("content").trim();
		String pwd = request.getParameter("pwd").trim();
		
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(writer);
		dto.setBoard_title(title);
		dto.setBoard_cont(content);
		dto.setBoard_pwd(pwd);
		
		int res = dao.insert(dto);
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('글 작성 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글 작성 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
