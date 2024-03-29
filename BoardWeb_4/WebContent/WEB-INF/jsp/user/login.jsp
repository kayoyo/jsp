<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* { margin:0; padding: 0; }
*:focus{
	outline:none;
}
 a {text-decoration:none; color: black;}
 .err {color: #585858;}
 #container {width: 450px; background-color: #ddc3d0; 
 			 margin: 0 auto; padding:20px; text-align:center; margin-top: 100px; border-radius: 20px 20px 20px 20px;}
 h1 {margin: 20px; color: white;}
 .pw {margin-right: 73px;}
 .pw, .id {color: white; margin-bottom: 15px; font-weight:bold;}
 input {height : 20px;}
 .btn {margin-bottom: 20px }
 #loginBtn {width: 60px; height: 30px; margin-left: 20px; 
 		color: #727A9A; background-color: #faebd7; font-weight:bold; border:none;}
 #joinBtn {width: 60px; height: 30px; display: inline-block;
 		color: #727A9A; background-color: #faebd7; font-weight:bold; border:none; }
 
 
 
</style>
<title>로그인</title>
</head>
<body>
	<div id="container">
		<h1>로그인</h1>
	<div class="err">${msg}</div>
		<form action="/login" method="post" id="logFrom">
	<div class="id">ID
		<input type="text" name="user_id" placeholder=" 아이디" value="${data.user_id}">
	</div>
	<div class="pw">PASSWORD
		<input type="password" name="user_pw" placeholder=" 비밀번호">
	</div>
	<div class="btn">
		<button type="submit" id="loginBtn">login</button>
		<button type="button" id="joinBtn" onclick="clkjoin()">join</button>
	</div>
	</form>
	</div>
	<script> 
	function clkjoin(){
		location.href = '/join'
	}
	</script>
</body>
</html>