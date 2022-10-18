package com.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.*;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		
		// 한글 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// getRequestURI() : "/프로젝트명/파일명(*.do)" 라는 문자열을 반환해주는 메서드
		String uri = request.getRequestURI(); // 결과값 : /12_Member_MVC2/select.do
		System.out.println("uri : " + uri);
		
		// getContextPath() : 현재 프로젝트명을 문자열로 반환해 주는 메서드
		String path = request.getContextPath(); // 결과값 : /12_Member_MVC2
		System.out.println("path : " + path);
		
		String command = uri.substring(path.length() + 1); // 결과값 : select.do
		System.out.println("command : "+command);
		
		Action action = null;
		String viewPage = null;
		
		if(command.equals("board_list.do")) {
			
			action = new BoardListAction();
			action.execute(request, response);
			viewPage = "view/board_list.jsp";
			
		}else if(command.equals("board_write.do")) {
			
			viewPage = "view/board_write.jsp";
			
		}else if(command.equals("board_write_ok.do")) {
			
			action = new BoardWriteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_content.do")) {
			
			action = new BoardContentAction();
			action.execute(request, response);
			viewPage = "view/board_content.jsp";
			
		}else if(command.equals("board_delete.do")) {
			
			action = new BoardContentAction();
			action.execute(request, response);
			int board_no = Integer.parseInt(request.getParameter("no").trim());
			int page = Integer.parseInt(request.getParameter("page").trim());
			request.setAttribute("board_no",board_no);
			request.setAttribute("page",page);
			viewPage = "view/board_delete.jsp";
			
		}else if(command.equals("board_update.do")) {
			
			action = new BoardContentAction();
			action.execute(request, response);
			int board_no = Integer.parseInt(request.getParameter("no").trim());
			int page= Integer.parseInt(request.getParameter("page").trim());
			request.setAttribute("board_no",board_no);
			request.setAttribute("page",page);
			viewPage = "view/board_updateok.jsp";
			
		}else if(command.equals("board_updateok.do")) {
			
			action = new BoardUpdateOkAction();
			action.execute(request, response);
			
		}else if(command.equals("board_deleteok.do")){
			
			action = new BoardDeleteOkAction();
			action.execute(request, response);
		}else if(command.equals("board_search.do")) {
			action = new BoardSearchAction();
			action.execute(request, response);
			viewPage = "view/board_search.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
}
