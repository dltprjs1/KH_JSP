package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberUpdateOkAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		MemberDAO dao = MemberDAO.getinstance();
		int mem_num = Integer.parseInt(request.getParameter("num").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		String mem_id = request.getParameter("mem_id").trim();
		String mem_name = request.getParameter("mem_name").trim();
		String mem_pwd = request.getParameter("mem_pwd").trim();
		int mem_age = Integer.parseInt(request.getParameter("mem_age").trim());
		int mem_mileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String mem_job = request.getParameter("mem_job").trim();
		String mem_addr = request.getParameter("mem_addr").trim();
		
		MemberDTO dto = new MemberDTO();
		dto.setNum(mem_num);
		dto.setMemid(mem_id);
		dto.setMemname(mem_name);
		dto.setPwd(mem_pwd);
		dto.setAge(mem_age);
		dto.setMileage(mem_mileage);
		dto.setJob(mem_job);
		dto.setAddr(mem_addr);
		
		int res = dao.updateok(dto,db_pwd);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("location.href='content.do?num="+dto.getNum()+"'");
			out.println("</script>");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해 주십시오')");
			out.println("location.href='content.do?num="+dto.getNum()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return null;
	}

}
