package com.upload.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.upload.action.Action;
import com.upload.action.ActionForward;

public class FrontController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void service( HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		// 한글 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// .do파일 불러오기
		
		String uri = request.getRequestURI();				// 프로젝트명+파일명 반환
		String path = request.getContextPath();				// 프로젝트명 반환
		String command = uri.substring(path.length()+1);	// 파일명	반환
		
		System.out.println("uri : "+uri);
		System.out.println("path : "+path);
		System.out.println("command : "+command);
		
		Action action = null;
		ActionForward forward = null;
		Properties pro = new Properties();	
		/*
		 * java.util.properties 클래스
		 *  - Properties 클래스는 HashTable의 하위 클래스
		 *  - 보통은 환경변수 및 속성 값을 Properties 파일에 저장하여 쉽게 접근할 수 있는 장점이 있다.
		 *  - Properties 파일은 일련의 키(key) - 값(value)의 한 쌍으로 이루어져 있다.
		 *  - 보통은 파일에 저장하여 사용을 하고, 파일 이름을 *.properties 이름으로 끝난다.
		 *  - FileInputStream 클래스에 Properties 파일을 인자로 넣어서 그 스트림으로부터
		 *    파일을 가져올때 많이 사용된다. 인자로 들어온 Properties 파일을 일게 됨.
		 *  - 읽어 들일 때 사용하는 메서드는 load()라는 메서드를 이용하여 파일을 읽어 들이게 된다.
		 */
			FileInputStream fis = new FileInputStream("C:\\NCS\\workspace(jsp)\\15_Board_FileUpload\\src\\com\\upload\\controller\\mapping.properties");
			
			pro.load(fis);
			
			String value = pro.getProperty(command);
			System.out.println("Value : " +value);
			
			if(value.substring(0,7).equals("execute")) {
				StringTokenizer st = new StringTokenizer(value,"|");
				// value값에 "|" 기호 단위로 잘라서 반환
				String url_1 = st.nextToken(); // "execute"
				String url_2 = st.nextToken(); // "패키지명.클래스명"
				/*
				 * 동적 객체 생성 : newInstance()
				 *   - Class 객체를 이용하면 new 연산자의 사용 없이 동적으로 객체 생성이 가능하다.
				 *   
				 *   - 코드 작성 시에 클래스의 이름을 결정할 수 없고, 런타임(실행) 시에 클래스의
				 *     이름이 결정되는 경우에 유용하게 사용할 수 있다.
				 *     
				 *   - newInstance() 메서드는 기본생성자를 호출해서 객체를 생성하기 때문에 반드시
				 *     클래스에 기본생성자가 존재해야 한다, 예외가 발생할 수 있는데 해당 클래스가
				 *     추상클래스이거나 인터페이스일 경우 발생하고, 또 하나의 예외는 클래스의
				 *     생성자가 접근제한자로 인해 접근할 수 없는 경우에 발생한다.
				 *     따라서, 예외 처리를 해 주어야한다.
				 *     
				 *   - 반환 타입은 Object 타입이므로 형변환 작업이 필요하지만, 클래스의 타입을 모르는
				 *     상태이므로 형변환을 해줄 수가 없어 이러한 문제를 해결하기 위해 인터페이스를 사용
				 *     
				 *   - Class.forNmae(class 이름)은 파라미터로 받은 class 이름에 해당하는 클래스를
				 *     로딩한 후에, 그 클래스에 해당하는 인스턴스를 리턴해 준다.
				 *     newInstace() 메서드는 로딩한 클래스의 객체를 생성하는 메서드이고, 
				 *     이렇게 생성된 객체를 동적 객체 생성이라고 부른다.
				 */
				
				try {
					Class<?> url = Class.forName(url_2);
					// 첫번째 동적 객체 생성 방법(deptrcated - 더이상 사용되지 않는 방법)
					//action = (Action)url.newInstance();
					
					// 두번째 동적 객체 생성 방법
					// getContructor() : 파라미터가 없는 기본생성자를 가져오는 메서드
					Constructor<?> constructor = url.getConstructor();
					action = (Action)constructor.newInstance();
					
					forward = action.execute(request, response);
					// action 클래스 실행 후 forward로 값(redirect , path) 반환
					
					System.out.println("forward : "+forward);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else { // value 값이 "execute"가 아닌 경우
				// view page로 이동
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath(value);
			}
			System.out.println("forward.getpath() : "+forward.getPath());
			if(forward != null) { 
				if(forward.isRedirect()) { // isredirect 가 true인 경우 
					response.sendRedirect(forward.getPath());
				}else {					   // isredirect 가 false인 경우
					RequestDispatcher rd = request.getRequestDispatcher(forward.getPath());
					rd.forward(request, response);
				}
			}
	} // service() 메서드 end
}
