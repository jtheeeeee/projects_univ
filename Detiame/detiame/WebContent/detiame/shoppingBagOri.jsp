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

<body onload="MM_preloadImages('image/mmenu_09.png','image/mmenu_10.png','image/mmenu_12.png','image/mmenu_13.png','image/mmenu_14.png','image/mmenu_15.png','image/mmenu_16.png','image/mmenu_17.png','image/mmenu_19.png','image/mmenu_20.png','image/mmenu_21.png','image/mmenu_006.png','image/mypage/f2_04.png','image/mypage/f2_05.png','image/mypage/f2_06.png','image/mypage/f2_08.png')">

<div id="wrap">
  <div id="menu">
    <div id="m1"><img src="image/menu_01.png" width="390" height="53" /></div>
    <div id="m2">
      <div id="m3"></div>
      <div id="login"><img src="image/logout.png" name="logout" width="54" height="23" id="logout" /></div>
      <div id="join"><img src="image/menu_04.png" name="join" width="124" height="23" id="join" /></div>
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
    <div id="best"></div>
    <div id="outer"><img src="image/menu_13.png" name="outer" width="390" height="53" id="outer" onmouseover="MM_swapImage('outer','','image/mmenu_13.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="top"><img src="image/menu_14.png" name="top" width="390" height="49" id="top" onmouseover="MM_swapImage('top','','image/mmenu_14.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="skirt"><img src="image/menu_15.png" name="skirt" width="390" height="49" id="skirt" onmouseover="MM_swapImage('skirt','','image/mmenu_15.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="pants"><img src="image/menu_16.png" name="pants" width="390" height="56" id="pants" onmouseover="MM_swapImage('pants','','image/mmenu_16.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="dress"><img src="image/menu_17.png" name="dress" width="390" height="47" id="dress" onmouseover="MM_swapImage('dress','','image/mmenu_17.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="line1"><img src="image/menu_18.png" width="390" height="24" /><img src="image/menu_12.png" name="best" width="390" height="46" id="best2" onmouseover="MM_swapImage('best','','image/mmenu_12.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    

    <div id="notice"><img src="image/menu_19.png" name="notice" width="390" height="41" id="notice" onmouseover="MM_swapImage('notice','','image/mmenu_19.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="qna"><img src="image/menu_20.png" name="qna" width="390" height="48" id="qna" onmouseover="MM_swapImage('qna','','image/mmenu_20.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="review"><img src="image/menu_21.png" name="review" width="390" height="51" id="review" onmouseover="MM_swapImage('review','','image/mmenu_21.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    <div id="line2"><img src="image/menu_22.png" width="390" height="43" /></div>

    
  </div>
  <div id="right">
    <div id="logo"><img src="image/logo-gray.png" width="1510" height="270" /></div>
    <div id="bg">
    <div id="bgcolor">
      <div id="sbag"><img src="image/mypage/f2_03.png" width="142" height="43" /></div>
     
      <div id="orderList"><img src="image/mypage/mypage_04.png" name="orderList" width="106" height="43" id="orderList2" onmouseover="MM_swapImage('orderList','','image/mypage/f2_04.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="qnalist"><img src="image/mypage/mypage_05.png" name="qnalist" width="106" height="43" id="qnalist2" onmouseover="MM_swapImage('qnalist','','image/mypage/f2_05.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="myInfoModify"><img src="image/mypage/mypage_10.png" name="myInfoModify" width="97" height="43" id="myInfoModify2" onmouseover="MM_swapImage('myInfoModify','','image/mypage/f2_06.png',1)" onmouseout="MM_swapImgRestore()" /></div>
      <div id="deleteId"><img src="image/mypage/mypage_07.png" name="deleteId" width="116" height="43" id="deleteId2" onmouseover="MM_swapImage('deleteId','','image/mypage/f2_08.png',1)" onmouseout="MM_swapImgRestore()" /></div>
    </div>
    </div>
  </div>
  <div id="tabpanelbg">
  
    <div id="tabpanel"><img src="image/mypage/shoppingBag.png" width="921" height="71" /></div>
    <div id="content">
 
      <div id="tablebg">
      <table width="814" border="1" align="center" cellpadding="10" cellspacing="0">
        <tr>
          <th width="90" scope="col">번호</th>
          <th width="125" scope="col">제품명</th>
          <th width="90" scope="col">수량</th>
          <th width="90" scope="col">가격</th>
          <th width="125" scope="col">배송비</th>
          <th width="160" scope="col">취소</th>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><div id="quantity">
            <form id="form4" name="form4" method="post" action="">
              <label for="quantity"></label>
              <select name="quantity" id="quantity">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
              </select>
            </form>
          </div></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td><div class="buttonForm">
            <form id="form1" name="form1" method="post" action="shoppingBagDelete.jsp">
              <input type="submit" name="reset" id="reset" value="삭제" class="buttonForm"/>
            </form>
          </div></td>
        </tr>
      </table>
      </div>
      <div id="mainBottom">
        <div id="bottom1">
          <div id="modifyBottom">
            <form id="form2" name="form2" method="post" action="shoppingBagModify.jsp">
              <input type="submit" name="modify" id="modify" value="변경" />
            </form>
          </div>
        </div>
        <div id="bottom2">
          <div id="submitBottom">
            <form id="form3" name="form3" method="post" action="orderForm.jsp">
              <input type="submit" name="submit" id="submit" value="구매" />
            </form>
          </div>
        </div>
       
      </div>
      

      <div class="gotoMainForm">
      	<form id="form5" method="post" action="mainPage.html">
      		<input type ="submit" value="메인 화면으로"  class="goToMain">
      		</input>
		</form>     
      </div>
    </div>
  </div>
 

  
</div>
</body>
</html>
