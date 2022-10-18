package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardDeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		String board_pwd = request.getParameter("board_pwd").trim();
		String db_pwd = request.getParameter("db_pwd").trim();
		System.out.println("board_pwd : "+board_pwd);
		System.out.println("db_pwd : "+db_pwd);
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		dto.setBoard_no(board_no);
		dto.setBoard_pwd(board_pwd);
		int res = dao.Delete(dto,db_pwd);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('삭제하였습니다.')");
			out.println("location.href='board_list.do?page'");
			out.println("</script>");
			dao.updateno(board_no);
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해 주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
