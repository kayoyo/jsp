<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="sectionContainerCenter">
<div>
	<form id="frm" class="frm" action="/user/joinProc" method="post">
		<div id="idError" class="msg"></div>
		<div>
			<input type="text" name="user_id" placeholder="ID">
			<button type = "button" onclick="chkId()">Duplicate ID Check</button><span id="resultGetLatLng"></span>
		</div>
		<div>
			<input type="password" name="user_pw" placeholder="PASSWORD">
		</div>
		<div>
			<input type="password" name="user_pw2" placeholder="Re-enter password">
		</div>
		<div>
			<input type="text" name="nm" placeholder="Your Name">
		</div>
		<div>
			<input type="submit" value="Create your account">
		</div>
	</form>
		<a href="/user/login"><button id="logBtn">Sign-In</button></a>
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
						resultGetLatLng.innerText = 'V'
						alert('사용할 수 있는 아이디입니다')	
					} else if(res.data.result == 3) //아이디 중복됨
						alert('이미 사용중입니다')
				})
			}		
		</script>
</div>