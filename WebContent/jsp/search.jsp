<%@page import="org.cpu.pethotel.po.Friends"%>
<%@page import="org.cpu.pethotel.po.Users"%>
<%@page import="org.cpu.pethotel.dao.impl.FriendsDaoImpl"%>
<%@page import="org.cpu.pethotel.dao.IFriendsDao"%>
<%@page import="org.cpu.pethotel.biz.IUsersBiz" %>
<%@page import="org.cpu.pethotel.biz.impl.UsersBizImpl" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>搜索</title>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
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
.search{
	width: 50%;
	height: 450px;
	background-color:#FFF;
	float:right;
}
.post{
	width:50%;
	height:450px;
	background-color:#FFF;

/**** End css        ****/
</style>


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
</div>
<!--END THE MAIN CONTAINER-->

<div class="login">
 <c:if test="${empty sessionScope.user }">
 	<script>
		alert("登陆后才可以操作哟，瞄！！！！");
		location="/PetHotel/jsp/index.jsp";
	</script>
  </c:if>
 <c:if test="${empty requestScope.check}">
 	<script>
 	    
		window.location="/PetHotel/PreSearchPostsServlet";
	</script>
  </c:if>
     <c:if test="${!empty sessionScope.user}">
    <script>login();</script>
    </c:if>
    </div>

<!------------------------------------------   -->
    <div id="content"><div id="hero-slider">
	  <div id="mainMenu">
		<ul>
			<li><a href="/PetHotel/jsp/index.jsp"  class="home"></a></li>   
			 <li><a href="/PetHotel/jsp/search.jsp"  class="contacts"></a></li>
			<li><a href="/PetHotel/jsp/PersonIndex.jsp" class="aboutus"></a></li>
			<li><a href="/PetHotel/jsp/showPosts.jsp?pos=0&potype=publicSenderPosts"  class="services"></a></li>
            <li><a href="/PetHotel/jsp/showPosts.jsp?pos=0&potype=publicReceivePosts"  class="tricks"></a></li>
			<li><a href="/PetHotel/jsp/showPosts.jsp?pos=0&potype=publicSharePosts" class="breeds"></a></li>
		</ul>
	</div>
		<div class="mask">
			<div class="slider-body">
				
				<div class="panel">     <!---->
<!-- ******************* Write Here ************************************-->					
<div class="search">
<form id="form" name="form" method="post" action="/PetHotel/SearchPostsServlet">
<table width="366" height="450" border="0">

       <tr>
         <td colspan="2"><div align="center">
           <h3>搜索萌宠/小窝：</h3></div></td>
         </tr>
       <tr>
         <td width="178"><div align="center"><strong>帖子种类：</strong></div></td>
         <td width="178">
           <span id="spryselect2">
           <label>
             <select name="potype" id="potype">
               <option value="publicSenderPosts">收养帖子</option>
               <option value="publicReceivePosts">寄养帖子</option>
             </select>
           </label></span>
         </td>
       </tr>
       <tr>
         <td><div align="center"><strong>宠物类型：</strong></div></td>
         <td>
           <span id="spryselect3">
             <label>
               <select name="pettype" id="pettype">
                 <option value="all">全部</option>
                 <option value="猫">猫</option>
                 <option value="狗">狗</option>
                 <option value="乌龟">乌龟</option>
                 <option value="兔子">兔子</option>
                 <option value="蛇">蛇</option>
                 <option value="猪">猪</option>
                 <option value="蜗牛">蜗牛</option>
               </select>
             </label></span>
         </td>
       </tr>
       <tr>
         <td><div align="center"><strong>所在省/市/区：</strong></div></td>
         <td>
           <span id="spryselect4">
             <label>
               <select name="province" id="province">
               <c:forEach items="${requestScope.lstprovince }" var="province">
      	         <option value="${province }">${province }</option>
               </c:forEach>
               </select>
             </label></span>
           <span id="spryselect5">
           <label>
             <select name="city" id="city">
             <c:forEach items="${requestScope.lstcity }" var="city">
      	         <option value="${city }">${city }</option>
               </c:forEach>
             </select>
           </label></span>
           <span id="spryselect6">
           <label>
             <select name="district" id="district">
             <c:forEach items="${requestScope.lstdistrict }" var="district">
      	         <option value="${district }">${district }</option>
               </c:forEach>
             </select>
           </label></span>
         </td>
       </tr>
       <tr>
         <td><div align="center"><strong>预计开始时间：</strong></div></td>
         <td>
           <input id="bdate" class="Wdate" name="bdate" type="text" onfocus="WdatePicker({ minDate:'%y-%M-%d', maxDate:'#F{$dp.$D(\'edate\')}' })" />
         </td>
       </tr>
       <tr>
         <td><div align="center"><strong>预计结束时间：</strong></div></td>
         <td>
           <label>
              <input id="edate" class="Wdate" name="edate" type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'bdate\')}' })" />
           </label>
         </td>
       </tr>
       <tr>
         <td colspan="2" align="center">
           <label>
               <input type="hidden" name="pos" id="pos" value="0">
               <input type="hidden" name="type" id="type" value="0">
             <input type="submit" name="search" id="search" value="搜索">
             
           </label>
          </td>
         </tr>
   </table>
   </form>
</div>
     
     
<div class="post">
<p> </p>
  <p class="pinktext"> <strong>为可爱的宠物宝宝寻找/提供一个</strong></p>
  <p class="pinktext"><strong>温馨的小窝吧！</strong>  <span class="pinktext">
  </span></p>
<span class="pinktext">
<hr/>
  </span><img src="images/WhiteBackground1.jpg" width="324" height="279" /><br />
</div>  
<div id="contact">
 &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
 <img src="images/treat.png" width="333" height="110"/>
 </div>
		
<br/>
          
	
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
