<%@page import="org.cpu.pethotel.po.Users" %>
<%@page import="org.cpu.pethotel.po.Pets" %>
<%@page import="java.util.List" %>
<%@page import="org.cpu.pethotel.po.Friends" %>
<%@page import="org.cpu.pethotel.biz.IFriendsBiz" %>
<%@page import="org.cpu.pethotel.biz.ITradeBiz" %>
<%@page import="org.cpu.pethotel.biz.IUsersBiz" %>
<%@page import="org.cpu.pethotel.biz.impl.FriendsBizImpl" %>
<%@page import="org.cpu.pethotel.biz.impl.TradeBizImpl" %>
<%@page import="org.cpu.pethotel.biz.impl.UsersBizImpl" %>
<%@page import="org.cpu.pethotel.dao.ITradesDao" %>
<%@page import="org.cpu.pethotel.dao.IFriendsDao" %>
<%@page import="org.cpu.pethotel.dao.impl.FriendsDaoImpl" %>
<%@page import="org.cpu.pethotel.dao.impl.TradesDaoImpl" %>
<%@page import="org.cpu.pethotel.po.Trades" %>
<%@page import="org.cpu.pethotel.po.Users" %>
<%@page import="java.util.ArrayList" %>
<%@page import="org.cpu.pethotel.biz.IPetsBiz" %>
<%@page import="org.cpu.pethotel.biz.impl.PetsBizImpl" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>宠物信息</title>

<script language="javascript">

function showFriend(id){
	// 获取页面中所有的消息回复表单行对象的集合
	var lstTr = document.getElementsByName("friend");
	// 使用循环遍历每个对象将其显示属性设置为none
	for(var i=0; i<lstTr.length; i++){
		lstTr[i].style.display = "none";
	}
	// 将需要显示的回复表单行对象进行显示
	document.getElementById(id).style.display = "block";
}
function showReply(id){
	// 获取页面中所有的消息回复表单行对象的集合
	var lstTr = document.getElementsByName("reply");
	// 使用循环遍历每个对象将其显示属性设置为none
	for(var i=0; i<lstTr.length; i++){
		lstTr[i].style.display = "none";
	}
	// 将需要显示的回复表单行对象进行显示
	document.getElementById(id).style.display = "block";
}
function hiddenReply(id){
	document.getElementById(id).style.display = "none";
}


function DeleteById(id){
	 // 删除前的判断确认
	 if(confirm('确定删除好友吗?')){
		// 页面跳转至制定的servlet并携带参数
		 window.location="/PetHotel/DeleteFriendsServlet?fsid="+id;
	 }
}
function ShowOtherUser(uid){
	window.location="/PetHotel/ShowOtherUserServlet?uid="+uid;
}
</script>

<script type='text/javascript' src='js/jquery.js'></script>
<link href="css/styleM.css" rel="stylesheet" type="text/css">
<script type='text/javascript'>
$(document).ready(function() {

        //when the user hovers over the div that contains our html...
        $('.box_container').hover(function(){
            //... we get the width of the div and split it by 2  ...
            var width = $(this).outerWidth() / 2;
            /*... and using that width we move the left "part" of the image to left and right "part"
            to right by changing it's indent from left side or right side... '*/
            $(this).find('.left').animate({ right : width },{queue:false,duration:300});
            $(this).find('.right').animate({ left : width },{queue:false,duration:300});
        }, function(){
            //... and when he hovers out we get the images back to their's starting position using the same function... '
            $(this).find('.left').animate({ right : 0 },{queue:false,duration:300});
            $(this).find('.right').animate({ left : 0 },{queue:false,duration:300});
            //... close it and that's it
        });

});
</script>


<script type="text/javascript">
$(document).ready(function () {

	$('#hero-slider ul a').click(function () {
	
		//reset all the items
		$('.customBlock ul a').removeClass('active');
		
		//set current item as active
		$(this).addClass('active');	
		
		//scroll it to the right position
		$('.mask').scrollTo($(this).attr('rel'), 300);
		
		//disable click event
	    return false;		
		
	});
	
});
</script>
<style>


/**** Add css        ****/

.usersRanking{
	max-width: 500px;
	
	margin-left:205px;
}
.post{
	width:200px;
	height:450px;
	border-right-color:#3FF;
	border-right-style:dashed;
	border-right-width:medium;
	float:left;
}


