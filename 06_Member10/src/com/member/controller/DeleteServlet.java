package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;

@WebServlet("/delete.do")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteServlet() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get 방식으로 넘어온 회원 번호에 해당하는 회원을 MEMBER10 테이블에서 삭제하는 비지니스 로직
		
		response.setContentType("text/html; charset=UTF-8");
		
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.delete(num);
		
		// st.close(); con.close();로 드라이버 연결이 종료되어 하나의 서블릿에서 
		// 두개의 DB연결 메서드를 사용하려면 마지막 메서드에서만 st.close() , con.close()를 생성해야한다. 
		
		dao.updatenum(num);	//회원번호 재작업 , 반환받을 값이 없기 때문에 변수로 선언해 줄 필요가 없다.
		PrintWriter out = response.getWriter();
		
		if(result > 0) {
			out.println("<script>");
			out.println("alert('회원 삭제 성공!')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('회원 삭제 실패!')");
			out.println("history.back()");
			out.println("</script>");
		}
	}
}
