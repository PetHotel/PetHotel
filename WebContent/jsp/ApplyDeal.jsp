<%@page import="org.cpu.pethotel.po.Users"%>
<%@page import="org.cpu.pethotel.po.Friends"%>
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
<title>申请交易</title>
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
.post{
	width:650px;
	height:350px;
	background-color:#FFC;
	border-color:red;
	border-style:solid;
	border-width:medium;
	margin-left:40px;
}

.login{
	width:600px;
	max-height:50px;
}

/**** End css        ****/
</style>


</head>
<body>



<% String choice = (String)pageContext.getRequest().getAttribute("choice");
   if(choice.equals("3")){
	} 
   else if(choice.equals("1")){%>
	   <script>
	 	alert("申请成功了喵！！！！");
	 	this.window.opener = null;
		window.close();
	   </script>
 <%   }else{%>     
 	<script>alert("申请失败，请重试");</script>
   <% }   %>

<div id="wrapper">
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
	<div class="slider-body">
<div class="post" style="margin-left:105px;">
  <p>注意事项：<br /><br />
    1.填写订单时请注意订单双方姓名正确；<br /><br />
    2.请正确填写交易开始和交易结束日期；<br /><br />
    3.交易开始前请双方商榷相关宠物赔偿机制，并提供宠物健康证明；<br /><br />
    4.交易进行时寄养方可以在指定地方查看宠物状态及相关照片和视频；<br /><br />  
    5.对方同意申请后您可在个人主页的寄领详情中查看订单，对方拒绝则会在个人主页消息页面中的申请消息中看到拒绝消息。<br/><br/>
     </p>
</div>	
	
     <div class="usersRanking">
 <form id="formInner" name="formInner" method="post" action="/PetHotel/ApplyDealServlet">
      <table height="500px" border="0" align="center" bgcolor="#FFFFCC">
        <tr>
          <td height="35" colspan="2" align="center" valign="middle">交易申请</td>
          </tr>      
        <tr>
          <td height="35" align="center" valign="middle">委托人姓名：</td>
          <td height="35" align="center" id="senderName"><a href="ShowOtherUserServlet?uid=${ requestScope.uid}">${requestScope.uname }</a></td>
        </tr>
        <tr>
          <td height="35" align="center" valign="middle">申请人姓名：</td>
          <td height="35" align="center" id="receiveName">${sessionScope.user.uname }</td>
        </tr>
        <tr>
          <td height="35" align="center" valign="middle">宠物种类：</td>
          <td height="35" align="center" id="petType">${requestScope.pettype }</td>
        </tr>
        <tr>
          <td height="35" align="center" valign="middle">开始时间：</td>
            <td height="35" align="center"><input id="bdae" class="Wdate" name="bdate" type="text" onfocus="WdatePicker({ minDate:'%y-%M-%d', maxDate:'#F{$dp.$D(\'edate\')}' })" /></td>
        </tr>
        <tr>
          <td height="25" align="center" valign="middle">结束时间：</td>
            <td height="25" align="center"><input id="edate" class="Wdate" name="edate" type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'bdate\')}' })" /></td>
        </tr>
        <tr>
          <td height="50" align="center" valign="middle">想说的话：</td>
          <td height="50" align="center"><label>
            <textarea name="umark" id="umark" cols="70" rows="3" style="resize:none;"></textarea>
          </label></td>
        </tr>
        <tr>
          <td height="35" colspan="2" align="center" valign="middle" bgcolor="#FFFFCC">
              <input type="hidden" id="poid" name="poid" value="${requestScope.poid }" />
          	  <input type="hidden" id="uid" name="uid" value="${requestScope.uid }" />
          	  <input type="hidden" id="pid" name="pid" value="${requestScope.pid }" />
          	  <input type="hidden" id="user_uid" name="user_uid" value="${sessionScope.user.uid }" />
              <input type="hidden" id="uname" name="uname" value="${requestScope.uname}" />
              <input type="hidden" id="pettype" name="pettype" value="${requestScope.pettype }" />
              <input type="hidden" id="receiveuname" name="receiveuname" value="${sessionScope.user.uname }" />
              <input type="submit" name="submit" id="submit" value="提交订单"/>
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
              <input type="button" name="cancel" id="cancel" onClick="" value="取消订单">
        </tr>

      </table>
</form>
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
</div>
</body>
</html>
