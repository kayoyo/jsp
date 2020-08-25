<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 페이지</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
<style>
.material-icons {}
</style>
</head>
<body>
<div>
<!-- 삭제 버튼을 누르면 post방식으로 action의 주소로 날라감 -->
	<a href="/board/list">리스트</a>
	<c:if test = "${loginUser.i_user == data.i_user}">
		<a href="/board/regmod?i_board=${data.i_board}">수정</a>
		<form id="delFrm" action="/board/del" method="post">
		<input type="hidden" name="i_board" value="${data.i_board}">
		<a href="#" onclick="submitDel()">삭제</a> 
		</form>			
	</c:if> 
</div>
<div>상세페이지</div>
<div>글번호 : ${data.i_board}</div>
<div>제목 : ${data.title}</div>
<div>내용 : ${data.ctnt}</div>
<div>작성자 : ${data.user_nm}</div>
<div>조회수 : ${data.hits}</div>
<div>작성일자 : ${data.r_dt}</div>	
<div onclick="like(${data.likey})"> 좋아요 : 
			 <c:if test = "${data.likey == 0}">
             <span class="material-icons">sentiment_very_dissatisfied</span></c:if>
			 <c:if test = "${data.likey == 1}">
			 <span class="material-icons">sentiment_very_satisfied</span></c:if>
</div>
<div>좋아요 수 : ${data.like_cnt}</div>
<script>
		function submitDel() {
			delFrm.submit()
		}
		function like(likey) {		
			location.href='/like?i_board=${data.i_board}&likey=${data.likey}';
			
		}
	</script>
</body>
</html>