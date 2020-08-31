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
	width: 1000px;
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
		<a href="/profile">프로필</a>
			<a href="/logout"><button class="logOutBtn">로그아웃</button></a>
		</div>
		<div class="user_write">
			<a href="/board/regmod"><button class="writeBtn">글쓰기</button></a>
		</div>
		<div>
		<form id="selFrm" action="/board/list" method="get">
		<input type="hidden" name="page" value="1">
		레코드수 :
		<select name="record_cnt" onchange="changeRecord()">
			<c:forEach begin="10" end="30" step="10" var="reItem">
				<c:choose>
					<c:when test="${recordCnt == reItem}">
						<option value="${reItem}" selected>${reItem}개</option>
					</c:when>
					<c:otherwise>
						<option value="${reItem}">${reItem}개</option>	
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
			<c:forEach items="${list}" var="liItem"><!--forEach items는 리스트 형태-->
				<tr onclick="moveToDetail(${liItem.i_board})">
					<td>${liItem.i_board}</td>
					<td>${liItem.title}</td>
					<td>${liItem.hits}</td>
					<td>${liItem.like_cnt}</td>
					<td>${liItem.i_user}</td>
					<td>${liItem.r_dt}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="search">
			<form action="/board/list">
				<input type="search" name="searchText" value="${param.searchText}">
				<input type="submit" value="검색">
				<input type="hidden" name="record_cnt" value="${recordCnt}">				
			</form>
		</div>
		<div class="pageCnt">
			<c:forEach var="pageItem" begin="1" end="${pagingCnt}">
				<c:choose>
					<c:when test="${pageItem == nowPage}">
						<span class="nowP">${pageItem}</span>
					</c:when>
					<c:otherwise>
						<span><a href="/board/list?page=${pageItem}&record_cnt=${recordCnt}&searchText=${param.searchText}">${pageItem}</a></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
	</div>
	<script>
	function moveToDetail(i_board){
		location.href = '/board/detail?page=${page}&record_cnt=${param.record_cnt}&searchText=${param.searchText}&i_board=' + i_board
						
	}
	function changeRecord(){
		selFrm.submit()
	} 
	</script>
</body>
</html>