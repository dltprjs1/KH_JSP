package com.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberDAO;
import com.member.model.MemberDTO;

public class MemberDeleteAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		int num = Integer.parseInt(request.getParameter("num").trim());
		
		MemberDAO dao = MemberDAO.getinstance();
		
		int res = dao.delete(num);
		PrintWriter out = response.getWriter();
		
		if(res>0) {
			dao.updateseq(num);
			out.println("<script>");
			out.println("alert('삭제되었습니다.')");
			out.println("location.href='select.do'");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
