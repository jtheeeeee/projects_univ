<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="my.model.*, my.dao.*, java.sql.*, my.util.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>무제 문서</title>
<link href="design.css" rel="stylesheet" type="text/css" />
<link href="mypagedesign.css" rel="stylesheet" type="text/css" />
<link href="orderdesign.css" rel="stylesheet" type="text/css" />
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

<body onload="MM_preloadImages('image/mmenu_04.png','image/mmenu_09.png','image/mmenu_10.png','image/mmenu_12.png','image/mmenu_14.png','image/mmenu_15.png','image/mmenu_16.png','image/mmenu_17.png','image/mmenu_19.png','image/mmenu_20.png','image/mmenu_21.png','image/mmenu_006.png')">
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
       <a href="logout.jsp">
        <img src="image/logout.png" name="login" width="54" height="23" id="login"  />
       </a>
       </div>
      <div id="join">
       <a href="signForm.jsp">
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
         <a href="infoModify.jsp">
          <img src="image/menu_09.png" name="mypage" width="68" height="26" id="mypage" onmouseover="MM_swapImage('mypage','','image/mmenu_09.png',1)" onmouseout="MM_swapImgRestore()" />
         </a> 
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
    
  </div>
  <div id="tabpanelbg">
  
    <div id="tabpanel">
     <div id="tabpanel"><img src="image/order.png" width="921" height="71" /></div>
    </div>
    <div id="content">
      <div id="tablebg">
      
<%
	String userId = (String)session.getAttribute("UI");
	Connection conn = ConnectionProvider.getConnection();
	int productId=0;
	int amount =0;
	int cartId = 0;
	if(request.getParameter("cartId")!=null){
		cartId = Integer.parseInt(request.getParameter("cartId"));
		Cart cart = null;
		try{
			cart = new Cart();
			CartDao cDao = new CartDao();
			cart = cDao.selectBycartId(conn, cartId);
			productId = cart.getProductId();
			amount =cart.getAmount();
		}catch(SQLException e){
			
		}
		
	}else {
		productId = Integer.parseInt(request.getParameter("productId"));
		amount = Integer.parseInt(request.getParameter("amount")); 
	}
	
	String name = null;
	int price=0;
	
	int memberId = 0; 
    
    Product product = null;  
    Member member = null;
    String userName = null;
    //String address = null;
    //String phone = null;
    //String email = null;
    try {
    
    	ProductDao dao = new ProductDao();
        MemberDao mdao = new MemberDao();
        memberId = mdao.selectMemberIdById(conn, userId);
        member = mdao.selectById(conn, memberId);
        dao.updateAccessCount(conn, productId);
        product = dao.selectById(conn, productId);   
        name = product.getProductName();
        price = product.getPrice();
        userName = member.getUserName();
    }catch(SQLException e) {}  
    finally { JdbcUtil.close(conn);}
%>

<table width="670" border="1" cellspacing="0" cellpadding="3">
  <tr>
    <th width="108" scope="col">제품번호</th>
    <th width="274" scope="col">제품명</th>
    <th width="99" scope="col">수량</th>
    <th width="155" scope="col">가격</th>
  </tr>
  <tr>
    <td><%=productId %></td>
    <td><%=name%></td>
    <td><%=amount %></td>
    <td><%=amount*price%></td>
  </tr>
</table>
</div>
<div id="tabpanel"><img src="image/order3.png" width="921" height="71" /></div>

<div id="tablebg">
        <c:set var="product" value="${product}"/>

<table width="670" border="1" cellspacing="0" cellpadding="3">
  <tr>
    <th width="83" scope="col">이름</th>
    <th width="569" scope="col"><%=userName %></th>
  </tr>
  <tr>
    <td> 이메일</td>
    <td><%=member.getEmail() %></td>
  </tr>
  <tr>
    <td>연락처</td>
    <td><%=member.getPhone() %></td>
  </tr>
  
</table>
</div>
<form action = "purchase.jsp" method="post">
<div id="tabpanel"><img src="image/order4.png" width="921" height="71" /></div>
		<div id="tablebg">
		
		<table width="670" border="1" cellspacing="0" cellpadding="3">
		  <tr>
		    <th width="88" scope="col">이름</th>
		    <th width="564" scope="col"><input type = "text" name ="orderName" ></th>
		  </tr>
		  <tr>
		    <td>전화</td>
		    <td><input type = "text" name ="phone"></input></td>
		  </tr>
		  <tr>
		    <td>주소</td>
		    <td>
		      <label for="userAddress"></label>
		     <input type = "text" name = "orderAddress"></input></td>
		  </tr>
		    <tr>
    <td>결제 방법</td>
    <td>
     <div id="quantity">
                  <select name="method" id="method">
                  <option value="1">하나은행 01049080399607 김형주</option>
                  <option value="2">신한은행 110403243584 김형주</option>
                  <option value="3">국민은행 64260101596143 정태희</option>
                  </select>
            </div>
    </td>
  </tr>
		</table>
			<div class="buttonForm">
				<input type="hidden" name ="productId" value ="<%=productId %>"></input>
				<input type="hidden" name ="memberId" value ="<%=memberId %>"></input>
				<input type="hidden" name ="amount" value = "<%=amount %>"></input>
				<input type="hidden" name ="cartId" value="<%=cartId %>"></input>
				
			  <input name="submit" type="submit" value="주문하기" />
			</div>
		</div>
    </div>
</form>
  </div>
 </div>
</div>
</body>
</html>
 
