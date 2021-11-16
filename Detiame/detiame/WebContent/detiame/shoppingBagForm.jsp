<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="my.model.*, my.dao.*, java.sql.*, my.util.*,java.util.*" %>  
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.awt.Image"%>
<%@page import="com.sun.jimi.core.Jimi"%>
<%@page import="com.sun.jimi.core.JimiException"%>
<%@page import="com.sun.jimi.core.JimiUtils"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <%
   String userId = (String)session.getAttribute("UI");
   int productId = Integer.parseInt(request.getParameter("productId"));
   int amount = Integer.parseInt(request.getParameter("amount"));
   int price =0;
   int totalPrice = amount*price;
  Connection conn = ConnectionProvider.getConnection();
  Cart cart = null;
  Product product = new Product();
  try{
   ProductDao pdao = new ProductDao();
   product = pdao.selectById(conn, productId);
   String name = product.getProductName();
   price = product.getPrice();
   cart = new Cart (userId, productId, name, amount, amount*price);
   CartDao dao = new CartDao();
   dao.insert(conn, cart); //db에 바로 들어가요~!
  }catch (SQLException e){}
  response.sendRedirect("shoppingBag2.jsp");
 %>
	

</body>
</html> 