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
 	 int cartId = Integer.parseInt(request.getParameter("cartId"));
	 String userId = (String)session.getAttribute("UI");
     Connection conn = ConnectionProvider.getConnection();
     Cart cart = null;  
     
     try {
      CartDao dao = new CartDao();
      cart = dao.selectById(conn, userId);
      dao.deleteById(conn, cartId);   
     }catch(SQLException e) {}  
     finally { JdbcUtil.close(conn);}
     response.sendRedirect("shoppingBag2.jsp");
 %> 
 <%--
  "<%=cart.getProductName() %>"가 삭제되었습니다. <br><br>
 --%>
</body>
</html> 