body,td,th {
	font-family: 幼圆;
	font-size: 16px;
	color: #000;
}
 #content #hero-slider .mask .slider-body .panel .post p {
	font-family: 幼圆;
}
#content #hero-slider .mask .slider-body .panel .usersRanking table tr td {
	font-family: 幼圆;
}


/**** End css        ****/
</style>
<script type="text/javascript">
/*** Add javascript**/

</script>

</head>
<body>
    <div id="header">
<div class='box_container'>
  <!--START THE IMAGE PARTS HOLDER-->
  <div class='images_holder'>
    <!--INSERT THE SAME IMAGE IN 2 DIVS, THEY BOTH HAVE image_div CLASS AND left OR right CLASS DEPENDING ON POSITION-->
    <div class='image_div left'><img class='box_image' src='images/treat.png' style='width:251px'/></div>
    <div class='image_div right'><img class='box_image' src='images/treat.png' style='width:251px'/></div>
    <!-- WE USED CSS FLOAT PROPERY, SO WE NEED TO CLEAR NOW-->
    <div class='clear'></div>
  </div>
  <!--END THE IMAGE PARTS HOLDER-->
</div>
<!--END THE MAIN CONTAINER-->
    </div>
	
 
<!------------------------------------------   -->
    <div id="content"><div id="hero-slider">
	  <div id="mainMenu">
			<ul>
			<li><a href="/PetHotel"  class="home"></a></li>   
			 <li><a href="/PetHotel/jsp/search.jsp"  class="contacts"></a></li>
			<li><a href="/PetHotel/jsp/PersonIndex.jsp" class="aboutus"></a></li>
			<li><a href="/PetHotel/jsp/showPosts.jsp?pos=0&potype=publicSenderPosts"  class="services"></a></li>
            <li><a href="/PetHotel/jsp/showPosts.jsp?pos=0&potype=publicReceivePosts"  class="tricks"></a></li>
			<li><a href="/PetHotel/jsp/showPosts.jsp?pos=0&potype=publicSharePosts" class="breeds"></a></li>
		</ul>
		</div>
		
		<div class="mask">
			<div class="pmenu"><!--个人主页菜单-->
				<div id="blogHomeDiv">
					<a href="/PetHotel/ShowOtherUserServlet?uid=${requestScope.user.uid }" class="blogHome"></a>
				</div>
				<div id="blogPetsDiv">
					<a href="/PetHotel/FindAllPetsByUidServlet?uid=${requestScope.user.uid }&choice=1" class="blogPets"></a>
				</div>
				
			</div><!-- pmenu end-->
				
				
			<div class="slider-body">
				
				<div class="panel">     
<!-- ******************** Write Here*******************************-->	
<div class="post">
  <p><img src="/PetHotel/upload/images/photo/${sessionScope.user.headportrait }" width="199" height="171"></p>
  <table width="202" height="131" border="0">
    <tr>
      <td width="196" align="left" valign="middle"><img src="images/man.JPG" width="22" height="22">欢迎萌萌哒的你：${sessionScope.user.uname }</td>
    </tr>
    </table>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
</div>
<div class="usersRanking">
 &nbsp;&nbsp;&nbsp;&nbsp;
 
 <hr/>
 

  <!-- 使用JSTL中c:forEach循环便利request级别中的lstCustoemrs对象 --> 
  <c:forEach items="${requestScope.lstPets }" var="pets">
  <table width="510" height="170" border="0">
   <tr>
     <td width="137" rowspan="2" align="center" valign="middle"><img src="/PetHotel/upload/images/photo/${pets.pfid}" width="143" height="213"></td>
     <td height="54" colspan="3" align="center">Ta的宠物信息：</td>
     </tr>
   <tr>
     <td height="110" colspan="3" valign="top">  
     <table width="300" border="0" >
  <tr>
  <td height="20" align="center" valign="middle">宠物主人：</td>
	<td bgcolor="lavender" align="center" valign="middle"> ${user.uname }
   	</td></tr>
   	<tr>
          <td height="20" align="center" valign="middle">宠物昵称：</td>
       
    <td bgcolor="lavender" align="center" valign="middle">${pets.pnickname }</td></tr>
     <tr>
          <td height="20" align="center" valign="middle">宠物种类：</td>

   <td bgcolor="lavender" align="center" valign="middle">${pets.ptype }</td></tr>
            <tr>
          <td height="20" align="center" valign="middle">宠物品种：</td>
