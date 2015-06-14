<%@page import="org.cpu.pethotel.po.Posts"%>
<%@page import="org.cpu.pethotel.dao.impl.PostsDaoImpl"%>
<%@page import="org.cpu.pethotel.dao.IPostsDao"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="org.cpu.pethotel.biz.IUsersBiz" %>
<%@page import="org.cpu.pethotel.biz.impl.UsersBizImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>萌宠Hotel</title>




<script type='text/javascript' src='js/jquery.js'></script>
<link href="css/styleO.css" rel="stylesheet" type="text/css">

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
#mainContent{
	
	
}

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
	document.getElementById("mreply").style.display="none";
	// 将需要显示的回复表单行对象进行显示
	document.getElementById(id).style.display = "block";
}
function hiddenReply(id){
	document.getElementById(id).style.display = "none";
	document.getElementById("mreply").style.display="block";
}
function deletePosts(poid){
	if(confirm('确定删除吗?')){
		 window.location="/PetHotel/DeletePostsServlet?poid="+poid;
	}
}
function deletePosts(poid,potype){
	if(confirm('确定删除吗?')){
		 runDel(poid,potype);
	}
}
function createXMLHttpRequest(){
	if(window.ActiveXObject){
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
	}
}
function runDel(poid,potype){

	createXMLHttpRequest();
	xmlHttp.onreadystatechange = processor;
	
	xmlHttp.open("GET","/PetHotel/DeletePostsServlet?poid="+poid+"&potype="+potype);         //Servlet
	xmlHttp.send(null);

	
}
function processor(){
	
	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
			
			var result = xmlHttp.responseText;
			if(result=="true"){
				delEx();
			}
		}
	}
	
}
function delEx(){
	alert("已后台删除，请刷新页面");
    this.window.opener=null;
    window.close();
}
</script>
</head>

<body>


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
<form id="formInner" name="formInner" method="post" action="/PetHotel/ApplyServlet">
	  <table  style="margin-left:108px;"><!-- width="579" height="558" -->
                    <tr height="50" align="center" >
                      <td colspan="2" align="center" ><strong style="font-size:20px;">&nbsp;求寄养帖子内容</strong><br/></td>
                    </tr>
                    <tr height="30">
                      <td colspan="2" width="70">  *发帖人：<a href="ShowOtherUserServlet?uid=${ requestScope.users.uid}">${ requestScope.users.uname}</a>
                      &nbsp;&nbsp; *发帖时间：${ requestScope.posts.potime }<br/></td>
                    </tr>
                     <c:if test="${requestScope.posts.uid == sessionScope.user.uid }"> <!--/////////////////////////////// ------------------ -->
                    <tr  height="30" ><!-- style="display:none;"-->
                      <td colspan="2"><a href="javascript:deletePosts('${ requestScope.posts.poid}','${ requestScope.posts.potype}')">删除</a></td>
                    </tr>
                    </c:if>
                    <tr height="20">
                      <td colspan="2">*想收养的宠物类型：${ requestScope.posts.pettype }</td>
                     
                    </tr>
                    <tr height="20">
                    	<td>*收养估计开始时间：${requestScope.posts.pobegintime }&nbsp;&nbsp;
                    	
                    	                  结束时间：${requestScope.posts.poendtime }  </td>
                    </tr>
                    <tr style="min-height:20px;">
                      <td colspan="2"><p>*帖子内容</p>
                      <p>
                       ${ requestScope.posts.pocontext}
                                            
                      </p></td>
                    </tr>
                      <tr  height="20">
                      <c:if test="${!empty requestScope.posts.pomark}">
                      <td colspan="2"><a href="/PetHotel/upload/postsAppendix/${requestScope.posts.pomark}">*帖子相关附件下载</a>(右击另存为)</td>
                      </c:if>
                    </tr>
                    <tr  height="20">
                     <c:if test="${empty requestScope.imgCount }">
							 <td colspan="2">*相关图片（共 0 张图片）</td>
                      </c:if>
                     <c:if test="${!empty requestScope.imgCount }"> 
                            <td colspan="2">*相关图片（共 ${requestScope.imgCount} 张图片）</td>
                     </c:if>
                    </tr>
                    <tr>
                      <td colspan="2">
                      <c:if test="${!empty requestScope.lstImg }">
                     <c:forEach items="${requestScope.lstImg }" var="Img">
                          <img alt="" width="200" height="200" src="/PetHotel/upload/images/posts/${Img} ">
					  </c:forEach>
					  </c:if>
					  </td>
                    </tr>
                   
                    <tr>
                      <td colspan="2"  height="50"><label>
                        <input type="hidden" id="poid" name="poid" value="${requestScope.posts.poid}" />
                        <input type="hidden" id="pid" name="pid" value="${requestScope.posts.retyeId}"/>
                        <input type="hidden" id="uid" name="uid" value="${requestScope.posts.uid}"/>
                        <input type="hidden" id="uname" name="uname" value="${requestScope.users.uname} "/>
                        <input type="hidden" id="pettype" name="pettype" value="${requestScope.posts.pettype }">
                    	   <c:if test="${requestScope.posts.uid != sessionScope.user.uid }">
                    	  		<input type="submit" name="submit" id="submit" value="申请寄养"/>
                    	  </c:if>
                      </label></td>
                    </tr>
                  </table>	               
