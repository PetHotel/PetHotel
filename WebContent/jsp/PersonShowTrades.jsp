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
<title>寄领详情</title>

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


/**** End css        ****/
</style>
<script type="text/javascript">
 <!-- 自定义一个JS脚本方法实现对指定数据记录的删除操作 -->
 function deleteById(id){
	 // 删除前的判断确认
	 if(confirm('确定删除这条记录吗?')){
		// 页面跳转至制定的servlet并携带参数
		 window.location="/nk09-CRUD/DeleteCustomersServlet?cusid="+id;
	 }
 }

 function selectAllCheckBox(checkBoxName){
	 // 步骤1：获取复选框对象的集合
	 var lstCusids = document.getElementsByName(checkBoxName);
	 // 步骤2：使用循环便利节点集合对象
	 for(var i=0; i<lstCusids.length; i++){
		 // 步骤3：判断节点是否被选中
		 if(lstCusids[i].checked == false){
			 // 步骤4：选中该节点对象
			 lstCusids[i].checked = true;
		 }
	 }
 }
 
 function selectNoneCheckBox(checkBoxName){
	 // 步骤1：获取复选框对象的集合
	 var lstCusids = document.getElementsByName(checkBoxName);
	 // 步骤2：使用循环便利节点集合对象
	 for(var i=0; i<lstCusids.length; i++){
		 // 步骤3：判断节点是否被选中
		 if(lstCusids[i].checked == true){
			 // 步骤4：选中该节点对象
			 lstCusids[i].checked = false;
		 }
	 }
 }

 function selectReverseCheckBox(checkBoxName){
	 // 步骤1：获取复选框对象的集合
	 var lstCusids = document.getElementsByName(checkBoxName);
	 // 步骤2：使用循环便利节点集合对象
	 for(var i=0; i<lstCusids.length; i++){
		 // 步骤3：判断节点是否被选中
		 if(lstCusids[i].checked == false){
			 // 步骤4：选中该节点对象
			 lstCusids[i].checked = true;
		 }else{
			// 步骤5：取消选中该节点对象
		    lstCusids[i].checked = false;
		 }
	 }
 } 
</script>

<script type="text/javascript">
	function cancel(tid){
		confirm("真的要删除这条申请吗？喵？");
		window.location = "/PetHotel/ApplyCancelServlet?tid="+tid;
	}
</script>

<script type="text/javascript">
	function dealEnd(tid){
		confirm("交易真的完成了吗？喵？");
		window.location = "/PetHotel/DealEndServlet?tid="+tid;
	}
</script>

<script type="text/javascript">
	function deleteTrade(tid){
		confirm("真的要删除这条交易记录吗？喵？");
		window.location = "/PetHotel/HiddenTradeServlet?tid="+tid;
	}
</script>

<script type="text/javascript">
	function refuse(tid){
		confirm("真的要拒绝这条申请吗？喵？");
		window.location = "/PetHotel/TradeRefuseServlet?tid="+tid;
	}
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
					<a href="/PetHotel/jsp/PersonIndex.jsp" class="blogHome"></a>
				</div>
				<div id="blogPetsDiv">
					<a href="/PetHotel/FindAllPetsByUidServlet?choice=0" class="blogPets"></a>
				</div>
				<div id="blogFriendsDiv">
					<a href="/PetHotel/jsp/FriendTrends.jsp?pos=0"  class="blogFriends"></a>
				</div>
				<div id="blogTradesDiv">
					<a href="/PetHotel/ShowTradesServlet" class="blogTrades"></a>
				</div>
				<div id="blogMessagesDiv">
				   <a href="/PetHotel/jsp/PersonMessage.jsp?type=0"  class="blogMessages"></a>
				</div>
				<div id="blogSendDiv">
				   <a href="/PetHotel/jsp/showAllOwnPosts.jsp?pos=0"  class="blogSend"></a>
				</div>
					
			</div><!-- pmenu end-->
				
				
			<div class="slider-body">
				
				<div class="panel">     
<!-- ******************** Write Here*******************************-->	
<!-- 步骤1：判断是否获取到lstEmps对象 EL表达式-->


<table width="100%" border="0" class="mytable">
  <tr>
        <td width="10%" align="center" valign="middle" bgcolor="#FFFFCC">订单编号</td>
    <td width="10%" align="center" valign="middle" bgcolor="#FFFFCC">委托人姓名</td>
    <td width="9%" align="center" valign="middle" bgcolor="#FFFFCC">宠物昵称</td>
    <td width="10%" align="center" valign="middle" bgcolor="#FFFFCC">宠物种类</td>
    <td width="11%" align="center" valign="middle" bgcolor="#FFFFCC">宠物品种</td>
    <td width="11%" align="center" valign="middle" bgcolor="#FFFFCC">申请人姓名</td>
    <td width="11%" align="center" valign="middle" bgcolor="#FFFFCC">开始时间</td>
    <td width="11%" align="center" valign="middle" bgcolor="#FFFFCC">结束时间</td>
    <td width="14%" align="center" valign="middle" bgcolor="#FFFFCC">订单状态</td>
    <td width="14%" align="center" valign="middle" bgcolor="#FFFFCC">操作</td>
  </tr>
  <!-- 使用JSTL中c:forEach循环便利request级别中的lstEmps对象 --> 
  <c:forEach items="${requestScope.lstTrades }" var="trade">
  <tr>
   	
   	<td bgcolor="lavender" align="center" valign="middle">${trade.tid }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.uname }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.pname }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.ptype }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.pvariety }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.u1name }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.tBeginDate }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.tEndDate }</td>
    <td bgcolor="lavender" align="center" valign="middle">${trade.tStatus }</td>
    <td bgcolor="lavender" align="center" valign="middle">
    <c:if test="${trade.tStatus == 'Apply' && sessionScope.user.uname == trade.u1name}">
    	<input type="button" name="button" id="button" onclick="javascript:cancel(${trade.tid});" value="撤销交易申请"/>  
    </c:if>
    <c:if test="${trade.tStatus == 'Apply' && sessionScope.user.uname == trade.uname}">
    	<input type="button" name="button1" id="button1" onclick="javascript:refuse(${trade.tid});" value="拒绝交易申请"/>  
    </c:if>
    <c:if test="${trade.tStatus == 'Deal'}">
    	<input type="button" name="button2" id="button2" onclick="javascript:dealEnd(${trade.tid});" value="申请结束交易"/>  
    </c:if>
    <c:if test="${trade.tStatus == 'DealEndApplyByAnother' }">
    	<input type="button" name="button4" id="button4" onclick="javascript:dealEnd(${trade.tid});" value="确认结束交易"/>  
    </c:if>
    <c:if test="${trade.tStatus == 'Done' || trade.tStatus  == 'Refused'}">
    	<input type="button" name="button3" id="button3" onclick="javascript:deleteTrade(${trade.tid});" value="删除交易信息"/>  
    </c:if>          
    </td>
  </tr>  
  </c:forEach>
</table>


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
