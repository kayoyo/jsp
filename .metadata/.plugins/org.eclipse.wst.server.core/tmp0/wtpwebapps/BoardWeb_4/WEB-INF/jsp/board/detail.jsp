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
 a {text-decoration:none; color: black;}
.material-icons {color: red;}

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
             <span class="material-icons">thumb_up_alt</span></c:if>
			 <c:if test = "${data.likey == 1}">
			 <span class="material-icons">thumb_down_alt</span></c:if>
</div>
<div>좋아요 수 : ${data.like_cnt}</div>
<div><form id="cmtFrm" action="/board/cmt" method="post">
	<input type="hidden" name="i_cmt" value="0" class=i_cmtNum> <!-- 서버로 값을 보냄 0 또는 1(스위치 구문 처리) --> 
	<input type="hidden" name="i_board" value="${data.i_board}">
	<div>
	<input type="text" name="cmt" placeholder="댓글내용" class=cmtBox> <!-- 수정할 값을 댓글창에 띄우는 용도-->
	<input type="submit" value="등록">
	</div>
	</form>
</div>
<div>댓글 리스트</div>
<table>
<tr>
<th>No</th>
<th>내용</th>
<th>글쓴이</th>
<th>등록날짜</th>
<th>비고</th>
</tr>
<c:forEach items="${cmtList}" var="item">
<tr>
<td>${item.i_cmt}</td>
<td>${item.cmt}</td>
<td>${item.user_nm}</td>
<td>${item.r_dt}</td>
<td>
<c:if test = "${loginUser.i_user == item.i_user}">
	<a href="/board/cmt?i_cmt=${item.i_cmt}&i_board=${data.i_board}">삭제</a>
	<form id="cmtFrm" action="/board/cmt" method="post">
	<a href="#" onclick="upBtn('${item.cmt}', '${item.i_cmt}')">수정</a>
	</form>
</c:if> 
</td>
</tr>
</c:forEach>
</table>
<script>
		function submitDel() {
			delFrm.submit()
		}
		function like(likey) {		
			location.href='/like?i_board=${data.i_board}&likey=${data.likey}';	
		}
		function upBtn(cmt1, cmt2){
			let cmt1 = document.querySelector(".i_cmtNum")
			i_cmtNum.setAttribute("value",cmt1);
			
			let cmt2 = document.querySelector(".cmtBox")
			cmtBox.setAttribute("value",cmt2);			 
		}
	</script>
</body>
</html>