</form>

<!-- 评论框 -->
<%  IPostsDao postDao = new PostsDaoImpl();
	IUsersBiz userBiz = new UsersBizImpl();
	Posts post = (Posts)pageContext.getRequest().getAttribute("posts");
	String uname=userBiz.findById(post.getUid()).getUname();
%>
      <div id="mreply" name="mreply" style="width:710px; height:150px;margin-left:108px;">
          <form name="replyform" method="post" action="/PetHotel/SendReplyServlet">
         
          <div style=" width:710px; height:110px;" >
            <label><textarea name="context" id="context" cols="120" rows="5" style="resize:none;"></textarea></label>
          </div>
          <div style=" width:710px; height:32px; margin:5px;" >
            <label>
              <input type="submit" name="submitm" id="submitm" value="评论">
            </label>
             <!-- 创建一个隐藏域对象存放主信息的ID编号 -->
            <input type="hidden" id="followid" name="followid" value="<%=post.getPoid() %>"/>
            <input type="hidden" id="puname" name="puname" value="<%=uname %>" />
            <input type="hidden" id="potype" name="potype" value="<%=post.getPotype() %>"/>
            <input type="hidden" id="where" name="where" value="in"/>
          </div>
          </form>
      </div>

<!-- 回帖部分 -->
<%            
                List<Posts> lstReply = new ArrayList<Posts>();
                lstReply = postDao.selectByRetypeId(post.getPoid());
	  			for (Posts t : lstReply) {
	  				String tuname=userBiz.findById(t.getUid()).getUname();
	  			%>  
	  			    <div   style="float:right;width:50px;height:26px;margin-top:6px;margin-left:108px;">
      					<a href="javascript:showReply('reply<%=t.getPoid() %>')">回帖</a>
      				</div>
      				<div style=" width:710px; min-height:32px; line-height:30px;margin-left:108px; " >
         				<%=t.getPotime()%>&nbsp; &nbsp;&nbsp;&nbsp;<a href=""><%=tuname %></a>
         					<%=t.getPocontext() %>
      				</div>
      				
		      	     <!-- 信息回复部分 -->
		      	  <div id="reply<%=t.getPoid() %>" name="reply" style="width:710px; height:150px; display:none;margin-left:108px;">
		          <form name="Inreplyform" method="post" action="/PetHotel/SendReplyServlet">
		          <!-- 创建一个隐藏域对象存放主信息的ID编号 -->
		          <input type="hidden" id="followid" name="followid" value="<%=post.getPoid() %>"/>
		           <input type="hidden" id="potype" name="potype" value="<%= post.getPotype() %>"/>
		            <input type="hidden" id="puname" name="puname" value="<%=tuname %>" />
		           <input type="hidden" id="where" name="where" value="in"/>
		          <div style=" width:710px; height:110px;" >
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
 	
<!-- ***********************End     *******************************-->	
				</div><!-- panel end -->
			</div>   <!-- slider-body -->
		</div> <!-- .mask -->
		<div class="clear"></div>
	</div><!-- hero-slider end-->
</div>   <!-- content end-->
<!------------------------  -->
    <div id="footer">Copyright © CPU Group All rights reserved.</div>
</div>
</body>
</html>
