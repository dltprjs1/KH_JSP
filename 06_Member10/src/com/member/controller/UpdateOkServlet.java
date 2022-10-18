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

@WebServlet("/update_Ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateOkServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 수정 폼 페이지에서 넘어온 데이터들을 받아서 , DB에 MEMBER10 테이블의 회원 번호에 해당하는 회원의
		// 정보를 수정하는 비지니스 로직
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String memid = request.getParameter("mem_id").trim();
		String memname = request.getParameter("mem_name").trim();
		String mempwd = request.getParameter("mem_pwd").trim();
		int memage = Integer.parseInt(request.getParameter("mem_age").trim());
		int memmileage = Integer.parseInt(request.getParameter("mem_mileage").trim());
		String memjob = request.getParameter("mem_job").trim();
		String memaddr = request.getParameter("mem_addr").trim();
		
		// type="hidden"으로 넘어온 데이터도 받아주어야한다.
		
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		dto.setNum(num);
		dto.setMemid(memid);
		dto.setMemname(memname);
		dto.setPwd(mempwd);
		dto.setAge(memage);
		dto.setMileage(memmileage);
		dto.setJob(memjob);
		dto.setAddr(memaddr);
		
		int check = dao.update(dto);
		
		PrintWriter out = response.getWriter();
		
		if(check > 0) {
			out.println("<script>");
			out.println("alert('회원 정보 수정 성공!')");
			out.println("location.href='content.do?num="+dto.getNum()+"'");
			out.println("</script>");
		}else if (check == -1){	//비밀번호가 틀린 경우
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 정보 수정 실패!')");
			out.println("history.back()");
			out.println("</script>");

		}
	}

}
