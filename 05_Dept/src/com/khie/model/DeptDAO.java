package com.khie.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * DAO(Data Access Object)
 *  - 데이터 접근 객체 ==> DB에 접속(연동)하는 객체
 *  - DAO란 데이터베이스에 접속해서 데이터 추가, 수정, 삭제, 조회 등의 작업을 하는 클래스
 *  - 일반적으로 JSP 또는 Servlet에서 위의 작업들을 같이 사용할 수 있지만, 유지보수, 코드의 모듈화 등을 위해서
 *    DAO 클래스를 따로 만들어서 사용을 한다.
 */
	
public class DeptDAO {
	
	// DB와 연동하는 객체
	Connection con=null;
	
	// DB에 SQL문을 전송하는 객체
	PreparedStatement st = null;
	
	// SQL문을 실행한 후에 결과 값을 가지고 있는 객체 
	ResultSet rs = null;
	
	// 쿼리문을 저장할 변수
	String sql = null;
	
	public DeptDAO() {	//기본 생성자
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "@@Cwoo7848";
		
		try {
			// 1단계 : 오라클 드라이버를 메모리로 로딩
			Class.forName(driver);
			// 2단계 : 오라클 데이터베이스와 연결 작업 진행
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	// 기본생성자 end
	
	// selectList() 메서드 만들기
	// DEPT 테이블에서 부서 목록 전체 리스트 조회하는 메서드
	public List<DeptDTO> selectList() {
		
		List<DeptDTO> list = new ArrayList<DeptDTO>(); // 다형성
		
		try {
		// 3단계 : 데이터베이스에 전송할 SQL문 작성
		sql = "select * from dept order by deptno";
		
		// 4단계 : SQL문을 데이터베이스에 전송객체에 저장
			st = con.prepareStatement(sql);
			
		// 5단계 : SQL문을 데이터베이스에 전송 및 실행
			rs = st.executeQuery();
			
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				list.add(dto);
			}
			
			//6단계 : 연결되어 있던 자원 종료하기
			rs.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // selectList() 메서드 end
	
	// DEPT 테이블에 부서를 추가하는 메서드
	public int insertDept(DeptDTO dto) {
		System.out.println("dao에서 dto 주소 : "+dto);
		int result = 0;
		try {
		// 3단계 : 데이터베이스에 전송할 SQL문 작성
		sql = "insert into dept values(?,?,?)";
		
		// 4단계 : SQL문을 데이터베이스 전송 객체에 저장
			st = con.prepareStatement(sql);
		
		//?(플레이스 홀더)에 데이터 배정
			st.setInt(1,dto.getDeptno());
			st.setString(2,dto.getDname());
			st.setString(3,dto.getLoc());
			
		// 5단계 : SQL문을 데이터베이스에 전송 및 실행
			result = st.executeUpdate();
			
		// 6단계 : 연결되어 있던 자원 종료하기
			st.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}// insetDept() 메서드 end
	
	// DB에 수정된 정보를 저장하는 메서드
	public int updateDept(DeptDTO dto) {
		int result = 0;
		
		try {
		// 3단계 : 데이터베이스에 전송할 SQL문 작성
			sql = "update dept set dname = ? , loc = ? where deptno = ?";
			
			
		// 4단계 : SQL문을 데이터베이스 전송 객체에 저장
			st = con.prepareStatement(sql);
		// ?(플레이스 홀더)에 저장될 정보를 설정
			st.setString(1,dto.getDname());
			st.setString(2,dto.getLoc());
			st.setInt(3,dto.getDeptno());
			
			
		// 5단계 : SQL문을 데이터베이스에 전송 및 실행
			result = st.executeUpdate();
			
			
		// 6단계 : 연결되어 있는 자원 종료
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} // updateDept() 메서드 end
	
	// 매개변수로 넘어온 부서번호에 해당하는 부서를 삭제하는 메서드
	public int deleteDept(int deptno) {
		int result = 0;
		
		try {
		// 3단계 : 데이터베이스에 전송할 SQL문 작성
			sql = "delete from dept where deptno = ?";
		
		// 4단계 : SQL문을 데이터베이스 전송 객체에 저장
			st = con.prepareStatement(sql);
			
		//?(플레이스 홀더) 데이터를 저장
			st.setInt(1,deptno);
			
		// 5단계 : SQL문을 데이터베이스에 전송 및 실행
			result = st.executeUpdate();
		
		// 6단계 : 사용되었던 자원 종료시키기
			st.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}	//deleteDept() 메서드 end
	
	
}
