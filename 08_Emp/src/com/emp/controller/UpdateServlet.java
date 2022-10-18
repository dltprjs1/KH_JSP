package com.emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.medel.DeptDTO;
import com.emp.medel.EmpDAO;
import com.emp.medel.EmpDTO;

@WebServlet("/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		EmpDAO dao = EmpDAO.getinstance();
		EmpDTO dto = new EmpDTO();
		
		EmpDTO content = dao.Content(num);
		List<String> joblist =  dao.getJobList();
		List<EmpDTO> mgrlist = dao.getMgrList();
		List<DeptDTO> deptlist = dao.getDeptList();
		
		request.setAttribute("content",content);
		request.setAttribute("joblist",joblist );
		request.setAttribute("mgrlist",mgrlist );
		request.setAttribute("deptlist",deptlist );
		
		RequestDispatcher rd = request.getRequestDispatcher("view/UpdateOk.jsp");
		rd.forward(request, response);
	}

}
