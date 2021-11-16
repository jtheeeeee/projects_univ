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
	String userId = request.getParameter("id");
	String password = request.getParameter("password");
	Connection conn = ConnectionProvider.getConnection();
	String dbPassword = null;
	try{
		MemberDao dao = new MemberDao();
		dbPassword = dao.selectPasswordById(conn, userId);
		
	}catch(SQLException e){}
	finally { JdbcUtil.close(conn);}
	if (userId != null) {
		if(password.equals(dbPassword)==true) {
			session.setAttribute("UI", userId); //UI에 지금 입력한 userId 
			response.sendRedirect("infoModifyForm.jsp");
		} else {
%>
	<script>
		alert("패스워드가 일치하지 않습니다.");
		history.go(-1);
	</script>	
<%		
		}
	} else {
%>		
	<script>
		alert("해당하는 사용자 아이디가 없습니다.");
		history.go(-1);
	</script>	
<%		
	}
%>
</body>
</html>