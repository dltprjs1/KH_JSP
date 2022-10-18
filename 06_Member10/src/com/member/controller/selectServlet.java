package com.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

@WebServlet("/select.do")
public class selectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public selectServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 : MEMBER10 테이블의 전체 회원 목록을 화면에 출력하기위해 클라이언트가 요청
		// 응답 : DB에서 MEMBER10 테이블의 회원 전체 목록을 조회하여 해당 전체 리스트를 
		//		 view page로 넘겨주면 된다.
		
		// 1단계 : 오라클 데이터베이스와 연동 작업
		MemberDAO dao = new MemberDAO();
		
		// 2단계 : MEMBER10 테이블의 회원 전체 목록을 조회해야 한다.
		List<MemberDTO> memberList = dao.getMemberList();
		
		// 3단계 : DB에서 가져온 정보를 view page로 이동시키기
		request.setAttribute("member",memberList);	//memberList에는 주소값이 저장
		
		// 4단계 : 페이지 이동을 진행하기
		RequestDispatcher rd = 
		request.getRequestDispatcher("view/member_list.jsp");
									// view폴더 밑 member_list.jsp파일로 이동
		rd.forward(request, response);
	}

}
