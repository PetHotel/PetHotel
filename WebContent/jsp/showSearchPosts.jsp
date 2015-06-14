<%@page import="org.cpu.pethotel.po.Posts"%>
<%@page import="org.cpu.pethotel.po.Users"%>
<%@page import="org.cpu.pethotel.po.Friends"%>
<%@page import="org.cpu.pethotel.dao.impl.FriendsDaoImpl"%>
<%@page import="org.cpu.pethotel.dao.IFriendsDao"%>
<%@page import="org.cpu.pethotel.dao.impl.PostsDaoImpl"%>
<%@page import="org.cpu.pethotel.dao.IPostsDao"%>
<%@page import="org.cpu.pethotel.biz.IUsersBiz" %>
<%@page import="org.cpu.pethotel.biz.impl.UsersBizImpl" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>萌宠Hotel</title>
<link href="css/styleM.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='js/jquery.js'></script>

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

<script language="javascript">
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
function createXMLHttpRequest(){
	if(window.ActiveXObject){
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}
}
function plusApp(poid){

	var appBtn = document.getElementById("app"+poid);
	var fappBtn = document.getElementById("fapp"+poid);
	var cappBtn=appBtn.value;
	var value = parseInt(cappBtn.replace("赞","")) + parseInt(fappBtn.value);
	 appBtn.value= "赞"+value;
	 if(fappBtn.value=="1"){
		   fappBtn.value="-1";
     }else{
		fappBtn.value="1";
	 }
	var poidApp=poid+","+value;
	createXMLHttpRequest();
	xmlHttp.onreadystatechange = processor;
	xmlHttp.open("GET","/PetHotel/PlusAppServlet?poidApp="+poidApp);         //Servlet
	xmlHttp.send(null);

	
}
function processor(){
	


	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
		  
			/*var result = xmlHttp.responseText;
			var m = result.split(",");
			var poid=m[0];
            var app=m[1];
			var appBtn = document.getElementById("app"+poid);
          */
          
		}
	}
	
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
</div>
<!--END THE MAIN CONTAINER-->

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
			
<%
IPostsDao postDao = new PostsDaoImpl();
IUsersBiz userBiz = new UsersBizImpl();
String potype = (String)pageContext.getRequest().getParameter("potype");
String strPos=(String)pageContext.getRequest().getParameter("pos");
//pageContext.getRequest().setCharacterEncoding("UTF-8");
//String params = (String)pageContext.getRequest().getAttribute("params");
//System.out.println(params);
int currentPos=Integer.parseInt(strPos);
Users user=(Users)session.getAttribute("user");
int pageSize=8;
List<Posts> lstPosts=new ArrayList<Posts>();
lstPosts = (List<Posts>)pageContext.getRequest().getAttribute("lstPosts");
int postsCount=0;
%>
 

