<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* { margin:0; padding: 0; }
 a {text-decoration:none; color: black;}
 .err {color: red;}
 
</style>
<title>로그인</title>
</head>
<body>
	<div id="container">
	<h1>로그인</h1>
	<div class="err">${msg}</div>
	<form action="/login" method="post" id="logFrom">
	<div class="id">아이디
	<input type="text" name="user_id" placeholder="아이디" value="${data.user_id}"></div>
	<div class="pw">패스워드
	<input type="password" name="user_pw" placeholder="비밀번호"></div>
	<input type="submit" value="로그인">
	<button><a href="/join">가입하기</a></button>
	</form>
	</div>
</body>
</html>