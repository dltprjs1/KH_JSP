package com.member.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.*;


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
		
		if(command.equals("select.do")) {
			
			action = new MemberListAction();
			
		}else if(command.equals("insert.do")) {
			
			action = new MemberInsertAction();
			
		}else if(command.equals("insert_ok.do")) {
			
			action = new MemberInsertOkAction();
			
		}else if(command.equals("content.do")) {
			
			action = new MemberContentAction();
			
		}else if(command.equals("update.do")) {
			
			action = new MemberUpdateAction();
			
		}else if(command.equals("update_ok.do")) {
			
			action = new MemberUpdateOkAction();
			
		}else if(command.equals("delete.do")) {
			
			action = new MemberDeleteAction();
			
		}
		
		String path1 = action.execute(request, response);
		//path1 : view/member_list.jsp;
		
		RequestDispatcher rd = request.getRequestDispatcher(path1);
		
		rd.forward(request,response);
		
	}

}
