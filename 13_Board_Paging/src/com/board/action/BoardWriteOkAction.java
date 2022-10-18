package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardWriteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String board_writer = request.getParameter("board_writer").trim();
		String board_title = request.getParameter("board_title").trim();
		String board_cont = request.getParameter("board_cont").trim();
		String board_pwd = request.getParameter("board_pwd").trim();
		
		BoardDAO dao = BoardDAO.getinstance();
		BoardDTO dto = new BoardDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		int res = dao.boardwrite(dto);
		PrintWriter out = response.getWriter();
		if(res > 0) {
			out.println("<script>");
			out.println("alert('등록 되었습니다')");
			out.println("location.href='board_list.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('등록 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}
