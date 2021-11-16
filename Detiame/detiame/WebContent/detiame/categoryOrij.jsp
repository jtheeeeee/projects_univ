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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>무제 문서</title>
<link href="design.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
</script>
</head>

<body onload="MM_preloadImages('image/mmenu_03.png','image/mmenu_04.png','image/mmenu_09.png','image/mmenu_10.png','image/mmenu_12.png','image/mmenu_13.png','image/mmenu_14.png','image/mmenu_15.png','image/mmenu_16.png','image/mmenu_17.png','image/mmenu_19.png','image/mmenu_20.png','image/mmenu_21.png','image/mmenu_006.png')">

<div id="wrap">
  <div id="menu">
    <div id="m1"><img src="image/menu_01.png" width="390" height="53" /></div>
    <div id="m2">
      <div id="m3"></div>
      <div id="login"><img src="image/menu_03.png" name="login" width="54" height="23" id="login" onmouseover="MM_swapImage('login','','image/mmenu_03.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="join"><img src="image/menu_04.png" name="join" width="124" height="23" id="join" onmouseover="MM_swapImage('join','','image/mmenu_04.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="m4">
        <div id="m5"></div>
        <div id="shoppingbag"><img src="image/menu_06.png" name="shoppingbag" width="106" height="30" id="shoppingbag" onmouseover="MM_swapImage('shoppingbag','','image/mmenu_006.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      </div>
      <div id="m6">
        <div id="m7"></div>
        <div id="mypage"><img src="image/menu_09.png" name="mypage" width="68" height="26" id="mypage" onmouseover="MM_swapImage('mypage','','image/mmenu_09.png',1)" onmouseout="MM_swapImgRestore()" /></div>
        <div id="home"><img src="image/menu_10.png" name="home" width="127" height="26" id="home" onmouseover="MM_swapImage('home','','image/mmenu_10.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      </div>
    </div>
    <div id="search"><img src="image/menu_11.png" name="search" width="390" height="70" id="search" /></div>
    <div id="best"><img src="image/menu_12.png" name="best" width="390" height="46" id="best" onmouseover="MM_swapImage('best','','image/mmenu_12.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="outer"><img src="image/menu_13.png" name="outer" width="390" height="53" id="outer" onmouseover="MM_swapImage('outer','','image/mmenu_13.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="top"><img src="image/menu_14.png" name="top" width="390" height="49" id="top" onmouseover="MM_swapImage('top','','image/mmenu_14.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="skirt"><img src="image/menu_15.png" name="skirt" width="390" height="49" id="skirt" onmouseover="MM_swapImage('skirt','','image/mmenu_15.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="pants"><img src="image/menu_16.png" name="pants" width="390" height="56" id="pants" onmouseover="MM_swapImage('pants','','image/mmenu_16.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="dress"><img src="image/menu_17.png" name="dress" width="390" height="47" id="dress" onmouseover="MM_swapImage('dress','','image/mmenu_17.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="line1"><img src="image/menu_18.png" width="390" height="24" /></div>
    

    <div id="notice"><img src="image/menu_19.png" name="notice" width="390" height="41" id="notice" onmouseover="MM_swapImage('notice','','image/mmenu_19.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="qna"><img src="image/menu_20.png" name="qna" width="390" height="48" id="qna" onmouseover="MM_swapImage('qna','','image/mmenu_20.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="review"><img src="image/menu_21.png" name="review" width="390" height="51" id="review" onmouseover="MM_swapImage('review','','image/mmenu_21.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="line2"><img src="image/menu_22.png" width="390" height="43" /></div>

    
  </div>
  <div id="right">
    <div id="logo"><img src="image/logo-gray.png" width="1510" height="270" /></div>
   
    <div class="product">
    	 <%
       		String cond = request.getParameter("cond");
       		String direct = request.getParameter("direct");
       		Connection conn = ConnectionProvider.getConnection();
       		List<Product> productList = null;
       		String userId = (String)session.getAttribute("UI");
       		int memberId = 0;
       		Member member = null;
       		try {
       			ProductDao dao= new ProductDao();
       			productList = dao.selectListCondition(conn,cond,direct);
       			MemberDao mDao = new MemberDao();
       			
       			memberId = mDao.selectByMemberId(conn, userId);
       			member = mDao.selectByMemberId(conn, memberId);
       			
       		}catch(SQLException e) {} 
       		finally { JdbcUtil.close(conn);}
       %>        
      <div class="product_banner">
      	<form name="conditionList" method="post">
       				<select name="cond">
       					<option value="accessCount">조회수</option>   
       					<option value="price">가격</option>     				
       					<option value="title">제목</option> 
       				</select>
       				<select name="direct">
       					<option value="asc">오름차순</option>   
       					<option value="desc">내림차순</option>     				
       				</select>
       				<input type="submit" value="카탈로그 보기"/>
       			</form>         
      </div>
      <div class="product_content">
 		
      <%-- <%=member.getName() %> 님 환영합니다. 많이 사세요. <br> --%>
       <c:set var="list" value="<%=productList %>"/>
       <c:forEach var="product" items="${list}">
       		<div class="item">
       		<a href="productDetail.jsp?productId=${product.productId}">
       			<img src="/detiame/detiame/image/${product.productImage}"/>
       		</a>
       		
       		
       		</div>
       </c:forEach>        
                </div>

 		
 		
        <div class="content"></div>
      </div>
      
      <div id="footer">여기에  id "footer"의 내용 입력</div>
      
    </div>
    
 
    
  </div>
 

  

</body>
</html>
