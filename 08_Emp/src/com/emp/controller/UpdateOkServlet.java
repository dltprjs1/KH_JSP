package com.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.medel.EmpDAO;
import com.emp.medel.EmpDTO;

@WebServlet("/update_ok.do")
public class UpdateOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateOkServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int emp_num = Integer.parseInt(request.getParameter("emp_num").trim());
		String emp_name = request.getParameter("emp_name").trim();
		String emp_job = request.getParameter("emp_job").trim();
		int emp_mgr = Integer.parseInt(request.getParameter("emp_mgr").trim());
		String emp_hiredate = request.getParameter("emp_hiredate").trim();
		int emp_sal = Integer.parseInt(request.getParameter("emp_sal").trim());
		int emp_comm = Integer.parseInt(request.getParameter("emp_comm").trim());
		int emp_deptno = Integer.parseInt(request.getParameter("emp_deptno").trim());
		
		EmpDTO dto = new EmpDTO();
		EmpDAO dao = EmpDAO.getinstance();
		
		dto.setEmpno(emp_num);
		dto.setEname(emp_name);
		dto.setJob(emp_job);
		dto.setMgr(emp_mgr);
		dto.setHiredate(emp_hiredate);
		dto.setSal(emp_sal);
		dto.setComm(emp_comm);
		dto.setDeptno(emp_deptno);
		
		int res = dao.updatetok(dto);
		
		PrintWriter out = response.getWriter();
		if(res > 0) {
			out.println("<script>");
			out.println("alert('사원 수정 성공')");
			out.println("location.href='content.do?num="+dto.getEmpno()+"'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('사원 수정 실패')");
			out.println("history.back()");
			out.println("</script>");
		}	
	}

}
