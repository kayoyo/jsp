<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.koreait.pjt.vo.BoardVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
* {
	margin: 0;
	padding: 0;
}

a {
	text-decoration: none;
	color: black;
}

.pageCnt {
	text-align: center;
	font-size: 20px;
	margin-top: 50px;
}

.container {
	width: 500px;
	margin: 0 auto;
}

.nowP {
	color: #ef7a85;
}
.user_name { margin-top: 30px; 
			margin-left: 20px;
	
}
.user_write {margin-top: 30px; 
			margin-bottom: 50px;
}
.logOutBtn {width: 70px; height: 27px; 
			margin-left:10px; 
			background-color: #faebd7; 
			color: #727A9A; border:none;
}
.writeBtn {width: 70px; height: 27px; 
		   background-color: #faebd7; 
		   color: #727A9A; border:none;
}
</style>

<title>리스트</title>
</head>
<body>
	<div class="container">

		<div class="user_name">
		<span>${loginUser.user_name}</span> 님 환영합니다! 
			<a href="/logout"><button class="logOutBtn">로그아웃</button></a>
		</div>
		<div class="user_write">
			<a href="/board/regmod"><button class="writeBtn">글쓰기</button></a>
		</div>
		<div>
		레코드수 : ${param.page == null ? 1 : param.page}
		<form id="selFrm" action="/board/list" method="get">
		<input type="hidden" name="page" value="${param.page == null ? 1 : param.page}">
		  
		<select name="record_cnt" onchange="changeRecord()">
			<c:forEach begin="10" end="30" step="10" var="item">
				<c:choose>
					<c:when test="${param.record_cnt == item || (param.record_cnt == null && item == 10)}">
						<option value="${item}">${item}개</option>
					</c:when>
					<c:otherwise>
						<option value="${item}">${item}개</option>	
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</select>
			</form>
		</div>
		<h1>리스트</h1>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>조회수</th>
				<th>좋아요</th>
				<th>작성자</th>
				<th>작성날짜</th>
			</tr>
			<c:forEach items="${list}" var="item"><!--forEach items는 리스트 형태-->
				<tr onclick="moveToDetail(${item.i_board})">
					<td>${item.i_board}</td>
					<td>${item.title}</td>
					<td>${item.hits}</td>
					<td>${item.like_cnt}</td>
					<td>${item.i_user}</td>
					<td>${item.r_dt}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="pageCnt">
			<c:forEach var="i" begin="1" end="${paginCnt}">
				<c:choose>
					<c:when test="${i == nowPage}">
						<span class="nowP">${i}</span>
					</c:when>
					<c:otherwise>
						<span><a href="/board/list?page=${i}">${i}</a></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
	<script>
	function moveToDetail(i_board){
		location.href = '/board/detail?i_board=' + i_board
	}
	function changeRecord(){
		selFrm.submit()
	} 
	</script>
</body>
</html>