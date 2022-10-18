package com.khie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khie.model.DeptDAO;
import com.khie.model.DeptDTO;

@WebServlet("/select")
public class selectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public selectServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * index.jsp페이지에서 요청 ==> DEPT 테이블의 전체 부서 목록을 화면에 보여달라고 요청(비지니스 로직)
		 *    				   ==> 해당 요청에 대해서 응답
		 *    
		 *    1단계 : DB와 연결 작업 진행
		 *    1단계 시 준비 과정
		 *     - DAO(Data Access Object) 객체 준비 
		 *     - DTO(Data Transfer Object) 객체 준비
		 */
		
		DeptDAO dao = new DeptDAO();
		
		// 2단계 : DB에서 DEPT 테이블의 전체 목록 조회
		List<DeptDTO> deltList = dao.selectList();
		
		// 3단계 : 페이지 이동 작업 시 데이터를 같이 넘겨야 한다.
		request.setAttribute("List", deltList);
		
		// 4단계 : RequestDispatcher객체를 통해 이동할 페이지 설정
		RequestDispatcher rd = request.getRequestDispatcher("select.jsp");
		
		// 실제 페이지 이동이 진행되는 메서드
		rd.forward(request,response);
		
		
	
	}

	

}
