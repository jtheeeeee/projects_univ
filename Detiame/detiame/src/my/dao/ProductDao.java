package my.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import my.model.Product;
import my.util.JdbcUtil;

public class ProductDao {
	
	public void insert(Connection conn, Product product) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("insert into product (productName,price,color,productImage,productType)"
					+ " values(?,?,?,?,?)");
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getColor());
			pstmt.setString(4, product.getProductImage());
			pstmt.setInt(5, product.getProductType());
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Product selectById(Connection conn, int productId) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		Product product = null; 
		try {
			pstmt = conn.prepareStatement
			("select * from product where productId = ?");
			pstmt.setInt(1, productId);
			rs = pstmt.executeQuery();
			if (rs.next()){
				product = new Product(); 
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				product.setAccessCount(rs.getInt(6));
				product.setProductType(rs.getInt(7));
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return product;
	}

	public Product selectByProductName(Connection conn, String productName) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		Product product = null; 
		try {
			pstmt = conn.prepareStatement
			("select * from product where productName = ?");
			pstmt.setString(1, productName);
			rs = pstmt.executeQuery();
			if (rs.next()){
				product = new Product(); 
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				product.setAccessCount(rs.getInt(6));
				product.setProductType(rs.getInt(7));
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return product;
	}
	public void update(Connection conn, Product product) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("update product set productName=?,price=?,color=?,productImage=?,productType=?"
					+ " where productId=?");
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getColor());
			pstmt.setString(4, product.getProductImage());
			pstmt.setInt(5, product.getProductType());
			pstmt.setInt(6, product.getProductId());
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void updateAccessCount(Connection conn, int productId) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("update product set accessCount=accessCount +1 where productId=?");
			pstmt.setInt(1, productId);
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void deleteById(Connection conn, int productId) 
			throws SQLException {
		PreparedStatement pstmt=null; 		
		try {
			pstmt = conn.prepareStatement
			("delete from product where productId = ?");
			pstmt.setInt(1, productId);
			pstmt.executeUpdate();			
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null; 
		ResultSet rs = null; 
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from product");
			rs.next();
			return rs.getInt(1);
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(rs);
		}
	}

	public List<Product> selectList(Connection conn, String keyField, String keyword) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		String sql = "select * from product";
		if (keyword != null && !keyword.equals("")){
			sql += " where "+keyField.trim()+" like '%"+keyword.trim()+"%'"; //오름차순 내림차순 받기 보여주는 순서 결정
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery(); 
			productList = new ArrayList<Product>();
			while (rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				product.setProductType(rs.getInt(6));
				productList.add(product);
			}
		} finally {
			//JdbcUtil.close(conn); 이렇게 해줘야 함. connection 닫지 못 하게 하기.!
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	
	public List<Product> selectListCondition(Connection conn, String cond, String direct) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		String sql = "select * from product";
		if (cond != null && !cond.equals("")){
			sql += " order by "+cond.trim()+" "+direct.trim(); //오름차순 내림차순 받기 보여주는 순서 결정
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery(); 
			productList = new ArrayList<Product>();
			while (rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				product.setProductType(rs.getInt(6));
				
				productList.add(product);
			}
		} finally {
			//JdbcUtil.close(conn); 이렇게 해줘야 함. connection 닫지 못 하게 하기.!
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	public List<Product> selectList(Connection conn) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		try {
			pstmt = conn.prepareStatement
					("select * from product");
			rs  = pstmt.executeQuery(); 
			productList = new ArrayList<Product>();
			while (rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				productList.add(product);
			}
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	
	public List<Product> selectListType(Connection conn, String type) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		String sql = "select * from product";
		if (type != null && !type.equals("")){
			sql += " where productType= '"+type.trim()+"'";
		}
		//System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery(); 
			productList = new ArrayList<Product>();
			while (rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductType(rs.getInt(5));
				product.setProductImage(rs.getString(6));

				productList.add(product);
			}
		} finally {
			//JdbcUtil.close(conn); 이렇게 해줘야 함. connection 닫지 못 하게 하기.!
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	public List<Product> selectListSearch(Connection conn, String keyword) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		String sql = "select * from product";
		if (keyword != null && !keyword.equals("")){
			sql += " where productName like '%"+keyword.trim()+"%'"; //오름차순 내림차순 받기 보여주는 순서 결정
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery(); 
			productList = new ArrayList<Product>();
			while (rs.next()){
				Product product = new Product();
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				
				productList.add(product);
			}
		} finally {
			//JdbcUtil.close(conn); 이렇게 해줘야 함. connection 닫지 못 하게 하기.!
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
	public List<Product> selectListConditionType(Connection conn, String cond, String direct, int type) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> productList = null;
		String sql = "select * from product where productType = "+type ;
		if (cond != null && !cond.equals("")){
			sql += " order by "+cond.trim()+" "+direct.trim(); //오름차순 내림차순 받기 보여주는 순서 결정
		}
		try {
			pstmt = conn.prepareStatement(sql);
			rs  = pstmt.executeQuery(); 
			productList = new ArrayList<Product>();
			while (rs.next()){
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getInt(3));
				product.setColor(rs.getString(4));
				product.setProductImage(rs.getString(5));
				product.setProductType(rs.getInt(6));
				
				productList.add(product);
			}
		} finally {
			//JdbcUtil.close(conn); 이렇게 해줘야 함. connection 닫지 못 하게 하기.!
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return productList;
	}
}



















