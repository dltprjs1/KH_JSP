package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get 방식으로 넘어온 글 번호에 해당하는 게시글을 조회하여 해당 게시글의 상세내역을 답변글 폼
		// 페이지로 이동시키는 비지니스 로직
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		BbsDAO dao = BbsDAO.getinstance();
		dao.bbsHit(board_no);
		BbsDTO content = dao.getBbsContent(board_no);
		
		
		// 상세내역을 view page로 이동시키기
		request.setAttribute("reply",content);
		request.setAttribute("board_no",board_no);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_reply.jsp");
		return forward;
		
	}

}
