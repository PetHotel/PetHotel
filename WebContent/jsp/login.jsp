<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mail</title>
<script type="text/javascript">
	function register(){
		window.location="register.jsp";
	}
	
	function checkEmpty(){
		var username = document.getElementById("username");
		var password = document.getElementById("password");
		if(username.value!="" && password.value!="")
		{	
			return true;
		}else{
			
			alert("请填写用户名或密码");
			return false;
		}
		return false;
	}
</script>
<style>

body {
	
	font-family: 'Microsoft yahei', sans-serif;
}
.wrap
{
	width:80%;
	margin: 0 auto;
}
.content {
padding-top: 40px;
}
.content p {
padding-top: 40px;
}

.logo
{
	text-align: center;
	padding: 200px;
	display: block;
}
.a{
	 height:40px;
	 width:auto;
	 float:left;
	}


.login{
   height:40px;
   width:80%;
   position:fixed;
   bottom:40px;
   left:20%;
}
.b{
	 height:40px;
	 width:160px;
	 float:left;
	 color:#fccccc;
	 padding-left:20px;

}
.c{
	 height:40px;
	 width:160px;
	 float:left;
	  padding-left:10px;

}
.a input{
	background:;
	padding:0px ;
	border:5px;
	color:#DDDD22;
	outline:none;
	margin: 0px;
	width:150px;
	font-size:30px;
	font-family: 'Microsoft yahei', sans-serif;
	font-weight:500;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-appearance: none;
	border-radius: 2;	
	}
.c input{
	background:#fa9e27;
	padding:0px ;
	border:5px;
	color:#fccccc;
	outline:none;
	margin: 0px;
	width:150px;
	font-size:30px;
	font-family: 'Microsoft yahei', sans-serif;
	font-weight:500;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-appearance: none;
	border-radius: 2;	
	}
.b input{
	background:#fa9e27;
	padding:0px ;
	border:5px;
	color:#fccccc;
	outline:none;
	cursor:pointer;
	margin: 0px;
	width:150px;
	font-size:30px;
	font-family: 'Microsoft yahei', sans-serif;
	font-weight: 500;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-appearance: none;
	border-radius: 0;
	}
.co{
	color:#ffffee;
	
}

</style>	
	</head>
	<body>
	<img alt="" src="/PetHotel/jsp/images/cat.jpg" style="display:inline-block;position:absolute;top:0;left:0;width:100%;height:100%;">
		<div class="wrap">
			
				<div class="logo"><a href="index.jsp">
			    <h1>PetHotel</h1></a>
</div>
			  <div class="login">
				      <form id="form"  method="post"  action="/PetHotel/LoginServlet" >
				        <div class="a">				          
				            <label class="co" width="116" style="font-size:30px;display:inline-block;height:40px;">用户名：</label>
				            <input name="uname" type="text" id="uname" value="" style="display:inline-block;height:40px;" >
				            <label class="co" width="82" style="font-size:30px;display:inline-block;height:40px;">密码：</label>
				            <input name="password" type="password" id="password" value="" style="display:inline-block;height:40px;">                           
                             </div>
                             <div class="b">
                             <input type="submit" name="submit" id="submit" value="登录"></div>
                            <div class="c">                       
				             <input type="button" name="login" id="button" onClick="javascript:register();"  value="注册">				         
                            </div>				       
		        </form></div>
				  <div class="clear"> </div>

			
		</div>

</body>
</html>