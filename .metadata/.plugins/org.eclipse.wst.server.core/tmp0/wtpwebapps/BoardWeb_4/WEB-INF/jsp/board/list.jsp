<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import = "java.util.*" %>
<%@ page import = "com.koreait.pjt.vo.BoardVO" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
 a {text-decoration:none; color: black;}
</style>
<title>리스트</title>
</head>
<body>
<button><a href="/logout">로그아웃</a></button><br><br>
<div>${loginUser.user_name}님 환영합니다.</div>
<div>
<button><a href="/board/regmod">글쓰기</a></button>
</div>
<h1>리스트</h1>
<table>
<tr>
<th>번호</th>
<th>제목</th>
<th>조회수</th>
<th>작성자</th>
<th>작성날짜</th>
</tr>
<c:forEach items="${list}" var="item">
<tr onclick="moveToDetail(${item.i_board})">
<td>${item.i_board}</td>
<td>${item.title}</td>
<td>${item.hits}</td>
<td>${item.i_user}</td>
<td>${item.r_dt}</td>
</tr>
</c:forEach>
</table>
<script>
	function moveToDetail(i_board){
		location.href = '/board/detail?i_board=' + i_board
	}
	</script>
</body>
</html>