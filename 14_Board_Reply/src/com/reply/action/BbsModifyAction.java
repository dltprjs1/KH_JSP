package com.reply.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.reply.model.BbsDAO;
import com.reply.model.BbsDTO;

public class BbsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		BbsDAO dao = BbsDAO.getinstance();
		dao.bbsHit(board_no);
		BbsDTO content = dao.getBbsContent(board_no);
		
		
		// 상세내역을 view page로 이동시키기
		request.setAttribute("Cont",content);
		request.setAttribute("board_no",board_no);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("view/bbs_update.jsp");
		return forward;
	}

}
