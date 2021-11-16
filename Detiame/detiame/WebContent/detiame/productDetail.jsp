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
<script type="text/javascript">
function MM_popupMsg(msg) { //v1.0
  alert(msg);
}
</script>
</head>

<body onload="MM_preloadImages('image/mmenu_03.png','image/mmenu_04.png','image/mmenu_09.png','image/mmenu_10.png','image/mmenu_12.png','image/mmenu_13.png','image/mmenu_14.png','image/mmenu_15.png','image/mmenu_16.png','image/mmenu_17.png','image/mmenu_19.png','image/mmenu_20.png','image/mmenu_21.png','image/mmenu_006.png')">
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
#buy {
	font-family: "Microsoft Himalaya";
	font-size: 30px;
	font-weight: bold;
	color: #f5f5f5;
	height: 118px;
	width: 118px;
	background-image: url(image/buyba.png);
	//padding-top: 20px;
	//padding-left: 20px;
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
       <a href="mainPage2.jsp">
        <img src="image/menu_04.png" name="join" width="124" height="23" id="join" onmouseover="MM_swapImage('join','','image/mmenu_04.png',1)" onmouseout="MM_swapImgRestore()" />
       </a>
      </div>
      <div id="m4">
        <div id="m5"></div>
        <div id="shoppingbag">
         <a href="shoppingBag2.jsp">
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
         <a href="mainPage2.jsp">
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
     <a href="category2.jsp">
      <img src="menu_12.png" name="All" width="390" height="46" id="All" onmouseover="MM_swapImage('All','','mmenu_12.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="outer">
     <a href="categoryType6.jsp">
      <img src="image/menu_13.png" name="outer" width="390" height="53" id="outer" onmouseover="MM_swapImage('outer','','image/mmenu_13.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>  
    </div>    
    <div id="top">
     <a href="categoryType7.jsp">
      <img src="image/menu_14.png" name="top" width="390" height="49" id="top" onmouseover="MM_swapImage('top','','image/mmenu_14.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="skirt">
     <a href="categoryType8.jsp">
      <img src="image/menu_15.png" name="skirt" width="390" height="49" id="skirt" onmouseover="MM_swapImage('skirt','','image/mmenu_15.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="pants">
     <a href="categoryType9.jsp">
      <img src="image/menu_16.png" name="pants" width="390" height="56" id="pants" onmouseover="MM_swapImage('pants','','image/mmenu_16.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="dress">
     <a href="categoryType10.jsp">
      <img src="image/menu_17.png" name="dress" width="390" height="47" id="dress" onmouseover="MM_swapImage('dress','','image/mmenu_17.png',1)" onmouseout="MM_swapImgRestore()" />
     </a>
    </div>
    <div id="line1"><img src="image/menu_18.png" width="390" height="24" /></div>
   

    <div id="notice"><img src="image/menu_19.png" name="notice" width="390" height="41" id="notice" onmouseover="MM_swapImage('notice','','image/mmenu_19.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="qna"><img src="image/menu_20.png" name="qna" width="390" height="48" id="qna" onmouseover="MM_swapImage('qna','','image/mmenu_20.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="review"><img src="image/menu_21.png" name="review" width="390" height="51" id="review" onmouseover="MM_swapImage('review','','image/mmenu_21.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="line2"><img src="image/menu_22.png" width="390" height="43" /></div>

    
  </div>
  <div id="right">
  
 <% 
         int productId = Integer.parseInt(request.getParameter("productId"));
         Connection conn = ConnectionProvider.getConnection();
         Product product = null;
         try {
          ProductDao dao = new ProductDao();
          dao.updateAccessCount(conn, productId);
          product = dao.selectById(conn, productId);  
         }catch(SQLException e) {}  
         finally { JdbcUtil.close(conn);}
         String productName = product.getProductName();
         int price = product.getPrice();
         String color = product.getColor();
         String productImage = product.getProductImage();
        
         
        %>
        <c:set var="product" value="<%= product%>"/>
     
    <div id="logo"><img src="image/logo.png" width="1510" height="270" /></div>
   
    <div class="product">
      <div id="product_detail">
         <div id="product_image">
         <img src="/detiame/detiame/productImage/<%=productImage%>"/>       
        </div>
       
        <div id="detail">
          <div id="product_name">
          <%=productName %>
          </div>
          <div id="p1">
            <div id="product_code"><img src="image/detail/detail_02.png" width="142" height="42" /></div>
            <div id="code">
            <input type="hidden" id="productId" value="<%=productId %>">
             <%=productId %>
            </div>
          </div>
          <div id="p2">
            <div id="product_price"><img src="image/detail/detail_04.png" width="142" height="42" /></div>
            <div id="price">
            <input type="hidden" id="price" name ="price" value="<%=price %>">
          
             <%=price %>
            </div>
          </div>
          <div id="p3">
            <div id="product_color"><img src="image/detail/detail_06.png" width="142" height="40" /></div>
            <div id="color">
             <%=color %>
            </div>
          </div>
          <div id="p4">
            <div id="product_quantity"><img src="image/detail/detail_08.png" width="142" height="39" /></div>
            <div id="quantity">
              <label for="amount"></label>
                        <select name="amount" id="amount">
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                        </select>
            </div>
          </div>
          <div id="p5">
           <%-- <div id="product_totalprice"><img src="image/detail/detail_10.png" width="142" height="46" /></div>
            <div id="totalprice">
            	<input type="text" name="totalPrice" id="price" value="    "/>
            </div>
          </div> --%>
        </div>
        <div id="shoppingtip">
          <div id="stip_logo"><img src="image/shoppingtip_01.png" width="497" height="45" /></div>
          <div id="stip_content"></div>
        </div>
        <div id="choice">
          <div id="buy">
            <%--a href="purchaseForm.jsp?productId=${product.productId}&price=${product.price}"> --%>	
            		<%--img src="image/buy.png" name="buy" width="118" height="141" id="buy" /> --%>
            		<input type="button" onclick="purchase()" name="buy" id="buy" value="buy" />
            	<%--a --%>
 
          </div>
          
          <div id="buy">
            
            <%-- a href ="shoppingBag.jsp"><img src="image/cart.png" name="cart" width="118" height="141" id="cart" /></a></div>--%>
            <input type = "button" id = "buy" onclick= "cartpurchase()" value="cart"></input>
        
        </div>
        
      </div>
      <div id="product_focus">
      
      </div>
    </div>
      
     <div id="footer"><img src="/detiame/detiame/image/footer.png"/></div>
      
      </div>
      
  </div>
    
 
    
  </div>
 </div>
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




function purchase(){
	var productId = document.getElementById("productId").value.trim();
	var amount = document.getElementById("amount").value.trim();
	location.href='orderForm.jsp?productId='+productId+'&amount='+amount;
}
function cartpurchase(){
	var productId = document.getElementById("productId").value.trim(); //꼭 해줘야 함. 숫자라서 공백 문자가 필요하지 않기 때문에!!!!!!!
	var amount = document.getElementById("amount").value.trim();
	location.href='shoppingBagForm.jsp?productId='+productId+'&amount='+amount;
}
</script>
</body>
</html>
