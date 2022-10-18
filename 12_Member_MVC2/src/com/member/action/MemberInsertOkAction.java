package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberInsertOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//회원등록 폼 페이지에서 넘어온 데이터들을 MEMBER10 테이블에 회원으로 등록하는 비지니스 로직
		
		// 1단계 : 회원등록 폼 페이지에서 넘어온 데이터들을 받아 주기
		
		String mem_id = request.getParameter("mem_id");
		String mem_name = request.getParameter("mem_name");
		String mem_pwd = request.getParameter("mem_pwd");
		int mem_age = Integer.parseInt(request.getParameter("mem_age"));
		int mem_mileage = Integer.parseInt(request.getParameter("mem_mileage"));
		String mem_job = request.getParameter("mem_job");
		String mem_addr = request.getParameter("mem_addr");
	
		MemberDAO dao = MemberDAO.getinstance();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setMemid(mem_id);
		dto.setMemname(mem_name);
		dto.setPwd(mem_pwd);
		dto.setAge(mem_age);
		dto.setMileage(mem_mileage);
		dto.setJob(mem_job);
		dto.setAddr(mem_addr);
		
		int res = dao.insertMember(dto);
		
		PrintWriter out = response.getWriter();
		if(res>0) {
			out.println("<script>");
			out.println("alert('등록되었습니다.')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('등록 실패 되었습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
