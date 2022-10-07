package com.reply.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//답변글 폼 페이지에서 넘어온 데이터들을 데이터베이스에 저장하는 비지니스 로직
		
		String reply_writer = request.getParameter("reply_board_writer").trim();
		String reply_title = request.getParameter("reply_board_title").trim();
		String reply_cont = request.getParameter("reply_board_cont").trim();
		String reply_pwd = request.getParameter("reply_board_pwd").trim();
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int reply_group = Integer.parseInt(request.getParameter("group").trim());
		int reply_step = Integer.parseInt(request.getParameter("step").trim());
		int reply_indent = Integer.parseInt(request.getParameter("indent").trim());
		
		BbsDAO dao = BbsDAO.getinstance();
		BbsDTO dto = new BbsDTO();
		
		dto.setBoard_no(board_no);
		dto.setBoard_writer(reply_writer);
		dto.setBoard_title(reply_title);
		dto.setBoard_cont(reply_cont);
		dto.setBoard_pwd(reply_pwd);
		dto.setBoard_group(reply_group);
		dto.setBoard_step(reply_step);
		dto.setBoard_indent(reply_indent);
		
		// 만약에 원글에 기존에 작성했던 답변글이 존재하는 경우
		// 해당 답변글에 step을 하나 증가 시켜주는 메서드 호출
		dao.replyUpdate(reply_group,reply_step);
		
		// 답변글을 DB에 저장하는 메서드를 호출
		int res = dao.replyBbs(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("bbs_list.do");
		}else {
			out.println("<script>");
			out.println("alert('게시물 답변글 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}
}
