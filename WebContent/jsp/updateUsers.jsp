<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>更新个人信息</title>

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
	width: 50%;
	height: 450px;
	float:right;
}
.post{
	width:49.8%;
	height:450px;
	border-right-color:#3FF;
	border-right-style:dashed;
	border-right-width:medium;
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
 
 <div id="space">
 
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
      <form name="form1" method="post" action="/PetHotel/UpdateUsersServlet" enctype="multipart/form-data">
  <table width="100%" border="0">
    <tr>
      <td height="30" colspan="2" align="center" valign="middle">更新用户信息</td>
    </tr>

    <tr>
      <td height="25" align="center" valign="middle">所在省：</td>
      <td><input type="text" name=province id="province" value="${requestScope.users.province }"></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">所在市：</td>
      <td><input type="text" name="city" id="city" value="${requestScope.users.city}"></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">所在区：</td>
      <td><input type="text" name="district" id="district" value="${requestScope.users.district}"></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">具体地址：</td>
      <td><input type="text" name="address" id="address" value="${requestScope.users.address}"></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">是否领养：</td>
      <td align="left" valign="middle" >
        <label>
          <select name="isAdopt" id="isAdopt">
            <option value="1">是</option>
            <option value="0">否</option>
          </select>
        </label></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">QQ号码：</td>
      <td><input type="text" name="qq" id="qq" value="${requestScope.users.qq}"></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">电子邮箱：</td>
      <td><input type="text" name="email" id="email"  value="${requestScope.users.email }"/></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">电话号码：</td>
      <td><input type="text" name="tel" id="tel"  value="${requestScope.users.tel }"/></td>
    </tr>
    <tr>
      <td height="25" align="center" valign="middle">头像：</td>
      <td>
        <input type="hidden" name="ofid" id="ofid" value="${requestScope.users.headportrait}">
      <input type="file" name="headportrait" id="headportrait">
         
      </td>
    </tr>
    <tr>
      <td height="30" colspan="2" align="center" valign="middle"><input type="submit" name="submit" id="submit" value="更新客户信息"></td>
    </tr>
  </table>
  <!--  
<td><input type="password" style="display:none" name="password" id="password" value="${requestScope.users.password }"></td>
<td><input type="uid" style="display:none" name="uid" id="uid" value="${requestScope.users.uid }"></td>
<td><input type="ufid" style="display:none" name="ufid" id="ufid" value="${requestScope.users.ufid }"></td>
<td><input type="reputation" style="display:none" name="reputation" id="reputation" value="${requestScope.users.reputation }"></td>
<td><input type="uname" style="display:none" name="uname" id="uname" value="${requestScope.users.uname }"></td>
<td><input type="status" style="display:none" name="status" id="status" value="${requestScope.users.status }"></td>-->
</form>
</div>
<div class="post">
  <p>&nbsp;</p>
  <p><img src="images/WhiteBackground7.jpg" width="359" height="322"></p>
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
