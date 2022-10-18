package com.products.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// DAO 객체를 싱글턴 방식으로 사용 해 보자
public class ProductDAO {
	
	// ProductDAO 객체를 싱글턴 방식으로 만들어 보자
	// 1단계 : 싱글턴 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본생성자의 접근 제어자를 public이 아닌 private으로 바꾸어야 한다.
	//        즉, 외부에서 직접적으로 기본생성자를 호출하지 못하게 하는 방법이다.
	
	// 2단계 : ProductDAO객체를 정적(static) 멤버로 선언해 주어야 한다.
	
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance()메서드를 생성한 후
	//        getInstance()라는 메서드를 외부에서 접근 할 수 있도록 해 주어야 한다.
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	String sql = null;
	
	private static ProductDAO instance = null;	// 자료형이 productDAO인 static 변수 선언
	
	private ProductDAO() { }	// 기본 생성자
	
	public static ProductDAO getInstance() {
		if(instance == null) {
			instance = new ProductDAO();
		}
		return instance;
	}
	
	// DB를 연동하는 작업을 진행하는 메서드
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
	public void closeConn(PreparedStatement st, Connection con) {
		try {
			
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

	public List<ProductDTO> getProductList() {
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		openConn();		//데이터베이스 연결 작업이 들어 있는 메서드
		
		try {
			sql = "select * from products order by pnum desc";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProducts_name(rs.getString("products_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return list;
	} // getProductList() 메서드 end


	public List<CategoryDTO> getCategoryList() {
	
		List<CategoryDTO> category = new ArrayList<CategoryDTO>();
		openConn();
		
		try {
			sql="select * from category order by category_code";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				CategoryDTO dto = new CategoryDTO();
				dto.setCnum(rs.getInt("cnum"));
				dto.setCategory_code(rs.getString("category_code"));
				dto.setCategory_name(rs.getString("category_name"));
				category.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return category;
	}	//getCategoryList() 메서드 end

	public int insertProduct(ProductDTO dto) {
		int result =0 , count = 0;
		openConn();
		try {
				sql = "select max(pnum) from products";
				st = con.prepareStatement(sql);
				rs = st.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1)+1;
			}
			sql = "insert into products values(?,?,?,?,?,?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setInt(1,count);
			st.setString(2,dto.getCategory_fk());
			st.setString(3,dto.getProducts_name());
			st.setString(4,dto.getEp_code_fk());
			st.setInt(5,dto.getInput_price());
			st.setInt(6,dto.getOutput_price());
			st.setInt(7,dto.getTrans_cost());
			st.setInt(8,dto.getMileage());
			st.setString(9,dto.getCompany());
			result = st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return result;
	}

	public ProductDTO content(int num) {
		ProductDTO dto = null;
		openConn();
		try {
			sql ="select * from products where pnum = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			rs=st.executeQuery();
			if(rs.next()) {
				dto = new ProductDTO();
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProducts_name(rs.getString("products_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		
		return dto;
		
	}	//content() 메서드 end

	public ProductDTO updatebefore(int num) {
		ProductDTO dto = null;
		openConn();
		try {
			sql = "select * from products where pnum = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			rs = st.executeQuery();
			if(rs.next()) {
				dto = new ProductDTO();
				
				dto.setPnum(rs.getInt("pnum"));
				dto.setCategory_fk(rs.getString("category_fk"));
				dto.setProducts_name(rs.getString("products_name"));
				dto.setEp_code_fk(rs.getString("ep_code_fk"));
				dto.setInput_price(rs.getInt("input_price"));
				dto.setOutput_price(rs.getInt("output_price"));
				dto.setTrans_cost(rs.getInt("trans_cost"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setCompany(rs.getString("company"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(rs, st, con);
		}
		return dto;
	}
	
	public int update(ProductDTO dto) {
		openConn();
		int result=0;
		try {
				sql="select * from products where pnum =?";
				st = con.prepareStatement(sql);
				st.setInt(1,dto.getPnum());
				rs = st.executeQuery();
				
				if(rs.next()) {
					sql = "update products set category_fk = ? , products_name = ? , ep_code_fk = ? , input_price = ? , output_price = ? , trans_cost = ?, mileage = ? , company = ?  where pnum = ?";
					st = con.prepareStatement(sql);
					st.setString(1,dto.getCategory_fk());
					st.setString(2,dto.getProducts_name());
					st.setString(3,dto.getEp_code_fk());
					st.setInt(4,dto.getInput_price());
					st.setInt(5,dto.getOutput_price());
					st.setInt(6,dto.getTrans_cost());
					st.setInt(7,dto.getMileage());
					st.setString(8,dto.getCompany());
					st.setInt(9,dto.getPnum());
					
					result = st.executeUpdate();
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConn(st, con);
		}
		return result;
	}

	public int delete(int num) {
		int result = 0;
		openConn();
		try {
			sql="delete from products where pnum = ?";
			st = con.prepareStatement(sql);
			st.setInt(1,num);
			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeConn(st, con);
		}
		return result;
	}

	public void updatenum(int num) {
		openConn();
		try {
		sql = "update products set pnum = pnum - 1 where pnum > ?";
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
