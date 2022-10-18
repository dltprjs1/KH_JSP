package com.emp.medel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql = null;
	
	private static EmpDAO instance = null;
	
	public EmpDAO() {	}
	
	public static EmpDAO getinstance() {
		if (instance == null) {
			instance = new EmpDAO();
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
	} // openConn() 메서드 end
	
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
	} // closeConn() 메서드 end

	public List<EmpDTO> select() {
		openConn();
		List<EmpDTO> list = new ArrayList<EmpDTO>();
		try {
			sql = "select * from emp order by empno";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
				
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		
		return list;
	}

	public List<String> getJobList() {
		List<String> list = new ArrayList<String>();
		try {
			openConn();
			sql = "select distinct(job) from emp order by job";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				String job = rs.getString("job");
				list.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return list;
	}

	public List<EmpDTO> getMgrList() {
		openConn();
		List<EmpDTO> mgrlist = new ArrayList<EmpDTO>();
		try {
			sql = "select * from emp where empno in (select distinct(mgr) from emp) ";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				EmpDTO dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				mgrlist.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}	
		return mgrlist;
	}

	public List<DeptDTO> getDeptList() {
		openConn();
		List<DeptDTO> list = new ArrayList<DeptDTO>();
		try {
			sql="select * from dept order by deptno";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				DeptDTO dto = new DeptDTO();
				dto.setDeptno(rs.getInt("deptno"));
				dto.setDname(rs.getString("dname"));
				dto.setLoc(rs.getString("loc"));
				
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		
		return list;
		
	}

	public int insertok(EmpDTO dto) {
		openConn();
		int result=0;
		try {
			sql="insert into emp values(?,?,?,?,sysdate,?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1,dto.getEmpno());
			st.setString(2,dto.getEname());
			st.setString(3,dto.getJob());
			st.setInt(4,dto.getMgr());
			st.setInt(5,dto.getSal());
			st.setInt(6,dto.getComm());
			st.setInt(7,dto.getDeptno());
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public EmpDTO Content(int num) {
		openConn();
		EmpDTO dto = null;
		try {
			sql="select * from emp where empno = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			rs = st.executeQuery();
			if(rs.next()) {
				dto = new EmpDTO();
				dto.setEmpno(rs.getInt("empno"));
				dto.setEname(rs.getString("ename"));
				dto.setJob(rs.getString("job"));;
				dto.setMgr(rs.getInt("mgr"));
				dto.setHiredate(rs.getString("hiredate"));
				dto.setSal(rs.getInt("sal"));
				dto.setComm(rs.getInt("comm"));
				dto.setDeptno(rs.getInt("deptno"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return dto;
	}

	public int updatetok(EmpDTO dto) {
		openConn();
		int result = 0;
		System.out.println(dto.getEmpno());
		try {
			sql="update emp set ename=? , job=? , mgr=? , hiredate=? , sal=? , comm=? , deptno=? where empno=?";
			st = con.prepareStatement(sql);
			st.setString(1,dto.getEname());
			st.setString(2,dto.getJob());
			st.setInt(3,dto.getMgr());
			st.setString(4,dto.getHiredate());
			st.setInt(5,dto.getSal());
			st.setInt(6,dto.getComm());
			st.setInt(7,dto.getDeptno());
			st.setInt(8,dto.getEmpno());
			result = st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public int delete(int num) {
		openConn();
		int result=0;
		try {
			sql = "delete from emp where empno = ?";
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
}
