package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//게시글 작성 폼 페이지에서 넘어온 데이터들을 DB에 저장하는 비지니스 로직
		String board_writer = request.getParameter("board_writer").trim();
		String board_title = request.getParameter("board_title").trim();
		String board_cont = request.getParameter("board_cont").trim();
		String board_pwd = request.getParameter("board_pwd").trim();
		System.out.println("asd");
		BbsDAO dao = BbsDAO.getinstance();
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_writer(board_writer);
		dto.setBoard_title(board_title);
		dto.setBoard_cont(board_cont);
		dto.setBoard_pwd(board_pwd);
		
		int res = dao.insertBbs(dto);
		PrintWriter out = response.getWriter();
		ActionForward forward = new ActionForward();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('등록하였습니다.')");
			out.println("</script>");
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		}else {
			out.println("<script>");
			out.println("alert('등록 실패하였습니다.')");
			out.println("</script>");
			forward.setRedirect(false);
			forward.setPath("view/bbs_write.jsp");

		}
		return forward;
	}
}
