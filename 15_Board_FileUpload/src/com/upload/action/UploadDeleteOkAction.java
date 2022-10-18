package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadDeleteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int upload_no = Integer.parseInt(request.getParameter("upload_no").trim());
		String db_pwd = request.getParameter("db_pwd").trim();
		String upload_pwd = request.getParameter("upload_pwd").trim();
		
		UploadDAO dao = UploadDAO.getinstance();
		UploadDTO dto = dao.content(upload_no);
		String upload = "C:\\NCS\\workspace(jsp)\\15_Board_FileUpload\\WebContent\\upload";
		String fileName = dto.getUpload_file();
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		
		int res = 0;
		if(upload_pwd.equals(dto.getUpload_pwd())) {
			res = dao.deleteUpload(upload_no);
			if (fileName != null) { 					// 첨부파일이 존재하는 경우
				File file = new File(upload+fileName) ;	// 경로+파일이름
				file.delete();
			}
		}else {
			res = -1;
		}
		if (res > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		}else if(res == -1) {
			out.println("<script>");
			out.println("alert('비밀번호를 확인해 주십시오.')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('삭제 실패 하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
