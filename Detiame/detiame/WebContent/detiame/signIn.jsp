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
	String userId = request.getParameter("id");
	String password = request.getParameter("password");
	String userName = request.getParameter("name");
	String userAddress = request.getParameter("address");
	String phone = request.getParameter("phone");
	String email_id =request.getParameter("email_id");
	String email_domain = request.getParameter("email_domain");
	String email = email_id+"@"+email_domain;
	int birth_year = Integer.parseInt(request.getParameter("birth_year"));
	int birth_month = Integer.parseInt(request.getParameter("birth_month"));
	int birth_day = Integer.parseInt(request.getParameter("birth_day"));
	java.util.Date birthdate = new java.util.Date();
	birthdate.setYear(birth_year-1900);
	birthdate.setMonth(birth_month-1);
	birthdate.setDate(birth_day);
	Member member = new Member(userId, password, userName, userAddress, phone, email, birthdate);
	//Member member = new Member(userId, password, name, birthDate); //파라미터 생성자를 이용한 member 객체 만들기
	Connection conn = ConnectionProvider.getConnection();
	try{
		MemberDao dao = new MemberDao();
		dao.insert(conn, member);
	}catch(SQLException e){}
	finally { JdbcUtil.close(conn);}
	response.sendRedirect("loginForm.html");
%>  
	

</body>
</html>