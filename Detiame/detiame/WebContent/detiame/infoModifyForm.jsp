<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="my.model.*, my.dao.*, java.sql.*, my.util.*,java.util.*" %>  
<%@page import="java.io.File"%>
<%@page import="java.util.Enumeration"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>무제 문서</title>
<link href="design.css" rel="stylesheet" type="text/css" />
<link href="mypagedesign.css" rel="stylesheet" type="text/css" />

</head>

<body onload="MM_preloadImages('image/mmenu_04.png','image/mmenu_09.png','image/mmenu_10.png','image/mmenu_12.png','image/mmenu_14.png','image/mmenu_15.png','image/mmenu_16.png','image/mmenu_17.png','image/mmenu_19.png','image/mmenu_20.png','image/mmenu_21.png','image/mmenu_006.png','image/mypage/f2_05.png','image/mypage/f2_06.png','image/mypage/f2_08.png')">
<style type="text/css">
#menuback {
	background-image: url(image/menu_02.png);
	background-repeat: repeat-y;
	width: 390px;
}
#searchTop {
	height: 28px;
	width: 390px;
}
#searchbottom {
	height: 42px;
	width: 390px;
}
#topleft {
	float: left;
	height: 28px;
	width: 106px;
}
#topcenter {
	float: left;
	height: 28px;
	width: 81px;
}
#topright {
	height: 28px;
	width: 203px;
	float: left;
}
#bottomleft {
	float: left;
	height: 42px;
	width: 106px;
}
#bottomcenter {
	float: left;
	height: 42px;
	width: 81px;
}
#bottomright {
	float: right;
	height: 42px;
	width: 203px;
}
#search {
	padding: 0px;
	float: left;
	height: 28px;
	width: 81px;
	font-family: "맑은 고딕";
	background-color: #F5F5F5;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}
#searchdiv {
	height: 70px;
	width: 390px;
	float: left;
}
#bcbottom {
	float: left;
	height: 13px;
	width: 81px;
}
#bctop {
	background-image: url(image/2search_05.png);
	float: left;
	height: 29px;
	width: 81px;
}
#searchbutton {
	background-image: url(image/3search_06.png);
	float: left;
	height: 42px;
	width: 42px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}
