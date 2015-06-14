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
<title>更新宠物信息</title>

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
	
	margin-left:80px;
}

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
<!--END THE MAIN CONTAINER-->
    </div>
	
<div class="login">
 <c:if test="${empty sessionScope.user }">
 	<script>
		alert("登陆后才可以操作哟，瞄！！！！");
		location="/PetHotel/jsp/index.jsp";
	</script>
   </c:if>
    </div>

<!------------------------------------------   -->
    <div id="content"><div id="hero-slider">
	  <div id="mainMenu">
			<<ul>
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
					<a href="/PetHotel/jsp/PersonIndex.jsp" class="blogHome"></a>
				</div>
				<div id="blogPetsDiv">
					<a href="/PetHotel/jsp/Pets.jsp" class="blogPets"></a>
				</div>
				<div id="blogFriendsDiv">
					<a href="/PetHotel/jsp/FriendTrends.jsp"  class="blogFriends"></a>
				</div>
				<div id="blogTradesDiv">
					<a href="/PetHotel/jsp/PersonShowTrades.jsp" class="blogTrades"></a>
				</div>
				<div id="blogMessagesDiv">
				   <a href="/PetHotel/jsp/Message.jsp"  class="blogMessages"></a>
				</div>
				<div id="blogSendDiv">
				   <a href="/PetHotel/jsp/ShowAllOwnPosts.jsp"  class="blogSend"></a>
				</div>
					
			</div><!-- pmenu end-->
				
				
			<div class="slider-body">
				
				<div class="panel">     
<!-- ******************** Write Here*******************************-->	

<div class="usersRanking">
 &nbsp;&nbsp;&nbsp;&nbsp;
 
 <hr/>
  <table width="510" height="170" border="0">
   <tr>
     <td width="200" rowspan="2" align="center" valign="middle"><img src="/PetHotel/upload/images/photo/${pets.pfid}" width="143" height="213"></td>
     </tr>
   <tr>
     <td height="110" colspan="3" valign="top">  
<form action="/PetHotel/UpdatePetsServlet" method="post" enctype="multipart/form-data" name="form1">
  <table width="345" border="0">

    <tr>
      <td width="80%" height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFCC">更新宠物信息</td>
              <!-- 创建一个隐藏域对象存放信息的ID编号 -->
     <td width="20%"><input type="hidden" id="pid" name="pid" value="${requestScope.pets.pid}" readonly="readonly">
     				<input type="hidden" id="opfid" name="opfid" value="${requestScope.pets.pfid}" readonly="readonly">
     
     </td>
    </tr>
    <tr>
      <td width="40%" height="35" align="center" valign="middle" bgcolor="#F3F9FA">宠物昵称：</td>
      <td width="60%"><input type="text" name="pnickname" id="pnickname" value="${requestScope.pets.pnickname }"></td>
    </tr>
    <tr>
      <td height="35" align="center" valign="middle" bgcolor="#F3F9FA">种类：</td>
      <td><input type="text" name="ptype" id="ptype" value="${requestScope.pets.ptype}"></td>
    </tr>
    <tr>
      <td height="35" align="center" valign="middle" bgcolor="#F3F9FA">品种：</td>
      <td><input type="text" name="pvariety" id="pvariety" value="${requestScope.pets.pvariety}"></td>
    </tr>
    <tr>
      <td height="35" align="center" valign="middle" bgcolor="#F3F9FA">生日：</td>
      <td><input type="text" name="pbirthday" id="pbirthday"  value="${requestScope.pets.pbirthday }"/>
                        <br/>如：2014-08</td>
    </tr>
        <tr>
      <td height="35" align="center" valign="middle" bgcolor="#F3F9FA">性别：</td>
      <td><div>
        <table width="212">
          <tr>
            <td width="56">
              <input type="radio" name="pgender" value="1" id="pgender_0">
              公</td>
              <td width="55">
              <input type="radio" name="pgender" value="2" id="pgender_1">
              母</td>
                <td width="85">
              <input type="radio" name="pgender" value="3" id="pgender_2">
              其他</td>
          </tr>                              
                 
        </table>
        </div></td>
    </tr>
        <tr>
      <td height="35" align="center" valign="middle" bgcolor="#F3F9FA">宠物图片上传：</td>
      <td><label>
         <input type="hidden" name="ofid" id="ofid" value="${requestScope.pets.pfid}">
        <input type="file" name="pfid" id="pfid">
       
      </label></td>
    </tr>
        <tr>
      <td height="35" align="center" valign="middle" bgcolor="#F3F9FA">备注：</td>
      <td><input type="text" name="pmark" id="pmark"  value="${requestScope.pets.pmark }"/></td>
    </tr>
    <tr>
      <td height="40" colspan="2" align="center" valign="middle" bgcolor="#FFFFCC"><input type="submit" name="submit" id="submit" value="更新宠物信息"></td>
    </tr>
  </table>
</form>
    </td>
    </tr>
  </table>
   <hr/>
</div>

<!-- ***********************End     *******************************-->	
				</div><!-- panel end -->
			</div>   <!-- slider-body -->
		</div> <!-- .mask -->
		<div class="clear"></div>
	</div><!-- hero-slider end-->
</div>   <!-- content end-->
<!------------------------  -->
    <div id="footer">Copyright © CPU Group All rights reserved.</div>
</body>
</html>
