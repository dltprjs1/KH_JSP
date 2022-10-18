package com.khie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/delete")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public deleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//삭제 버튼을 누르면 get방식으로 넘어온 부서 번호를 가지고 DB에서 해당 부서번호를 삭제하는 비지니스 로직
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 삭제 버튼을 눌렀을 때 get 방식으로 넘어온 부서번호를 받아야 한다.
		int deptno = Integer.parseInt(request.getParameter("deptno").trim());
		
		// 2단계 : 삭제할 부서번호를 DB에 넘겨 주어야 한다.
		DeptDAO dao = new DeptDAO();
		int res = dao.deleteDept(deptno);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {	//부서 삭제이 성공한 경우
			out.println("<script>");
			out.println("alert('부서 삭제 성공!')");
			out.println("location.href='select'");
			out.println("</script>");
		}else { 		// 부서 삭제이 실패한 경우
			out.println("<script>");
			out.println("alert('부서 삭제 실패!')");
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>");
		}
		// 3단계 : 
		
	}

}
