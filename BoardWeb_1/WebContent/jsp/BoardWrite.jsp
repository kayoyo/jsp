<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 사용자로 부터 값을 받아와서 BoardWriteProc로 값을 보내줌 -->
<% 
	String msg = "";
	String err = request.getParameter("err"); //err의 값은 null
	if(err != null){
		switch(err){
		case "10" : 
			msg = "등록 할 수 없습니다.";
			break;
		case "20" : 
			msg = "DB 에러 발생";
			break;	
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<style>
	#msg {color : red;}
	
	
</style>
<body>
<div id="msg"><%=msg%></div>
<div>
	<form id="frm" action="/jsp/BoardWriteProc.jsp" method="post" onsubmit="return chk()"> 
	<!-- on으로 시작하는 것은 이벤트 함수, onsubmit="return false" 하면 절대로 서버로 보낼 수 없다. --> 
	<!-- method의 get방식은 Query String 노출되고 보안은 취약하나 속도는 빠름, post는 그 반대 -->
		<div>
			<label>제목 : <input type="text" name="title"></label> <!-- 서버에게 값을 보낼 때 name은 Query String key값 -->
		</div> 
		<div>
			<label>내용 : <textarea name="ctnt"></textarea></label>
		</div>
		<div>
			<label>작성자 : <input type="text" name="i_student"></label>
		</div>
		<div>
			<button type="submit">글등록</button>
		</div>
		<div>
			<button type="reset" onclick="procWrt()">돌아가기</button>
		</div>
	</form>
</div>
	<script>
	function procWrt(){
		location.href = '/jsp/Boardlist.jsp'		    	    	
	}
	
	//function eleValid(ele, nm){
		//if(ele.value.length == 0){
			//alert(nm + '을(를) 입력 해 주세요')
			//ele.focus()
			//return true
		//}	
	//}
	
	function chk(){ //제목, 내용, 작성자의 값이 등록되지 않으면 submit을 하지 않음 
		//console.log(`title : \${frm.title.value}`)
		if(frm.title.value == ''){
			alert('제목을 입력해 주세요!')
			frm.title.focus()
			return false
		} else if(frm.ctnt.value == ''){
			alert('내용을 입력해 주세요!')
			frm.ctnt.focus()
			return false	
		} else if(frm.i_student.value.length === 0){
			alert('내용을 입력해 주세요!')
			frm.i_student.focus()
			return false	
	
		}
	}
		//if(eleValid(frm.title, '제목')){
			//return false	
		//} else if(eleValid(frm.ctnt, '내용')){
			//return false
		//} else if(eleValid(frm.i_student, '작성자')){
			//return false }
		</script>
</body>
</html>