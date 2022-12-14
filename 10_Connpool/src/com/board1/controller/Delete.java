package com.board1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board1.model.BoardDAO;
import com.board1.model.BoardDTO;

@WebServlet("/delete.do")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Delete() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int board_no = Integer.parseInt(request.getParameter("num").trim());
		String board_pwd = request.getParameter("board_pwd").trim();
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_pwd(board_pwd);
		int res = dao.delete(dto);
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			dao.boardnoudate(dto);
			out.println("<script>");
			out.println("alert('삭제 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다. 확인해 주십시오!')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
