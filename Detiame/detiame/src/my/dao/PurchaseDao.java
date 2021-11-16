package my.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import my.model.Purchase;
import my.util.JdbcUtil;

public class PurchaseDao {
	
	public void insert(Connection conn, Purchase purchase) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("insert into purchase (userId,productId,amount,totalPrice,method,purchaseDate,orderAddress,phone,email,orderName)"
					+ " values(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, purchase.getUserId());
			pstmt.setInt(2, purchase.getProductId());
			pstmt.setInt(3, purchase.getAmount());
			pstmt.setInt(4, purchase.getTotalPrice());
			pstmt.setInt(5, purchase.getMethod());
			pstmt.setTimestamp(6, new Timestamp(purchase.getPurchaseDate().getTime()));
			pstmt.setString(7, purchase.getOrderAddress());
			pstmt.setString(8, purchase.getPhone());
			pstmt.setString(9, purchase.getEmail());
			pstmt.setString(10, purchase.getOrderName());
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn); 여러 번 들어갈 수 있으므로 한 번 하고 닫지 않도록 하기.
			JdbcUtil.close(pstmt);
		}
	}

	public Purchase selectById(Connection conn, String userId) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		ResultSet rs = null;
		Purchase purchase = null; 
		try {
			pstmt = conn.prepareStatement
			("select * from purchase where userId = ?");
			pstmt.setString(1, userId );
			rs = pstmt.executeQuery();
			if (rs.next()){
				purchase = new Purchase(); 
				purchase.setPurchaseId(rs.getInt(1));
				purchase.setUserId(rs.getString(2));
				purchase.setProductId(rs.getInt(3));
				purchase.setAmount(rs.getInt(4));
				purchase.setTotalPrice(rs.getInt(5));
				purchase.setMethod(rs.getInt(6));
				purchase.setPurchaseDate(rs.getTimestamp(7));
				purchase.setOrderAddress(rs.getString(8));
				purchase.setPhone(rs.getString(9));
				purchase.setEmail(rs.getString(10));
				purchase.setOrderName(rs.getString(11));
				
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(rs);
		}
		return purchase;
	}
/*	
	public void update(Connection conn, Purchase purchase) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("update purchase set guestname=?,password=?,purchase=?, "
					+ "   registertime=? where purchaseId=?");
			pstmt.setString(1, purchase.getGuestName());
			pstmt.setString(2, purchase.getPassword());
			pstmt.setString(3, purchase.getPurchase());
			pstmt.setTimestamp(4, new Timestamp(purchase.getRegisterTime().getTime()));
			pstmt.setInt(5, purchase.getPurchaseId());
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void deleteById(Connection conn, int purchaseId) 
			throws SQLException {
		PreparedStatement pstmt=null; 		
		try {
			pstmt = conn.prepareStatement
			("delete from purchase where purchaseId = ?");
			pstmt.setInt(1, purchaseId);
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
			rs = stmt.executeQuery("select count(*) from purchase");
			rs.next();
			return rs.getInt(1);
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(rs);
		}
	}
	*/
	public List<Purchase> selectList(Connection conn, String userId) 
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Purchase> purchaseList = null;
		try {
			pstmt = conn.prepareStatement
			("select * from purchase where userId = ?");
			pstmt.setString(1, userId );
			rs  = pstmt.executeQuery(); 
			purchaseList = new ArrayList<Purchase>();
			while (rs.next()){
				Purchase purchase = new Purchase();
				purchase.setPurchaseId(rs.getInt(1));
				purchase.setUserId(rs.getString(2));
				purchase.setProductId(rs.getInt(3));
				purchase.setAmount(rs.getInt(4));
				purchase.setTotalPrice(rs.getInt(5));
				purchase.setMethod(rs.getInt(6));
				purchase.setPurchaseDate(rs.getTimestamp(7));
				purchase.setOrderAddress(rs.getString(8));
				purchase.setPhone(rs.getString(9));
				purchase.setEmail(rs.getString(10));
				purchase.setOrderName(rs.getString(11));
				purchaseList.add(purchase);
			}
		} finally {
			//JdbcUtil.close(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return purchaseList;
	}
	 public void deleteById(Connection conn, int purchaseId) 
		      throws SQLException {
		     PreparedStatement pstmt=null;   
		     try {
		      pstmt = conn.prepareStatement
		      ("delete from purchase where purchaseId = ?");
		      pstmt.setInt(1, purchaseId);
		      pstmt.executeUpdate();   
		     } catch (SQLException e){
		      e.printStackTrace();
		     } finally {
		      //JdbcUtil.close(conn);
		      JdbcUtil.close(pstmt);
		     }
		    } 
}




















