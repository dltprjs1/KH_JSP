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

@WebServlet("/board_updateok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateOkServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		String board_writer = request.getParameter("board_writer").trim();
		String board_title = request.getParameter("board_title").trim();
		String baord_content = request.getParameter("board_content").trim();
		String board_pwd = request.getParameter("board_pwd").trim();
		
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(baord_content);
		dto.setBoard_pwd(board_pwd);
		
		int res = dao.update(dto);
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('글 수정 성공!')");
			out.println("location.href='board_content.do?num="+dto.getBoard_no()+"'");
			out.println("</script>");
		}else if(res == -1){
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다. 확인해 주십시오')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글 수정 실패! 다시 시도해 주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
