package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")		//action="member" webServlet(action의 값)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MemberServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ex03.jsp 폼 페이지에서 넣어온 정보들을 받아 주자
		
		String id = request.getParameter("memId");
		String pwd = request.getParameter("memPwd");
		String name = request.getParameter("memName");
		String age = request.getParameter("memAge");
		String phone = request.getParameter("memPhone");
		
		System.out.println("회원 아이디 : "+ id);
		System.out.println("회원 비밀번호 : "+ pwd);
		System.out.println("회원 이름 : "+ name);
		System.out.println("회원 나이 : "+ age);
		System.out.println("회원 연락처 : "+ phone);
		
		//응답 시 한글 깨짐 방지
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<div align='center'>");
		out.println("<h2>회원정보</h2>");
		out.println("<table border='1' cellspacing='0'>");
		out.println("<tr>");
		out.println("<th>회원 아이디</th>");
		out.println("<td>"+id+"</td>");		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>회원 비밀번호</th>");
		out.println("<td>"+pwd+"</td>");		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>회원 이름</th>");
		out.println("<td>"+name+"</td>");		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>회원 나이</th>");
		out.println("<td>"+age+"</td>");		
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>회원 연락처</th>");
		out.println("<td>"+phone+"</td>");		
		out.println("</tr>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
