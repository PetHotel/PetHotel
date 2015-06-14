<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
	function confirmPassword(){
		var password=document.getElementById("password");
		var reconfigPassword=document.getElementById("reconfigPassword");
		var chars=document.getElementById("chars");
		if(password.value!=reconfigPassword.value){
			chars.style.display = "inline";
		}else{
			chars.style.display = "none";
		}
	}
	function showNotice() {
		//背景
		
			var wnotice=document.getElementById("noContent");
			var msgObj=document.getElementById("msgDiv");
			document.getElementById("msgShut").onclick = function(){
		    	wnotice.style.display="none";
			}
		 wnotice.style.display = "block";
		
			
		}
	function checkAgree(){
		
		var agreeBtn = document.getElementById("read");
		if(agreeBtn.checked){
			return true;
			}
		else{
			alert("勾选同意用户须知才可成功注册");
			return false;
		}
		
	}
	
	function createXMLHttpRequest(){
		if(window.ActiveXObject){
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}else if(window.XMLHttpRequest){
			xmlHttp = new XMLHttpRequest();
		}
	}
	function checkUname(){

		var unameBtn = document.getElementById("uname");
		var uname = unameBtn.value;
		if(uname!=""){
		
			createXMLHttpRequest();
			xmlHttp.onreadystatechange = processor;
			xmlHttp.open("GET","/PetHotel/PreRegisterServlet?uname="+uname);         //Servlet
			xmlHttp.send(null);
		}

		
	}
	function processor(){

		if(xmlHttp.readyState==4){
			if(xmlHttp.status==200){
			  
				var result = xmlHttp.responseText;
				if(result.toString()=="exist"){
					var checkUnameBtn = document.getElementById("checkUname");
					checkUnameBtn.style.display="inline";
				}else{
					var checkUnameBtn = document.getElementById("checkUname");
					checkUnameBtn.style.display="none";
				}
				
			}
		}
		
	}

</script>
<style>
@charset "utf-8";
/* CSS Document */
body {
	font-family: 'Microsoft yahei', sans-serif;
	height: 100%;
    margin: 0;
}
.usersRanking{
	width: 80%;
	height:100%;
	float:right;
	
}
#chars{
	display:none;
	color:#F00;
	}
#form{
	font-size:25px;
	color:#ffffff;
}
#form input{
	background:;
	padding:0px ;
	border:5px;
	color:#000000;
	outline:none;
	margin: 0px;
	width:180px;
	font-size:20px;
	font-family: 'Microsoft yahei', sans-serif;
	font-weight:500;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-appearance: none;
	border-radius: 2;	
	}
	#form select{
	background:;
	padding:0px ;
	border:5px;
	color:#000000;
	outline:none;
	margin: 0px;
	width:35px;
	font-size:20px;
	font-family: 'Microsoft yahei', sans-serif;
	font-weight:500;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-appearance: none;
	border-radius: 2;
	}
#form input[id="headportrait"]{
	background:;
	padding:0px ;
	border:5px;
	color:#fcfcfc;
	outline:none;
	margin: 0px;
	width:200px;
	font-size:15px;
	font-family: 'Microsoft yahei', sans-serif;
	font-weight:500;
	-webkit-transition: all 0.3s;
	-moz-transition: all 0.3s;
	transition: all 0.3s;
	-webkit-appearance: none;
	border-radius: 2;
	}

#noContent{
	position:absolute;
	top:70px;
	right:80px;
	width:550px;
	height:565px;
	display:none;
	z-index:100;
	background-image: url(images/box2.gif);
	
}
#msgShut{
cursor:auto;
}
.post{
padding:10px 20px 10px 20px;
}

</style>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>注册</title>


</head>
<body>
<img alt="" src="/PetHotel/jsp//images/r.jpg" style="display:block;position:absolute;top:0;left:0;width:100%;height:110%;">
    <div id="noContent">
    
		<div id="msgDetail">
		<div class="post">
用户须知：<br /><br />
1.注册时，带*为必填项，不带为选填项。考虑到本网站用户服务的重要性，您同意在注册时提供真实、完整及准确的企业及个人资料，并及时更新。 如您提供的资料不准确，或本网站有合理的理由认为该资料不真实、不完整、不准确，本网站有权暂停或终止您的注册身份及资料，并拒绝您使用本网站的服务；<br /><br />
2.请您选择填写用户名和密码，并按页面提示提交相关信息。您负有对用户名和密码保密的义务，并对该用户名和密码下发生的所有活动承担责任。您同意邮件服务的使用由您自己承担风险。本网站不会向您所使用服务所涉及相关方之外的其他方公开或透露您的个人资料，法律法规规定除外；<br /><br />
3.严禁传播淫秽、色情等不良信息及有害国家安全利益的消息；<br /><br />
4.网站管理员有权直接删除用户账户，而不做任何通知。<br />
<div align="center" style="margin-top:100px;">
   <input type="checkbox" id="read" value="read"/><label>同意</label>
	<input type="button" id="msgShut" value="关闭"/></div>
</div>
</div>  
	</div>
<div>
	
	</div>
     <div class="usersRanking" style="display:inline-block;position:absolute;top:0%;right:80px;">
      <form id="form" name="form" method="post" action="/PetHotel/RegisterServlet" onsubmit="return checkAgree()" enctype="multipart/form-data">
      <table width="550" height="550" border="0" align="right">
         <tr>
          <td height="60" colspan="2" align="center" valign="bottom"><strong>注册</strong></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">用户账号*：</td>
          <td><input type="text" name="uname" id="uname" onBlur="javascript:checkUname();" />
             <label name="checkUname" id="checkUname" style="font-size:15px;display:none;color:#F00;" >用户已存在</label>
          </td>
          
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">用户密码*：</td>
          <td><input type="password" name="password" id="password" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">确认密码*：</td>
          <td><input type="password" name="reconfigPassword" id="reconfigPassword" onBlur="javascript:confirmPassword();"/>
          <label name="chars" id="chars" style="font-size:15px;" >密码不一致</label></td>
         
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">所在省份*：</td>
          <td><input type="text" name="province" id="province" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">所在城市*：</td>
          <td width="250" height="40"><input type="text" name="city" id="city" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">所在区/县*：</td>
          <td width="250" height="40"><input type="text" name="district" id="district" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">具体地址*：</td>
          <td width="250" height="40"><input type="text" name="address" id="address" /></td>
        </tr>
        <tr>
          <td width="200" height="40" align="right" valign="middle">是否领养*：</td>
<td width="200" height="40"><select name="isAdopt" id="isAdopt" >
                  <option value="1">是</option>
                  <option value="0">否</option>
                </select></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">QQ：</td>
          <td width="250" height="40"><input type="text" name="qq" id="qq" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">Email*：</td>
          <td width="250" height="40"><input type="text" name="email" id="email" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">电话号码*：</td>
          <td width="250" height="40"><input type="text" name="tel" id="tel" /></td>
        </tr>
        <tr>
          <td width="250" height="40" align="right" valign="middle">头像：</td>
          <td width="250" height="40"><input type="file" name="headportrait" id="headportrait" /></td>
        </tr>
        <tr>
          <td height="40" colspan="2" align="center" valign="middle"><input type="submit" name="submit" id="submit"  value="注册" style="display:inline-block;width:70px;" >
         <a id="notice" style="font-size:20px;color:#F00;" href="javascript:showNotice()">请仔细阅读用户须知</a></td>
        </tr>
      </table>
</form>
</div>
</body>
</html>