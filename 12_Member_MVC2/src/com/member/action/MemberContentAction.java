package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberContentAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int num = Integer.parseInt(request.getParameter("num").trim());
		MemberDAO dao =MemberDAO.getinstance();
		MemberDTO content = dao.Membercontent(num);
		request.setAttribute("content",content);
		RequestDispatcher rd = request.getRequestDispatcher("view/member_content.jsp");
		rd.forward(request, response);
		return null;
	}

}
