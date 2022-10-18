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

@WebServlet("/delete_ok.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		int board_no = Integer.parseInt(request.getParameter("num").trim());
		String board_pwd = request.getParameter("pwd").trim();
		
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_pwd(board_pwd);
		
		
		int res = dao.delete(dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			dao.updatesequence(dto);
			out.println("<script>");
			out.println("alert('글 삭제 성공!')");
			out.println("location.href='select.do?num="+dto.getBoard_no()+"'");
			out.println("</script>");
		}else if(res == -1){
			out.println("<script>");
			out.println("alert('비밀번호가 다릅니다. 확인해 주십시오')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('글 삭제 실패! 다시 시도해 주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
