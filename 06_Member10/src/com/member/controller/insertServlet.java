package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/insertOk")
public class insertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public insertServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String memid = request.getParameter("mem_id").trim();
		String memname = request.getParameter("mem_name").trim();
		String mempwd = request.getParameter("mem_pwd").trim();
		int memage = Integer.parseInt(request.getParameter("mem_age").trim());
		int memmileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String memjob = request.getParameter("mem_job").trim();
		String memaddr = request.getParameter("mem_addr").trim();
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		dto.setMemid(memid);
		dto.setMemname(memname);
		dto.setPwd(mempwd);
		dto.setAge(memage);
		dto.setMileage(memmileage);
		dto.setJob(memjob);
		dto.setAddr(memaddr);
		
		int check = dao.insert(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원 등록 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 등록 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