<%for (Posts post : lstPosts) {
	String uname=userBiz.findById(post.getUid()).getUname();
	postsCount++;
	String context=post.getPocontext();
	if(context.length()>200){
		context=context.substring(0,200);   //取帖子内容的前200字
	}
	
	String pofilelist;                       //解析图片路径
	List<String> lstImg = new ArrayList<String>();
	String[] img;
	int imgCount = 0;                          //图片数目
	if (post.getPofilelist() != null) {
		pofilelist = post.getPofilelist();
		if (pofilelist.indexOf(",") < 0) {
			lstImg.add(pofilelist);
			imgCount = 1;
		} else {

			img = pofilelist.split(",");
			for (int i = 0; i < img.length; i++) {
				lstImg.add(img[i]);
			}
			imgCount = img.length;
		}
	}
	
	%>

    <div style="width:820px; margin:10px;" >
      <div style="width:820px; height:32px;">
        <div style="width:600px; height:32px; float:left; margin-right:5px;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=uname%>&nbsp; <%=post.getPotime() %></div>
        <div style="width:90px; height:32px; padding-top:1px; float:left; margin-right:5px;" ><label>
          
          <a href="/PetHotel/ShowPostsServlet?poid=<%=post.getPoid() %>&potype=<%=post.getPotype() %>" target="_blank">查看详情</a>
        </label></div>
        <div style="width:55px; height:32px; text-align:center; float:left; margin-right:5px;">
          <a href="javascript:showReply('reply<%=post.getPoid() %>')">回复</a>
        </div>
        <div style="width:55px; height:32px; text-align:center; float:left;"> 
          <input type="button" name="app" class="app" id="app<%=post.getPoid() %>" value="赞<%=post.getAppreciate() %>" onClick="javascript:plusApp('<%=post.getPoid()%>');" />
          
            <input type="hidden" name="fapp" id="fapp<%=post.getPoid()%>" value="1"/>
        </div>
      </div>
      <!-- 帖子内容 -->
      <div style="height:80px; width:820px; padding:5px; margin:5px; border-top-color: #999; border-top-width: 5px; border-top-style: solid; border-right-color: #999;
                  border-right-width: 5px; border-right-style: solid;-moz-border-radius:10px; -webkit-border-radius:10px;"><%=context %>
       </div>
      <br/>&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
      <%if(imgCount!=0){
        for(String aimg: lstImg){%>
     	 <img width="100px" height="100px" alt="" src="/PetHotel/upload/images/posts/<%=aimg%>">
      <%} }%>
      <!-- 信息回复部分 -->
      <div id="reply<%=post.getPoid() %>" name="reply" style="width:710px; height:150px; display:none;">
          <form name="replyform" method="post" action="/PetHotel/SendReplyServlet?where=outup">
          <!-- 创建一个隐藏域对象存放主信息的ID编号 -->
          <input type="hidden" id="followid" name="followid" value="<%=post.getPoid() %>"/>
           <input type="hidden" id="puname" name="puname" value="<%=uname %>" />
           <input type="hidden" id="potype" name="potype" value="<%=potype%>"/>
            
          <div style=" width:710px; height:110px; margin-left:15px;" >
            <label><textarea name="context" id="context" cols="120" rows="5" style="resize:none;"></textarea></label>
          </div>
          <div style=" width:710px; height:32px; margin:5px;" >
            <label>
              <input type="submit" name="submit2" id="submit2" value="回复">
            </label>
            <label>
              <input type="button" name="button4" id="button4" onClick="javascript:hiddenReply('reply<%=post.getPoid() %>')" value="关闭">
            </label>
          </div>
          </form>
      </div>
    <% List<Posts> lstReply = postDao.selectByRetypeId(post.getPoid());
                int pos=0;
	  			for (Posts t : lstReply) {
	  				pos++;
	  				if(pos>5){ break;}
	  				String tuname=userBiz.findById(t.getUid()).getUname();
	  			%>  
	  			    <div   style="float:right;width:50px;height:26px;margin-top:6px;">
      					<a href="javascript:showReply('reply<%=t.getPoid() %>')">回帖</a>
      				</div>
      				<div style=" width:710px; min-height:32px; line-height:30px;margin:5px; " >
         				<%=t.getPotime()%>&nbsp; &nbsp;&nbsp;&nbsp;<a href=""><%=tuname %></a>
         					<%=t.getPocontext() %>
      				</div>
      				
		      	     <!-- 信息回复部分 -->
		      	  <div id="reply<%=t.getPoid() %>" name="reply" style="width:710px; height:150px; display:none;">
		          <form name="Inreplyform" method="post" action="/PetHotel/SendReplyServlet">
		          <!-- 创建一个隐藏域对象存放主信息的ID编号 -->
		          <input type="hidden" id="followid" name="followid" value="<%=post.getPoid() %>"/>
		           <input type="hidden" id="puname" name="puname" value="<%=tuname %>" />
		           <input type="hidden" id="potype" name="potype" value="<%=potype%>"/>
		           <input type="hidden" id="where" name="where" value="out"/>
		          <div style=" width:710px; height:110px; margin:5px;" >
		            <label><textarea name="context" id="context" cols="120" rows="5" style="resize:none;"></textarea></label>
		          </div>
		          <div style=" width:710px; height:32px; margin:5px;" >
		            <label>
		              <input type="submit" name="submit2" id="submit2" value="回复">
		            </label>
		            <label>
		              <input type="button" name="button4" id="button4" onClick="javascript:hiddenReply('reply<%=t.getPoid() %>')" value="关闭">
		            </label>
		          </div>
		          </form>
		      </div>
      				
      				
    	<%}%>
      
</div>
    <p>&nbsp;</p>
<%
}
%>
 <div align="center">	<% if(currentPos-8<0){ %>
    <label style="text-decoration:underline;">上一页</label>
     <%}else{
       
     %>
     
	<a href="/PetHotel/SearchPostsServlet?pos=<%=Integer.toString(currentPos-8) %>&type=1&params=${requestScope.params}"  >上一页</a>
	  <%} %>
	  
	  <% if(postsCount<8) {%>
	   	 <label style="text-decoration:underline;">下一页</label>
	   <%}else if(postsCount==0){ %>
	      	<label style="text-decoration:underline;">下一页</label>
	   <%} else{%>
		 <a href="/PetHotel/SearchPostsServlet?pos=<%=Integer.toString(currentPos+8) %>&type=1&params=${requestScope.params}" >下一页</a>
	 <%} %>
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
				Users suser = (Users)pageContext.getSession().getAttribute("user");
				lstFriends = (ArrayList<Friends>) friendDao.selectByUid(suser.getUid());
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
