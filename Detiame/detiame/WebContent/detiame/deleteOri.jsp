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
	 		String userId = (String)session.getAttribute("UI");        	
	 		int memberId;
        	Connection conn = ConnectionProvider.getConnection();
           	Member member = null;
        	try {
        		MemberDao dao = new MemberDao();
        		memberId = dao.selectMemberIdById(conn, userId);
        		dao.deleteById(conn, memberId);
        	}catch(SQLException e) {}  
        	finally { JdbcUtil.close(conn);}
        %>
		<div id="content">
    <p></p>
  		
      <div id="delete1">안녕히 가세요.</div>

      
     
      
    </div>
		
		 <div class="gotoMainForm">
      	<form id="form5" method="post" action="mainPage.jsp">
      		<input type ="submit" value="메인 화면으로"  class="goToMain"/>
		</form>     
      </div>
		
	
</body>
</html>