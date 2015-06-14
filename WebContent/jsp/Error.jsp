<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
@charset "utf-8";
/* CSS Document */
body {
	 font-family: 'MS Serif', 'New York', serif;
	height: 100%;
    margin: 0;
}
.usersRanking{
	bottom:20%;
	left:8%;
	width:40%;
	height:65%;
	float:left;	
}
.error{
	top:10%;
	right:10%;
	width:42%;
	height:40%;
	float:right;	
}
.out{
	top:42%;
	right:10%;
	width:42%;
	height:25%;
	float:right;
	color:red;	
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>


</head>
<body>

 	<div class="usersRanking" style="display:inline-block;position:absolute;">
   	 	<img alt="" src="/PetHotel/images/dog.jpg" style="display:inline-block;width:100%;height:100%;">
	</div>
	<div class="error" style="display:inline-block; position:absolute;">
	 <label style="font-size:100px;display:inline-block"><em><strong>E r r o r ! !<br></strong></em></label>
    
     <label style="font-size:60px;display:inline-block"><em><strong>&nbsp; 出 错 啦 ...</strong></em></label>
    </div>
    <div class="out" style="display:inline-block;position:absolute;">
    	<label style="font-size:30px;display:inline-block">${requestScope.error }</label>
    </div>

</body>
</html>