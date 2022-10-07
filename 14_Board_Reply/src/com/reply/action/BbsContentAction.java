package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// get방식으로 넘어온 글번호에 해당하는 게시글을 DB에서 조회하여 해당 상세내역을 view page로 
		// 이동시키는 비지니스 로직
		
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		BbsDAO dao = BbsDAO.getinstance();
		dao.bbsHit(board_no);
		BbsDTO content = dao.getBbsContent(board_no);
		
		
		// 상세내역을 view page로 이동시키기
		request.setAttribute("Cont",content);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_content.jsp");
		
		return forward;
	}

}
