package com.upload.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.upload.model.UploadDAO;
import com.upload.model.UploadDTO;

public class UploadWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UploadDTO dto = new UploadDTO();
		
		/*
		 * 파일 업로드 시 설정
		 *  1. 파일 저장 경로 지정 
		 *  2. 첨부 파일 크기 지정
		 *  3. MultipartRequest 객체 생성 
		 */
		
		String saveFolder = "C:\\NCS\\workspace(jsp)\\15_Board_FileUpload\\WebContent\\upload";
		
		int fileSize =10 * 1024 * 1024; // 10MB
		// 1024 : 1KB , 1024 * 1024 : 1MB , 10* 1024 * 1024 : 10MB
		
		MultipartRequest mr = new MultipartRequest(request,			// 일반적인 request객체
												   saveFolder,		// 첨부파일이 저장될 경로
												   fileSize,		// 업로드할 첨부파일의 최대 크기
												   "UTF-8",			// 한글 인코딩
												   new DefaultFileRenamePolicy());	
												   // 파일의 이름이 같은 경우 중복제거 설정
		
		// multipartRequest 멤버로 getParameter 폼페이지 name을 받아야한다.
		// request.getParameter(x)
		
		String upload_writer = mr.getParameter("upload_writer").trim();
		String upload_title = mr.getParameter("upload_title").trim();
		String upload_cont = mr.getParameter("upload_cont").trim();
		String upload_pwd = mr.getParameter("upload_pwd").trim();
		
		// 자료실 폼 페이지에서 type="file"로 되어 있으면 getFile() 메서드로 받아야 한다.
		// java.io 패키지의 File 클래스 변수 선언
		File upload_file = mr.getFile("upload_file");
		
		if(upload_file != null) { // 첨부파일이 존재하는 경우
			// getName() 메서드를 이용하여 첨부파일의 이름 가져오기
			String fileName = upload_file.getName();
			
			// 날짜 객체 생성
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH)+1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			
			// .../upload/2022-10-11
			String homedir = saveFolder+"/"+year+"-"+month+"-"+day;
			
			// 날짜 폴더를 만들어 보자
			File path1 = new File(homedir);
			
			if(!path1.exists()) {	// 폴더가 존재하지 않는 경우
				path1.mkdir();		// mkr(make directory) : 실제 폴더를 만들어 주는 메서드
			}
			
			// 파일을 만들어 보자 ==> 예) 홍길동_파일명
			// .../upload/2022-10-11/홍길동_파일명
			String reFileName = upload_writer+"_"+fileName;
			upload_file.renameTo(new File(homedir+"/"+reFileName));
			
			// 실제로 DB에 저장되는 파일 이름 
			// "/2022-10-11/홍길동_파일명"으로 저장
			String fileDBName = "/"+year+"-"+month+"-"+day+"/"+reFileName;
			dto.setUpload_file(fileDBName);
		}
		dto.setUpload_writer(upload_writer);
		dto.setUpload_title(upload_title);
		dto.setUpload_cont(upload_cont);
		dto.setUpload_pwd(upload_pwd);
		
		UploadDAO dao = UploadDAO.getinstance();
		int res = dao.insertUpload(dto);
		ActionForward forward = new ActionForward();
		PrintWriter out = response.getWriter();
		if(res > 0) {
			forward.setRedirect(true);
			forward.setPath("upload_list.do");
		}else {
			out.println("<script>");
			out.println("alert('업로드 추가 실패 하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
