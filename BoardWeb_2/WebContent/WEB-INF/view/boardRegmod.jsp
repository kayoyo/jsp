<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${data == null ? '글등록' : '글수정'}</title>
<style>
.err { color : red};
</style>
</head>
<body>
<div>${data == null ? '글등록' : '글수정'}</div>
<div class="err">${msg}</div>
<form id = "frm" action="/${data == null ? 'boardWrite' : 'boardMod'}" method="post" onsubmit="return chk()"> 	
	<input type="hidden" name="i_board" value="${data.i_board}">
	<div><label>제목 : <input type="text" name="title" value="${data.title}"></label> <!-- 서버에게 값을 보낼 때 name은 Query String key값 -->
	</div> 
	<div><label>내용 : <textarea name="ctnt">${data.ctnt}</textarea></label>
	</div>
	<div><label>작성자 : <input type="text" name="i_student" value="${data.i_student}" ${data == null ? "" : 'readonly'}></label>
	</div>
	<div><button type="submit">${data == null ? '글등록' : '글수정'}</button></div>
	</form>
</body>
</html>