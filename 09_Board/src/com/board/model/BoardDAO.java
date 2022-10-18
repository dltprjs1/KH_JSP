package com.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String user = "web";
		String password = "@@Cwoo7848";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
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

	public List<BoardDTO> select() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		openConn();
		try {
			sql="select * from board order by board_no desc";
			st = con.prepareStatement(sql);
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

	public int insert(BoardDTO dto) {
		openConn();
		int result = 0, count = 0;
		try {
			sql = "select max(board_no) from board";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			if(rs.next()) {
				count = (rs.getInt(1)+1);
				sql = "insert into board values(?,?,?,?,?,default,sysdate,'')";
				st = con.prepareStatement(sql);
				st.setInt(1,count);
				st.setString(2,dto.getBoard_writer());
				st.setString(3,dto.getBoard_title());
				st.setString(4,dto.getBoard_cont());
				st.setString(5,dto.getBoard_pwd());
				result = st.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public BoardDTO Content(int num) {
		BoardDTO dto = null;
		openConn();
		try {
			sql = "select * from board where board_no = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			rs = st.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
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

	public void boardHit(int num) {
		openConn();
		try {
			sql="update board set board_hit = board_hit + 1 where board_no = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	public int update(BoardDTO dto) {
		openConn();
		int result = 0;
		try {
			sql = "select * from board where board_no=?";
			st = con.prepareStatement(sql);
			st.setInt(1,dto.getBoard_no());
			rs = st.executeQuery();
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))) {
					sql="update board set board_writer=? , board_title=? , board_cont=? , board_update = sysdate where board_no=?";
					st = con.prepareStatement(sql);
					st.setString(1,dto.getBoard_writer());
					st.setString(2,dto.getBoard_title());
					st.setString(3,dto.getBoard_cont());
					st.setInt(4,dto.getBoard_no());
					
					result = st.executeUpdate();
				}else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
		
	}

	public int delete(BoardDTO dto) {
		openConn();
		int result = 0;
		try {
			sql = "select * from board where board_no=?";
			st = con.prepareStatement(sql);
			st.setInt(1,dto.getBoard_no());
			rs = st.executeQuery();
			if(rs.next()) {
				if(dto.getBoard_pwd().equals(rs.getString("board_pwd"))) {
					sql="delete from board where board_no = ?";
					st = con.prepareStatement(sql);
					st.setInt(1,dto.getBoard_no());
					result = st.executeUpdate();
				}else {
					result = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public void updatesequence(BoardDTO dto) {
		openConn();
		try {
			sql="update board set board_no = board_no - 1 where board_no > ?";
			st = con.prepareStatement(sql);
			st.setInt(1,dto.getBoard_no());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
	}

	public List<BoardDTO> searchBoard(String find_field, String find_name) {
		openConn();
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
			if(find_field.equals("title")) {
				try {
				sql="select * from board where board_title like ? order by board_no desc";
					st = con.prepareStatement(sql);
					st.setString(1,"%"+find_name+"%");
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
			}else if(find_field.equals("cont")){
				try {
				sql="select * from board where board_cont like ? order by board_no desc";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+find_name+"%");
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
			}else if(find_field.equals("title_cont")) {
				try {
				sql="select * from board where board_title like ? or board_cont like ? order by board_no desc";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+find_name+"%");
				st.setString(2,"%"+find_name+"%");
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
				sql="select * from board where board_writer like ? order by board_no desc";
				st = con.prepareStatement(sql);
				st.setString(1,"%"+find_name+"%");
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
