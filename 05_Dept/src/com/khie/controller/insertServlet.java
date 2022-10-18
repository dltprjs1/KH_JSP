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

@WebServlet("/insertOk")
public class insertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public insertServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// insert.jsp 페이지에서 넘어온 데이터들을 DB에 저장시키는 비지니스 로직
		
		//한글 인코딩 처리 작업
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 : 부서등록 폼 페이지에서 넘어온 데이터들을 받아주기
		int deptno = Integer.parseInt(request.getParameter("deptNo").trim());
		
		String deptName = request.getParameter("deptName").trim();
		
		String location = request.getParameter("deptLocation").trim();
		
		// 2단계 : DTO 객체를 이용하여 DB에 데이터를 전송해야 한다.
		DeptDTO dto = new DeptDTO();
		
		dto.setDeptno(deptno);
		dto.setDname(deptName);
		dto.setLoc(location);
		
		// 3단계 : DB에 DTO 객체를 전송
		DeptDAO dao = new DeptDAO();
		int res = dao.insertDept(dto);
		System.out.println("servlet에서 dto 주소 : "+dto);
		
		PrintWriter out = response.getWriter();
		
		if(res > 0) {	//부서 추가가 성공한 경우
			out.println("<script>");
			out.println("alert('부서 추가 성공!')");
			out.println("location.href='select'");
			out.println("</script>");
		}else { // 부서 추가가 실패한 경우
			out.println("<script>");
			out.println("alert('부서 추가 실패!')");
			out.println("history.back()"); // 이전 페이지로 이동
			out.println("</script>");
		}
	}
}
