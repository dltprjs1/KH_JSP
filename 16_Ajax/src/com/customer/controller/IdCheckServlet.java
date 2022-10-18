package com.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;


@WebServlet("/idCheck.do")
public class IdCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public IdCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파라미터로 넘어온 아이디가 customer 테이블에
		// 등록되어 있는 아이디인지 여부를 확인하는 비지니스 로직.
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String user_id = 
			request.getParameter("id").trim();
		
		MemberDAO dao = MemberDAO.getInstance();
		
		String str = dao.idCheck(user_id);
		
		// 아이디 중복 체크 결과를 클라이언트(Ajax)로 전송.
		out.println(str);
	}

}
