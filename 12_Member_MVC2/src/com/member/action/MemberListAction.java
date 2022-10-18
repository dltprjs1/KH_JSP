package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// MEMBER10 테이블에 있는 회원 전체 리스트를 
		// 조회하여 view page로 이동시키는 비지니스 로직
		
		MemberDAO dao = MemberDAO.getinstance();
		
		List<MemberDTO> memberList = dao.getMemberList();
		
		request.setAttribute("List",memberList);
		
		return "view/member_list.jsp";
	}

}
