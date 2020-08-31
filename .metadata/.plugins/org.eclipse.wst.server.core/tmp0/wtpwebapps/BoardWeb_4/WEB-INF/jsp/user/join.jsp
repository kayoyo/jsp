<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	#container {width:700px; margin: 0 auto; background-color: #e7d6c9; }
	#container > h1 
	.err {color: red;}
	
</style>
</head>
<body>
	<div id="container">
	<h1>회원가입</h1>
	<div class="err">${msg}</div>
	<form id="frm" action="/join" method="post" onsubmit="return check()">
	<div id="uID">
		<label>아이디 
			<input type="text" name="user_id" placeholder=" 아이디" required value="${data.user_id}">
		</label>
	</div>
	<div id="uPw">
		<label>패스워드
			<input type="password" name="user_pw" placeholder=" 패스워드" required>
		</label>
	</div>
	<div id="uPwE">
		<label>패스워드 확인
			<input type="password" name="user_pwEtc" placeholder=" 확인 비밀번호">
		</label>
	</div>
	<div id="uNm">
		<label>이름
			<input type="text" name="user_name" placeholder=" 이름" required value="${data.user_name}">
		</label>
	</div>
	<div id="uEm">
		<label>이메일
			<input type="email" name="user_email" placeholder=" 이메일" value="${data.user_email}">
		</label>
	</div>
	<div id="btn">
			<input type="submit" value="가입하기">
	</div>
	</form>
	</div>
	<script>
	function check(){
		if(frm.user_id.value.length < 5){
			alert('아이디는 5글자 이상이 되어야 합니다.')
			frm.user_id.focus()
			return false
		} else if(frm.user_pw.value.length < 5){
			alert('비밀번호는 5글자 이상이 되어야 합니다.')
			frm.user_pw.focus()
			return false
		} else if(frm.user_pw.value != frm.user_pwEtc.value){
			alert('확인 비밀번호를 다시 입력 해 주세요.')
			frm.user_pw.focus()
			return false
		} else if(frm.user_name.value.length > 0){
			const korean = /[^가-힣]/;
			const result = korean.test(frm.user_name.value)
			if(result){
				alert('이름은 한글만 입력 해 주세요.')
				frm.user_name.focus()
				return false
			}		
		} if(frm.user_email.value.length > 0){
			const email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i		
			if(!email.test(frm.user_email.value)){
				alert('이메일을 확인 해 주세요.')
				frm.user_email.focus()
				return false
				}
			}
			
		}
			
	</script>
</body>
</html>