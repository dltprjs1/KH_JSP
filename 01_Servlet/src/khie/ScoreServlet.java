package khie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ScoreServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int mat = Integer.parseInt(request.getParameter("mat"));
		int java = Integer.parseInt(request.getParameter("java"));
		String grade = "";
		int sum = kor+eng+mat+java;
		double avg =sum/4.0;
		if(avg >=90) {
			grade = "A학점";
		}else if(avg >= 80) {
			grade = "B학점";
		}else if(avg >= 70) {
			grade = "C학점";
		}else if(avg >= 60) {
			grade = "D학점";
		}else if(avg < 60) {
			grade = "F학점";
		}
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head></head>");
		out.println("<body>");
		out.println("<h2>학생 성적 정보</h2>");
		
		out.println("<table border='1' cellspacing='0'>");
		
		out.println("<tr>");
		out.println("<th> 이 름 </th>");
		out.println("<td>"+name+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 국 어 </th>");
		out.println("<td>"+kor+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 영 어 </th>");
		out.println("<td>"+eng+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 수 학 </th>");
		out.println("<td>"+mat+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 자 바 </th>");
		out.println("<td>"+java+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 총 점 </th>");
		out.println("<td>"+sum+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 평 균 </th>");
		out.println("<td>"+avg+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<th> 학 점 </th>");
		out.println("<td>"+grade+"</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

}
