package com.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/update.do")
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public updateServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 회원번호에 해당하는 회원의 정보를 조회해서 수정 폼 페이지로 이동시키는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		// MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		MemberDTO cont = dao.getcontentMember(num);
		
		request.setAttribute("modify", cont);
		
		RequestDispatcher rd = request.getRequestDispatcher("view/member_update.jsp");
		
		rd.forward(request, response);
		
	}

}
