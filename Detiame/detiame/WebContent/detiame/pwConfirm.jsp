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
	String password = request.getParameter("password");
	Connection conn = ConnectionProvider.getConnection();
	String sessionPw = (String)session.getAttribute("PW");

	if(password.equals(sessionPw)) {	
		response.sendRedirect("infoModifyMain.jsp"); 
	} else {
%>
	<script>
		alert("패스워드가 일치하지 않습니다.");
		history.go(-1);
	</script>	
<%		
	} 
%>	
</body>
</html>