package com.board.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardUpdateOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			int board_no = Integer.parseInt(request.getParameter("board_no").trim());
			int page = Integer.parseInt(request.getParameter("page").trim());
			String db_pwd = request.getParameter("db_pwd").trim();
			String board_writer = request.getParameter("board_write").trim();
			String board_title = request.getParameter("board_title").trim();
			String board_cont = request.getParameter("board_cont").trim();
			String board_pwd = request.getParameter("board_pwd").trim();
			
			BoardDAO dao = BoardDAO.getinstance();
			BoardDTO dto = new BoardDTO();
			
			dto.setBoard_no(board_no);
			dto.setBoard_writer(board_writer);
			dto.setBoard_title(board_title);
			dto.setBoard_cont(board_cont);
			dto.setBoard_pwd(board_pwd);
			
			int res = dao.UpdateOk(dto,db_pwd);
			PrintWriter out = response.getWriter();
			
			if(res > 0) {
				dao.updateno(board_no);
				out.println("<script>");
				out.println("alert('수정하였습니다.')");
				out.println("location.href='board_content.do?no="+board_no+"&page="+page+"'");
				out.println("</script>");
			}else if(res == -1) {
				out.println("<script>");
				out.println("alert('비밀번호를 확인해 주십시오.')");
				out.println("history.back()");
				out.println("</script>");
			}else {
				out.println("<script>");
				out.println("alert('수정실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
			}
	}
}