<td bgcolor="lavender" align="center" valign="middle">${pets.pvariety }</td></tr>
            <tr>
          <td height="20" align="center" valign="middle">宠物出生年月：</td>
<td bgcolor="lavender" align="center" valign="middle">${pets.pbirthday }</td></tr>
            <tr>
          <td height="20" align="center" valign="middle">性别：</td>
<td bgcolor="lavender" align="center" valign="middle">${pets.pgender }</td></tr>
           <tr>
             <c:if test="${!empty pets.pmark }">
         	  <td height="20" align="center" valign="middle">备注：</td>
			 <td bgcolor="lavender" align="center" valign="middle">${pets.pmark }</td>
           </c:if>
          </tr>

  
     </table></td>
   </tr>
   </table>
   <hr/>
    </c:forEach>
</div>
  



<!-- ***********************End     *******************************-->	
				</div><!-- panel end -->
			</div>   <!-- slider-body -->
		</div> <!-- .mask -->
		
		<!-- ---------------------- -->
	<%  
		try {
				IFriendsDao friendDao = new FriendsDaoImpl();
				List<Friends> lstFriends = new ArrayList<Friends>();
				Users user = (Users)pageContext.getSession().getAttribute("user");
				lstFriends = (ArrayList<Friends>) friendDao.selectByUid(user.getUid());
			%>
			<div style="float:left;margin-left:20px; width:200px; border-color:#FC6; border-width:5px; border-style:double; text-align: center;">
		  <table width="100%" align="center">
		    <tr>
		      <td height="120" colspan="2"><img src="/PetHotel/upload/images/photo/${sessionScope.user.headportrait }" width="150" height="139"></td>
		    </tr>
		    <tr>
		      <td width="80">昵称：</td>
		      <td width="100">${sessionScope.user.uname }</td>
		    </tr>
		    <tr>
		      <td>积分：</td>
		      <td>${sessionScope.user.reputation }</td>
		    </tr>
		    <tr  align="center">
		    <td colspan="2"> <a href="/PetHotel/LogoutServlet">退出</a></td>
		    </tr>
		  </table>
		  <p>好友列表</p>
		  <div style="width:100%; max-height:200px; overflow-y:scroll;">
		  <%
		  IUsersBiz myuserBiz = new UsersBizImpl();
		  for (Friends friend : lstFriends) {
			  String uname=myuserBiz.findById(friend.getUse_uid()).getUname();
			  String photo=myuserBiz.findById(friend.getUse_uid()).getHeadportrait();
		  %>
		  <div style="width:100%; margin-bottom:5px;" >
		    <div style="width:100%; height:50px;">
		      <div style=" width:40%; float:left;"><img src="/PetHotel/upload/images/photo/<%=photo %>" width="40" height="40"></div>
		      <div style=" text-align:left; width:55%; margin-top:20px; float:left;"><a href="javascript:showFriend('friend<%=friend.getFsid() %>')"><%=uname %></a></div>
		    </div>
		    <div id="friend<%=friend.getFsid() %>" name="friend" style=" display:none; width:100%; height:20px;">
		      <div style=" width:50%; float:left;"><input type="submit" name="button2" id="button2" onClick="javascript:ShowOtherUser(<%=friend.getUse_uid() %>)" value="进入主页"></div>
		      <div style=" width:50%; float:left;"><label><input type="button" name="button" id="button<%=friend.getFsid() %>" onClick="javascript:DeleteById(<%=friend.getFsid() %>)" value="删除好友"></label></div>
		    </div>
		    
		  </div>
		  <%} %>
		  </div>
		  <div style=" width:90%; height:20px; border-width:4px; border-style:double; border-color:#09F;margin-left:5px;"><a href="/PetHotel/jsp/searchFriend.jsp">添加好友+</a></div>
		</div>
		
	<% } catch (java.lang.NullPointerException e) {
		
	}
      %> 	
		
		<div class="clear"></div>
	</div><!-- hero-slider end-->
</div>   <!-- content end-->
<!------------------------  -->
    <div id="footer">Copyright © CPU Group All rights reserved.</div>

</body>
</html>
