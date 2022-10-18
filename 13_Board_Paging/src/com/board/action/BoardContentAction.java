package com.board.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.model.BoardDAO;
import com.board.model.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)throws UnsupportedEncodingException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		int board_no = Integer.parseInt(request.getParameter("no").trim());
		int page = Integer.parseInt(request.getParameter("page").trim());
		
		BoardDAO dao = BoardDAO.getinstance();
		
		// 글제목 클릭시 조회수 증가시키는 메서드 호출
		dao.getBoardHit(board_no);
		
		// 글번호에 해당하는 게시글의 상세내역을 조회하는 메서드 호출
		BoardDTO content = dao.getBoardContent(board_no);
		request.setAttribute("Content",content);
		request.setAttribute("Page",page);
		
	}

}
