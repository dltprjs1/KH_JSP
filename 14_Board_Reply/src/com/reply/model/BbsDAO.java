package com.reply.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




public class BbsDAO {
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql = null;
	
	public BbsDAO() {	}
	
	private static BbsDAO instance = null;
	
	public static BbsDAO getinstance() {
		if(instance == null) {
			instance = new BbsDAO();
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

	public List<BbsDTO> getBbsList() {
		List<BbsDTO> list = new ArrayList<BbsDTO>();
		openConn();
		try {
			sql = "select * from jsp_bbs order by board_group desc, board_step";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				BbsDTO dto = new BbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return list;
	}

	public int insertBbs(BbsDTO dto) {
		openConn();
		int result=0,count=0;
		try {
			sql = "select max(board_no) from jsp_bbs";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			sql = "insert into jsp_bbs values(?,?,?,?,?,default,sysdate,'',?,0,0)";
			st = con.prepareStatement(sql);
			st.setInt(1,count);
			st.setString(2,dto.getBoard_writer());
			st.setString(3,dto.getBoard_title());
			st.setString(4,dto.getBoard_cont());
			st.setString(5,dto.getBoard_pwd());
			st.setInt(6,count);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public void bbsHit(int board_no) {
		openConn();
		try {
			sql="update jsp_bbs set board_hit = board_hit+1 where board_no = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,board_no);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	public BbsDTO getBbsContent(int board_no) {
		BbsDTO dto = new BbsDTO();
		openConn();
		try {
			sql = "select * from jsp_bbs where board_no = ? ";
			st = con.prepareStatement(sql);
			st.setInt(1,board_no);
			rs = st.executeQuery();
			if(rs.next()) {
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				dto.setBoard_group(rs.getInt("board_group"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_indent(rs.getInt("board_indent"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return dto;
	}

	public int bbsUpdate(BbsDTO dto, String db_pwd) {
		openConn();
		int result=0;
			try {
				if(db_pwd.equalsIgnoreCase(dto.getBoard_pwd())) {
				sql="update jsp_bbs set board_writer=? , board_title=? , board_cont=? , board_update=sysdate where board_no=?";
				st = con.prepareStatement(sql);
				st.setString(1,dto.getBoard_writer());
				st.setString(2,dto.getBoard_title());
				st.setString(3,dto.getBoard_cont());
				st.setInt(4,dto.getBoard_no());
				result = st.executeUpdate();
				}else {
					result=-1;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		return result;
	}

	public void replyUpdate(int reply_group, int reply_step) {
		openConn();
		try {
			sql="update jsp_bbs set board_step=board_step +1 where board_group=? and board_step > ?";
			st = con.prepareStatement(sql);
			st.setInt(1,reply_group);
			st.setInt(2,reply_step);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	// jsp_bbs 테이블의 게시글 원글에 답변글을 추가하는 메서드
	public int replyBbs(BbsDTO dto) {
		int result=0,count=0;
		openConn();
		try {
		sql = "select max(board_no) from jsp_bbs";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			sql="insert into jsp_bbs values(?,?,?,?,?,default,sysdate,'',?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1,count);
			st.setString(2,dto.getBoard_writer());
			st.setString(3,dto.getBoard_title());
			st.setString(4,dto.getBoard_cont());
			st.setString(5,dto.getBoard_pwd());
			st.setInt(6,dto.getBoard_group());
			st.setInt(7,dto.getBoard_step()+1); 	// 답변글을 작성하면 1 하나더 작성하면 기존의 step1은 step2가된다.
			st.setInt(8,dto.getBoard_indent()+1);	// 들여쓰기
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}
}
