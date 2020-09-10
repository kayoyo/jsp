<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
<div>
	<form id="frm" class="frm" action="/user/joinProc" method="post">
		<div id="idError" class="msg"></div>
		<div>
			<input type="text" name="user_id" placeholder="아이디">
			<button type = "button" onclick="chkId()">아이디 중복체크</button>
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
		<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
		<script>
			function chkId(){
				const user_id = frm.user_id.value //frm form 안의 name="user_id" 의 value값 
				axios.get('/user/ajaxIdChk', { 
					params: {
						'user_id' : user_id //params : key값 >> 쿼리스트링으로 날려줌(http://localhost:8090/user/ajaxIdChk?user_id='')
					}					
				}).then(function(res){ //then 실행안된다면, ajaxIdChk의 주소값을 가져오지 못함 
					console.log(res)
					if(res.data.result == 2) { //아이디 없음
						idError.innerText = '사용할 수 있는 아이디입니다'	
					} else if(res.data.result == 3) //아이디 중복됨
						idError.innerText = '이미 사용중입니다'
				})
			}		
		</script>
</div>