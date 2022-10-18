package com.upload.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class UploadDAO {
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql = null;
	
	public UploadDAO() {	}
	
	private static UploadDAO instance = null;
	
	public static UploadDAO getinstance() {
		if(instance == null) {
			instance = new UploadDAO();
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

	public List<UploadDTO> getUploadList() {
		List<UploadDTO> list = new ArrayList<UploadDTO>();
		openConn();
		try {
			sql = "select * from upload order by upload_no desc";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				UploadDTO dto = new UploadDTO();
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return list;
	}

	public int insertUpload(UploadDTO dto) {
		openConn();
		int result = 0,count = 0;
		try {
			sql = "select max(upload_no) from upload";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			sql = "insert into upload values(?,?,?,?,?,?,default,sysdate,'')";
			st = con.prepareStatement(sql);
			st.setInt(1,count);
			st.setString(2,dto.getUpload_writer());
			st.setString(3,dto.getUpload_title());
			st.setString(4,dto.getUpload_cont());
			st.setString(5,dto.getUpload_pwd());
			st.setString(6,dto.getUpload_file());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public void updateUpSeq(int upload_no) {
		openConn();
		try {
			sql="update upload set upload_hit=upload_hit+1 where upload_no=?";
			st = con.prepareStatement(sql);
			st.setInt(1,upload_no);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	public UploadDTO content(int upload_no) {
		UploadDTO dto = new UploadDTO();
		openConn();
		try {
			sql="select * from upload where upload_no=?";
			st = con.prepareStatement(sql);
			st.setInt(1,upload_no);
			rs = st.executeQuery();
			if(rs.next()) {
				dto.setUpload_no(rs.getInt("upload_no"));
				dto.setUpload_writer(rs.getString("upload_writer"));
				dto.setUpload_title(rs.getString("upload_title"));
				dto.setUpload_cont(rs.getString("upload_cont"));
				dto.setUpload_pwd(rs.getString("upload_pwd"));
				dto.setUpload_file(rs.getString("upload_file"));
				dto.setUpload_hit(rs.getInt("upload_hit"));
				dto.setUpload_date(rs.getString("upload_date"));
				dto.setUpload_update(rs.getString("upload_update"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return dto;
	}

	public int updateUpload(UploadDTO dto, String db_pwd) {
		openConn();
		int result=0;
		if(db_pwd.equals(dto.getUpload_pwd())) {
			if(dto.getUpload_file() == null) {
			try {
				sql="update upload set upload_writer=? , upload_title=? , upload_cont=? , upload_update=sysdate where upload_no=?";
				st = con.prepareStatement(sql);
				st.setString(1,dto.getUpload_writer());
				st.setString(2,dto.getUpload_title());
				st.setString(3,dto.getUpload_cont());
				st.setInt(4,dto.getUpload_no());
				result = st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
			}else { //수정폼 페이지에서 첨부파일을 선택한 경우
				sql = "update upload set upload_writer=? , upload_title=? , upload_cont=? , upload_file=? , upload_update=sysdate where upload_no=?";
				try {
					st = con.prepareStatement(sql);
					st.setString(1,dto.getUpload_writer());
					st.setString(2,dto.getUpload_title());
					st.setString(3,dto.getUpload_cont());
					st.setString(4,dto.getUpload_file());
					st.setInt(5,dto.getUpload_no());
					result = st.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					closeConn(rs, st, con);
				}
			}
		}else {
			result = -1;
		}
		return result;
	}

	public int deleteUpload(int upload_no) {
		openConn();
		int result = 0;
		try {
			sql = "delete from upload where upload_no=?";
			st = con.prepareStatement(sql);
			st.setInt(1,upload_no);
			result = st.executeUpdate();
			sql = "update upload set upload_no = upload_no - 1 where upload_no > ?";
			st = con.prepareStatement(sql);
			st.setInt(1,upload_no);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}
}
