<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>profile</title>
<style>
	* { margin:0; padding: 0; }
	*:focus{ outline:none; }
	.container {width: 500px; margin: 0 auto;}
	h1 {margin: 20px; color: #4b4b4b;}
</style>
</head>
<body>
	<div class="container">
	<h1>MY PAGE</h1>
	<div class="profile">
	<div class="pwBtn">
	<a href="/changePW"><button>비밀번호 변경</button></a>
	</div>
	<c:choose>
		<c:when test="${data.profile_img != null}">
			<img src="/img/user/${loginUser.i_user}/${data.profile_img}" width="200px" height="200px">
		</c:when>
		<c:otherwise> 
			<img src="/img/default_profile.jpg" width="200px" height="200px">
		</c:otherwise>
	</c:choose>
	<div>	
		<form action="/profile" method="post" enctype="multipart/form-data">
		<div>
			<label>프로필 이미지 : </label>
			<input type="file"  name="profile_img" accept="image/x-png,image/gif,image/jpeg" class="fileInput"> 
			<input type="submit" value="업로드" class="subBtn">
		</div>
		</form>
	</div>	
	<div>ID : ${data.user_id}</div>
	<div>NAME : ${data.user_name}</div>
	<div>E-MAIL : ${data.user_email}</div>
	<div>DATE : ${data.r_dt}</div>
	</div>
	</div>
	<script>
		var proc = '${param.proc}'
		switch(proc) {
		case '1':
			alert('비밀번호를 변경하였습니다.')
			break
		}
	</script>
</body>
</html>