package com.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			int num = Integer.parseInt(request.getParameter("num").trim());
			MemberDAO dao = MemberDAO.getinstance();
			MemberDTO dto = dao.Membercontent(num);
			request.setAttribute("update",dto);
			
		return "view/member_update.jsp";
	}
}
