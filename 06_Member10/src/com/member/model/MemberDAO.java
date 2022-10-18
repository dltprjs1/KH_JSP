package com.member.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	
	Connection con=null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql =null;
	
	public MemberDAO() { //기본 생성자
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "@@Cwoo7848";
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// 기본 생성자 end
	
	// MEMBER10 테이블에서 회원 전체 목록을 조회하는 메서드
	public List<MemberDTO> getMemberList() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			sql = "select * from member10 order by num desc";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));
				
				list.add(dto);
			}
			
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}	//getMemberList() 메서드 end
	

	public int insert(MemberDTO dto) {
		int result = 0 , count = 0;
		try {
			
			sql = "select max(num) from member10";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);		//max(num)값을 받기 위해서 getInt함수 사용 인덱스는 1
			}
			sql = "insert into member10 values(?,?,?,?,?,?,sysdate,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1,count+1);
			st.setString(2,dto.getMemid());
			st.setString(3,dto.getMemname());
			st.setString(4,dto.getPwd());
			st.setInt(5,dto.getAge());
			st.setInt(6,dto.getMileage());
			st.setString(7,dto.getAddr());
			st.setString(8,dto.getJob());
			
			result = st.executeUpdate();
			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	//insert() 메서드 end

	public MemberDTO getcontentMember(int num) {
		MemberDTO dto = null;
		try {
			sql = "select * from member10 where num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			rs = st.executeQuery();
			if(rs.next()) {
				dto = new MemberDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setMemid(rs.getString("memid"));
				dto.setMemname(rs.getString("memname"));
				dto.setPwd(rs.getString("pwd"));
				dto.setAge(rs.getInt("age"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setJob(rs.getString("job"));
				dto.setAddr(rs.getString("addr"));
				dto.setRegdate(rs.getString("regdate"));	
			
			}
			rs.close();
			st.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
		
	}

	public int update(MemberDTO dto) {
		int result =0;
		try {
			sql="select * from member10 where num =?";
			st = con.prepareStatement(sql);
			st.setInt(1,dto.getNum());
			rs = st.executeQuery();
			
			if(rs.next()) {
				if(dto.getPwd().equals(rs.getString("pwd"))){
					//수정 폼 페이지에서 입력한 비밀번호와 회원목록 폼 페이지에 있는 비밀번호와 같다면
					sql ="update member10 set age = ? , mileage = ? , job = ? , addr = ? where num = ?";
					
					st = con.prepareStatement(sql);
					st.setInt(1,dto.getAge());
					st.setInt(2,dto.getMileage());
					st.setString(3,dto.getJob());
					st.setString(4,dto.getAddr());
					st.setInt(5,dto.getNum());
					
					result = st.executeUpdate();
					
				} else {
					// 비밀번호가 회원목록 폼 페이지에 있는 비밀번호와 같지 않다면
					result=-1;
				}
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}	//update() 메서드 end

	public int delete(int num) {
		int result = 0;
		try {
			sql = "delete from member10 where num = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			result = st.executeUpdate();
			//st.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}	//delete() 메서드 end

	public void updatenum(int num) {
		
		try {
			sql = "update member10 set num = num -1 where num > ?";
			st = con.prepareStatement(sql);
			st.setInt(1, num);
			st.executeUpdate();
			
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	// updatenum() 메서드 end
}
