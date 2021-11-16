<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="my.model.*, my.dao.*, java.sql.*, my.util.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
      <% 
      	request.setCharacterEncoding("utf-8");
     	int memberId = Integer.parseInt(request.getParameter("memberId"));
      	int productId = Integer.parseInt(request.getParameter("productId"));
   		int method = Integer.parseInt(request.getParameter("method"));
   		int amount = Integer.parseInt(request.getParameter("amount"));
   		String phone = request.getParameter("phone");
   		String orderAddress = request.getParameter("orderAddress");
   		String orderName = request.getParameter("orderName");
       	Purchase purchase = null;
       	Member member = null;
       	Product product = null;
       	Connection conn = ConnectionProvider.getConnection();
       	int cartId = 0;
     	if(request.getParameter("cartId")!=null){
     		try{
     			
     			CartDao cDao = new CartDao();
     			cartId = Integer.parseInt(request.getParameter("cartId"));
     			cDao.deleteById(conn, cartId);
     		}catch(SQLException e){}
     		
     	}
       	try {
       		member = new Member();
       		product = new Product();
       		PurchaseDao  dao = new PurchaseDao();
       		MemberDao mdao = new MemberDao();
       		ProductDao pdao = new ProductDao();
			member = mdao.selectById(conn, memberId);
			product = pdao.selectById(conn, productId);
       		purchase = new Purchase(member.getUserId(), productId, amount, amount*product.getPrice(),method, new java.util.Date(), orderAddress, phone, member.getEmail(),orderName );
       		dao.insert(conn, purchase);
       	}catch(SQLException e)  {}  
        finally { JdbcUtil.close(conn);}
       	response.sendRedirect("orderList2.jsp?memberId="+memberId);  
        %>
        
</body>
</html>