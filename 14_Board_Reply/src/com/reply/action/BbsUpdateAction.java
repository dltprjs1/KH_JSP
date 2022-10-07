package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String db_pwd = request.getParameter("db_pwd").trim();
		int board_no = Integer.parseInt(request.getParameter("board_no").trim());
		String board_writer = request.getParameter("board_writer").trim();
		String board_title = request.getParameter("board_title").trim();
		String board_cont = request.getParameter("board_cont").trim();
		String board_pwd = request.getParameter("board_pwd").trim();
		
		BbsDAO dao = BbsDAO.getinstance();
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		ActionForward forward = new ActionForward();
		
		int res = dao.bbsUpdate(dto,db_pwd);
		PrintWriter out = response.getWriter();
		if(res > 0){
			
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해 주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('수정에 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}
