package com.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql = null;
	public MemberDAO() {	}
	
	private static MemberDAO instance = null;
	
	public static MemberDAO getinstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	
	public void openConn() {
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥셔을 찾는다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여 커넥션을 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void closeConn(ResultSet rs, PreparedStatement st, Connection con) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(st != null) {
				st.close();
			}
			if(con != null) {
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberDTO> getMemberList() {
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		openConn();
		sql = "select * from member10";
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return list;
	}

	public int insertMember(MemberDTO dto) {
		int result= 0 , count = 0;
		openConn();
		try {
			sql="select max(num) from member10";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			sql="insert into member10 values(?,?,?,?,?,?,sysdate,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1,count);
			st.setString(2,dto.getMemid());
			st.setString(3,dto.getMemname());
			st.setString(4,dto.getPwd());
			st.setInt(5,dto.getAge());
			st.setInt(6,dto.getMileage());
			st.setString(7,dto.getAddr());
			st.setString(8,dto.getJob());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
		
	}

	public MemberDTO Membercontent(int num) {
		MemberDTO dto = new MemberDTO();
		openConn();
		sql="select * from member10 where num=?";
		try {
			st=con.prepareStatement(sql);
			st.setInt(1,num);
			rs = st.executeQuery();
			if(rs.next()) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return dto;
	}

	public int updateok(MemberDTO dto, String db_pwd) {
		int result = 0;
		openConn();
			try {
				if(db_pwd.equals(dto.getPwd())) {
				sql = "update member10 set memid=? , memname=? , age=? , mileage=? , addr=? , job=? where num=?";
				st = con.prepareStatement(sql);
				st.setString(1,dto.getMemid());
				st.setString(2,dto.getMemname());
				st.setInt(3,dto.getAge());
				st.setInt(4,dto.getMileage());
				st.setString(5,dto.getAddr());
				st.setString(6,dto.getJob());
				st.setInt(7,dto.getNum());
				result = st.executeUpdate();
				}else {
					result = -1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
			return result;
	}

	public int delete(int num) {
		int result=0;
		openConn();
		try {
			sql="delete from member10 where num=?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	return result;
	}

	public void updateseq(int num) {
		openConn();
		try {
			sql = "update member10 set num=num-1 where num > ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}
}

