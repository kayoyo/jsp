<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
<div>
	<form id="frm" class="frm" action="/user/joinProc" method="post">
		<div>
			<input type="text" name="user_id" placeholder="아이디">
		</div>
		<div>
			<input type="password" name="user_pw" placeholder="비밀번호">
		</div>
		<div>
			<input type="password" name="user_pw2" placeholder="비밀번호 확인">
		</div>
		<div>
			<input type="text" name="nm" placeholder="이름">
		</div>
		<div>
			<input type="submit" value="회원가입">
		</div>
	</form>
		<a href="/user/login"><button id="logBtn">로그인</button></a>
</div>
</div>