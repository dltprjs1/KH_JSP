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

@WebServlet("/content.do")
public class ContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ContentServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전체 회원 목록에서 회원 이름을 클릭했을 때, get 방식으로 넘어온 회원번호에 해당하는 회원의 전체정보를
		// 데이터베이스에서 조회한 후, 해당 회원의 정보를 view page로 이동시키는 비지니스 로직
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		
		MemberDTO cont = dao.getcontentMember(num);
		
		request.setAttribute("content", cont);
		
		RequestDispatcher rd =  request.getRequestDispatcher("view/member_content.jsp");
		
		rd.forward(request, response);
		
	}

}
