package my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import my.model.Cart;

import my.util.JdbcUtil;

public class CartDao {
 /*
 public void insert(Connection conn, Cart cart) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  try {
   pstmt = conn.prepareStatement
   ("insert into cart (userId,productId,amount,price)"
     + " values(?,?,?,?)");
   pstmt.setString(1, cart.getUserId());
   pstmt.setInt(2, cart.getProductId());
   pstmt.setInt(3, cart.getAmount());
   pstmt.setInt(4, cart.getPrice());

   pstmt.executeUpdate(); 
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
  }
 }
 */
	public void insert(Connection conn, Cart cart) 
			throws SQLException {
		PreparedStatement pstmt=null; 
		try {
			pstmt = conn.prepareStatement
			("insert into cart (userId,productId,productName,amount,price)"
					+ " values(?,?,?,?,?)");
			pstmt.setString(1,cart.getUserId());
			pstmt.setInt(2, cart.getProductId());
			pstmt.setString(3, cart.getProductName());
			pstmt.setInt(4, cart.getAmount());
			pstmt.setInt(5, cart.getPrice());
			pstmt.executeUpdate(); 
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn); 여러 번 들어갈 수 있으므로 한 번 하고 닫지 않도록 하기.
			JdbcUtil.close(pstmt);
		}
	}
 
 public Cart selectById(Connection conn, String userId) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  ResultSet rs = null;
  Cart cart = null; 
  try {
   pstmt = conn.prepareStatement
   ("select * from cart where userId = ?");
   pstmt.setString(1, userId);
   rs = pstmt.executeQuery();
   if (rs.next()){
    cart = new Cart(); 
    cart.setCartId(rs.getInt(1));
    cart.setUserId(rs.getString(2));
    cart.setProductId(rs.getInt(3));
    cart.setProductName(rs.getString(4));
    cart.setAmount(rs.getInt(5));

   }
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
   JdbcUtil.close(rs);
  }
  return cart;
 }
 public Cart selectBycartId(Connection conn, int cartId) 
		   throws SQLException {
		  PreparedStatement pstmt=null; 
		  ResultSet rs = null;
		  Cart cart = null; 
		  try {
		   pstmt = conn.prepareStatement
		   ("select * from cart where cartId = ?");
		   pstmt.setInt(1, cartId);
		   rs = pstmt.executeQuery();
		   if (rs.next()){
		    cart = new Cart(); 
		    cart.setCartId(rs.getInt(1));
		    cart.setUserId(rs.getString(2));
		    cart.setProductId(rs.getInt(3));
		    cart.setProductName(rs.getString(4));
		    cart.setAmount(rs.getInt(5));
		    cart.setPrice(rs.getInt(6));

		   }
		  } catch (SQLException e){
		   e.printStackTrace();
		  } finally {
		   //JdbcUtil.close(conn);
		   JdbcUtil.close(pstmt);
		   JdbcUtil.close(rs);
		  }
		  return cart;
		 }


 public void update(Connection conn, Cart cart) 
   throws SQLException {
  PreparedStatement pstmt=null; 
  try {
   pstmt = conn.prepareStatement
   ("update cart set amount=?, price=? where cartId=? ");
   pstmt.setInt(1, cart.getAmount());
   pstmt.setInt(2, cart.getPrice());
   pstmt.setInt(3, cart.getCartId());

   pstmt.executeUpdate(); 
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
  }
 }
 
 public void deleteById(Connection conn, int cartId) 
   throws SQLException {
  PreparedStatement pstmt=null;   
  try {
   pstmt = conn.prepareStatement
   ("delete from cart where cartId = ?");
   pstmt.setInt(1, cartId);
   pstmt.executeUpdate();   
  } catch (SQLException e){
   e.printStackTrace();
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(pstmt);
  }
 }

 public List<Cart> selectList(Connection conn, String userId) 
   throws SQLException {
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  List<Cart> cartList = null;
  try {
   pstmt = conn.prepareStatement
     ("select * from cart where userId = ?");
   pstmt.setString(1, userId );
   rs  = pstmt.executeQuery(); 
   cartList = new ArrayList<Cart>();
   while (rs.next()){
    Cart cart = new Cart();
    cart.setCartId(rs.getInt(1));
    cart.setUserId(rs.getString(2));
    cart.setProductId(rs.getInt(3));
    cart.setProductName(rs.getString(4));
    cart.setAmount(rs.getInt(5));
    cart.setPrice(rs.getInt(6));
    cartList.add(cart);
   }
  } finally {
   //JdbcUtil.close(conn);
   JdbcUtil.close(rs);
   JdbcUtil.close(pstmt);
  }
  return cartList;
 }
 /*
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
	*/
}
 