</style>
<div id="wrap">
   <div id="menu">
    <div id="m1"><img src="image/menu_01.png" width="390" height="53" /></div>
    <div id="m2">
      <div id="m3"></div>
      <div id="login">
       <a href="loginForm.html">
        <img src="image/menu_03.png" name="login" width="54" height="23" id="login" onmouseover="MM_swapImage('login','','image/mmenu_03.png',1)" onmouseout="MM_swapImgRestore()" />
       </a>
       </div>
      <div id="join">
       <a href="signForm.html">
        <img src="image/menu_04.png" name="join" width="124" height="23" id="join" onmouseover="MM_swapImage('join','','image/mmenu_04.png',1)" onmouseout="MM_swapImgRestore()" />
       </a>
      </div>
      <div id="m4">
        <div id="m5"></div>
        <div id="shoppingbag">
         <a href="shoppingBag.jsp">
          <img src="image/menu_06.png" name="shoppingbag" width="106" height="30" id="shoppingbag" onmouseover="MM_swapImage('shoppingbag','','image/mmenu_006.png',1)" onmouseout="MM_swapImgRestore()" />
         </a> 
        </div>
      </div>
      <div id="m6">
        <div id="m7"></div>
        <div id="mypage">
         
        </div>
        <div id="home">
         <a href="mainPage.jsp">
          <img src="image/menu_10.png" name="home" width="127" height="26" id="home" onmouseover="MM_swapImage('home','','image/mmenu_10.png',1)" onmouseout="MM_swapImgRestore()" />
         </a> 
        </div>
      </div>
    </div>
     <form id="form1" name="form1" method="post" action="search.jsp">
    <div id="searchdiv">
      <div id="searchTop">
        <div id="topleft"><img src="image/1search_01.png" width="106" height="28" /></div>
        <div id="topcenter"><img src="image/1search_02.png" width="81" height="28" /></div>
        <div id="topright"><img src="image/1search_03.png" width="203" height="28" /></div>      
      </div>
      <div id="searchbottom">
        <div id="bottomleft"><img src="image/1search_04.png" width="106" height="42" /></div>
      	<div id="bottomcenter">
      	<div id="bctop">
      	  
      	    <label for="search"></label>
      	    <input type="text" name="search" id="search" />
    	    
      	</div>  
        <div id="bcbottom"><img src="image/2search_07.png" width="81" height="13" />
        </div>
      	</div>
      	<div id="bottomright">
      	   <input type="submit" name="submit" id="searchbutton" value="" />
      	  <div id="brright"><img src="image/3search_07.png" width="161" height="42" /></div>
     	
      	</div>
        
      </div>
      
    </div>
    </form>
    <div id="best">
     <a href="category.jsp">
      <img src="menu_12.png" name="All" width="390" height="46" id="All" onmouseover="MM_swapImage('All','','mmenu_12.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="outer">
     <a href="categoryType1.jsp">
      <img src="image/menu_13.png" name="outer" width="390" height="53" id="outer" onmouseover="MM_swapImage('outer','','image/mmenu_13.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>  
    </div>    
    <div id="top">
     <a href="categoryType2.jsp">
      <img src="image/menu_14.png" name="top" width="390" height="49" id="top" onmouseover="MM_swapImage('top','','image/mmenu_14.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="skirt">
     <a href="categoryType3.jsp">
      <img src="image/menu_15.png" name="skirt" width="390" height="49" id="skirt" onmouseover="MM_swapImage('skirt','','image/mmenu_15.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="pants">
     <a href="categoryType4.jsp">
      <img src="image/menu_16.png" name="pants" width="390" height="56" id="pants" onmouseover="MM_swapImage('pants','','image/mmenu_16.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="dress">
     <a href="categoryType5.jsp">
      <img src="image/menu_17.png" name="dress" width="390" height="47" id="dress" onmouseover="MM_swapImage('dress','','image/mmenu_17.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="line1"><img src="image/menu_18.png" width="390" height="24" /></div>
   

    <div id="notice">
     <a href="noticeMain.jsp"> 
      <img src="image/menu_19.png" name="notice" width="390" height="41" id="notice" onmouseover="MM_swapImage('notice','','image/mmenu_19.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="qna">
     <a href="qna.jsp">
      <img src="image/menu_20.png" name="qna" width="390" height="48" id="qna" onmouseover="MM_swapImage('qna','','image/mmenu_20.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="review">
     <a href="reviewMain.jsp">
      <img src="image/menu_21.png" name="review" width="390" height="51" id="review" onmouseover="MM_swapImage('review','','image/mmenu_21.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="line2"><img src="image/menu_22.png" width="390" height="43" /></div>
  </div>

  <div id="right">
    <div id="logo"><img src="image/logo.png" width="1510" height="270" /></div>
    <div id="bg">
    <div id="bgcolor">
      <div id="sbag"><img src="image/mypage/mypage_03.png" width="142" height="43" /></div>
      <div id="orderList"><img src="image/mypage/mypage_04.png" width="106" height="43" /></div>
      <div id="qnalist"><img src="image/mypage/mypage_05.png" name="qnalist" width="106" height="43" id="qnalist2" onmouseover="MM_swapImage('qnalist','','image/mypage/f2_05.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="myInfoModify"><img src="image/mypage/f2_06.png" name="myInfoModify" width="97" height="43" id="myInfoModify2" onmouseover="MM_swapImage('myInfoModify','','image/mypage/f2_06.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="deleteId"><img src="image/mypage/mypage_07.png" name="deleteId" width="116" height="43" id="deleteId2" onmouseover="MM_swapImage('deleteId','','image/mypage/f2_08.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    </div>
    </div>
  </div>
  <div id="tabpanelbg">
  
    <div id="tabpanel"><img src="image/mypage/infoConfirm.png" width="921" height="71" /></div>
    <div id="content">
      <div id="infoImage">
                     <div class="tablestyle">
           					<%	
           					
           						String userId = (String)session.getAttribute("UI");
           						String password = request.getParameter("password");
           						String email_id = request.getParameter("email_id");
       							String email_field = request.getParameter("email_field");
       							String phone = request.getParameter("phone");
       							String userAddress = request.getParameter("userAddress");
       							String email = email_id+"@"+email_field;
       							int memberId=0;
       							Connection conn = ConnectionProvider.getConnection();
           						try{
           							MemberDao dao = new MemberDao();
           							memberId = dao.selectMemberIdById(conn, userId);
           							Member member = new Member(memberId, password, email, phone, userAddress);
           							dao.update(conn, member); //db에 바로 들어가요~!
           						}catch (SQLException e){}
       							
								%>      
								수정이 완료 되었습니다.<br>
                   				<a href="mainPage.html">메인 페이지로 가기</a>
                	</div>
      </div>
    </div>
  </div>
 

  <div id="footer"><img src="/detiame/detiame/image/footer.png"/></div>
</div>
</body>

</html>
