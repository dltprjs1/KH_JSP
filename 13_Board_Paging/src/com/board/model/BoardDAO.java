package com.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql = null;
	public BoardDAO() {	}
	
	private static BoardDAO instance = null;
	
	public static BoardDAO getinstance() {
		if(instance == null) {
			instance = new BoardDAO();
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

	public int getBoardCount() {
		int result = 0;
		openConn();
		try {
			sql = "select count(*) from board";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public List<BoardDTO> getBoardList(int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		openConn();
		// 해당 페이지에서 시작번호
		int startNo = (page * rowsize) - (rowsize - 1);
		
		// 해당 페이지에서 끝 번호
		int endNo = (page*rowsize);
		try {
			sql = "select * from (select row_number() over(order by board_no desc)rnum, b.* from board b) where rnum >= ? and rnum <= ?";
			st = con.prepareStatement(sql);
			st.setInt(1,startNo);
			st.setInt(2,endNo);
			rs = st.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_update(rs.getString("board_update"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return list;
	}

	public int boardwrite(BoardDTO dto) {
		int result=0, count=0;
		openConn();
		try {
			sql="select max(board_no) from board";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1)+1;
			}
			sql="insert into board values(?,?,?,?,?,default,sysdate,'')";
			st = con.prepareStatement(sql);
			st.setInt(1,count);
			st.setString(2,dto.getBoard_writer());
			st.setString(3,dto.getBoard_title());
			st.setString(4,dto.getBoard_cont());
			st.setString(5,dto.getBoard_pwd());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public void getBoardHit(int board_no) {
		openConn();
		try {
			sql = "update board set board_hit = board_hit+1 where board_no =?";
			st = con.prepareStatement(sql);
			st.setInt(1,board_no);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	public BoardDTO getBoardContent(int board_no) {
		openConn();
		BoardDTO dto = new BoardDTO();
		try {
			sql = "select * from board where board_no=?";
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return dto;
	}

	public int UpdateOk(BoardDTO dto, String db_pwd) {
		int result =0;
		openConn();
		if(dto.getBoard_pwd().equals(db_pwd)) {
			try {
				sql="update board set board_writer=?,board_title=?,board_cont=?,board_update=sysdate where board_no=?";
				st = con.prepareStatement(sql);
				st.setString(1,dto.getBoard_writer());
				st.setString(2,dto.getBoard_title());
				st.setString(3, dto.getBoard_cont());
				st.setInt(4,dto.getBoard_no());
				result = st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else {
			result=-1;
		}
		return result;
	}

	public int Delete(BoardDTO dto, String db_pwd) {
		int result=0;
		openConn();
		if(dto.getBoard_pwd().equals(db_pwd)) {
			try {
				sql="delete from board where board_no=?";
				st = con.prepareStatement(sql);
				st.setInt(1,dto.getBoard_no());
				result = st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else {
			result=-1;
		}
		return result;
	}

	public void updateno(int board_no) {
		openConn();
		try {
			sql="update board set board_no = board_no - 1 where board_no > ?";
			st = con.prepareStatement(sql);
			st.setInt(1,board_no);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	public int searchListCount(String search_field, String search_keyword) {
		int result =0;
		openConn();
		
		if(search_field.equals("title")) {
			try {
				sql="select count(*) from board where board_title like ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				rs = st.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else if(search_field.equals("cont")) {
			try {
				sql="select count(*) from board where board_cont like ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				rs = st.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else if(search_field.equals("title_cont")) {
			try {
				sql="select count(*) from board where board_title like ? or board_cont like ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				st.setString(2,"%"+search_keyword+"%");
				rs = st.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else {
			try {
				sql="select count(*) from board where board_writer like ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				rs = st.executeQuery();
				if(rs.next()) {
					result = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}
		return result;
	}

	public List<BoardDTO> searchBoardList(String search_field, String search_keyword, int page, int rowsize) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		openConn();
		
		// 해당 페이지에서 시작 번호
		int startNo = (page * rowsize) - (rowsize - 1);
		// 해당 페이지에서 마지막 번호
		int endNo = (page * rowsize);
		if(search_field.equals("title")) {
			try {
				sql="select * from(select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ?) where rnum > ? and rnum <= ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				st.setInt(2,startNo-1);
				st.setInt(3,endNo);
				rs = st.executeQuery();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else if(search_field.equals("cont")) {
			try {
				sql="select * from(select row_number() over(order by board_no desc) rnum, b.* from board b where board_cont like ?) where rnum > ? and rnum <= ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				st.setInt(2,startNo-1);
				st.setInt(3,endNo);
				rs = st.executeQuery();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else if(search_field.equals("title_cont")) {
			try {
				sql="select * from(select row_number() over(order by board_no desc) rnum, b.* from board b where board_title like ? or board_cont like ?) where rnum > ? and rnum <= ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				st.setString(2,"%"+search_keyword+"%");
				st.setInt(3,startNo-1);
				st.setInt(4,endNo);
				rs = st.executeQuery();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}else {
			try {
				sql="select * from(select row_number() over(order by board_no desc) rnum, b.* from board b where board_writer like ?) where rnum > ? and rnum <= ?";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+search_keyword+"%");
				st.setInt(2,startNo-1);
				st.setInt(3,endNo);
				rs = st.executeQuery();
				while(rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setBoard_no(rs.getInt("board_no"));
					dto.setBoard_writer(rs.getString("board_writer"));
					dto.setBoard_title(rs.getString("board_title"));
					dto.setBoard_cont(rs.getString("board_cont"));
					dto.setBoard_pwd(rs.getString("board_pwd"));
					dto.setBoard_hit(rs.getInt("board_hit"));
					dto.setBoard_date(rs.getString("board_date"));
					dto.setBoard_update(rs.getString("board_update"));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				closeConn(rs, st, con);
			}
		}
		return list;
	}
}