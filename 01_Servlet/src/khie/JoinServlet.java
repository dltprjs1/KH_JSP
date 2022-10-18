package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청한 정보에 한글이 있으면 한글이 깨지는 형상이 발생하기도 한다
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 1단계 :Ex05.jsp 페이지에서 넘어온 정보 변수에 저장하기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		
		// 여러 개의 정보가 넘어올 경우
		String[] hobby = request.getParameterValues("hobby");
		
		// 2단계 : 웹 브라우저에 요청한 결과를 화면에 보여주기
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<table border='1' cellspacing='0'>");
		out.println("<tr>");
		out.println("<th>아이디<th>");
		out.println("<td>"+id+"<td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>비밀번호<th>");
		out.println("<td>"+pwd+"<td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>이름<th>");
		out.println("<td>"+name+"<td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>연락처<th>");
		out.println("<td>"+phone+"<td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>주소<th>");
		out.println("<td>"+addr+"<td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<th>취미<th>");
		out.println("<td>");
		
		for(int i=0; i<hobby.length;i++) {
			out.println(hobby[i]+"&nbsp;&nbsp;&nbsp;");
		}
		
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
