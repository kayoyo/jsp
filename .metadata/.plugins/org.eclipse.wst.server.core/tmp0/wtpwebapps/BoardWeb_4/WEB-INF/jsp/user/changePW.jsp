<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
<!-- 이전 비밀번호 확인 --> 
	<c:if test="${isAuth == false || isAuth == null}">
		<div class="beforePW">
		<form action="/changePW" method="post">
		<input type="hidden" name="type" value="1">
		<label><input type="password" name="passWord" placeholder="현재 비밀번호"></label>
		<input type="submit" value="확인">
		</form>
		</div>
	</c:if>
<!-- 비밀번호 변경 --> 
	<c:if test="${isAuth == true}">
		<div class="afterPW">
		<form id= "changeFrm" action="/changePW" method="post" onsubmit="return chkChangePW()">
		<input type="hidden" name="type" value="2">
		<label><input type="password" name="passWord" placeholder="변경 비밀번호"></label>
		<label><input type="password" name="rePassWordCon" placeholder="변경 비밀번호 확인"></label>
		<input type="submit" value="확인">
		</form>
		</div>
	</c:if>
	<script>
		function chkChangePW() {
			if(changeFrm.passWord.value.length == 0){
				alert('비밀번호를 작성 해 주세요')
				return false
			} else if(changeFrm.passWord.value != changeFrm.rePassWordCon.value) {
				alert('비밀번호를 확인 해 주세요')
				return false
			}
		} 
	</script>
</body>
</html>