<%@page import="org.cpu.pethotel.po.Posts"%>
<%@page import="org.cpu.pethotel.po.Users"%>
<%@page import="org.cpu.pethotel.po.Friends"%>
<%@page import="org.cpu.pethotel.dao.impl.PostsDaoImpl"%>
<%@page import="org.cpu.pethotel.dao.IPostsDao"%>
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
<link rel=”icon” href=”/favicon.ico” mce_href=”/favicon.ico” type=”image/x-icon”>
<link rel=”shortcut icon” href=”/favicon.ico” mce_href=”/favicon.ico” type=”image/x-icon”>

<script type="text/javascript">
	function register() {
		window.location = "/PetHotel/jsp/register.jsp";
	}
	
</script>

<script type="text/javascript">
	function login(){
		window.location = "/PetHotel/jsp/login.jsp";
	}
</script>

<script language="javascript" type="text/javascript">

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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>萌宠“hotel”</title>

<script type='text/javascript' src='js/jquery.js'></script>
<link href="css/styleM.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/style.css" />
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


/**** End css        ****/
</style>


</head>
<body>
<c:if test="${empty sessionScope.user }">
<script type="text/javascript">login();</script>
</c:if>

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
			<div class="slider-body">
				
				<div class="panel">     <!---->
<!-- ******************* Write Here ************************************-->	
	
	<img src="/PetHotel/jsp/images/welcome1.gif" width="558" height="55" /><br />
	<img src="/PetHotel/jsp/images/pic01.jpg" alt="" width="311" height="220" class="leftimg" /><br/><span class="pinktext"><br/><br/>欢迎光临萌宠“hotel”！！！</span> <br/><br/><br /> <span class="pinktext2">最贴心的宠物服务信息，最热的宠物新鲜事，尽在萌宠hotel--</span><br/><br/><br /><span class="pinktext">--萌宠萌萌哒！！！</span><br />
				
<div id="bodychild">	
<div id="outercontainer">			
 <!-- SLIDER -->
        <div id="outerslider">
        	<div id="slidercontainer">
                <section id="slider">

                    <div id="feature_gallery">
                          <div class="bigimg">
                              <img src="images/slider/s11.jpg" alt="" class="change" />
                              <div class="slidedesc"></div>
                          </div>
                          <div class="bigimg">
                              <img src="images/slider/s13.jpg" alt="" class="change" />
                    	           <div class="slidedesc"></div>
                          </div>
                          <div class="bigimg">
                              <img src="images/slider/s3.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                          <div class="bigimg">
                              <img src="images/slider/s5.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                          <div class="bigimg">
                              <img src="images/slider/s6.jpg" alt="" class="change" />
                              <div class="slidedesc"></div> 
                          </div>
                          <div class="bigimg">
                              <img src="images/slider/s10.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                            <div class="bigimg">
                              <img src="images/slider/s12.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s12.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s15.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s16.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s17.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s15.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s16.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                           <div class="bigimg">
                              <img src="images/slider/s17.jpg" alt="" class="change" />
                               <div class="slidedesc"></div>
                          </div>
                          
                          
                    </div>
                    <div id="pager-container">
                     	<a href="#" id="mycarousel-prev"></a> <a href="#" id="mycarousel-next"></a>                     
                    </div>
 
                </section>
            </div>
        </div>
        <!-- END SLIDER -->
</div>
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
<!-- Javascript
================================================== -->

<!-- Slider -->
<script type="text/javascript" src="js/jquery.jcarousel.pack.js"></script>
<script type="text/javascript" src="js/gallery.js"></script>
<script type="text/javascript" src="js/jquery.cycle.all.min.js"></script>

</body>
</html>
