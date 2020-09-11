<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/res/css/common.css">
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500&display=swap" rel="stylesheet">
<title>${title}</title>
</head>
<body>
	<div id="container">
	<header>
		<div class="headerLeft">
			<div class="containerPImg">
				<c:choose>
				<c:when test="${loginUser.profile_img != null}"> <!-- /res/를 적은 이유는 containerSer에서 "/"를 걸러 낼 수 있기 때문 -->
					<img class ="pImg" src="/res/img/user/${loginUser.i_user}/${loginUser.profile_img}" >
				</c:when>
				<c:otherwise>
					<img class ="pImg" src="/res/img/default_profile.jpg">
				</c:otherwise>
	 			</c:choose>
		</div>
			<div class="headerNm"><span>${loginUser.nm}</span>님 환영합니다.</div> 
			<div class="headerLogOut"><a href="/user/logout">로그아웃</a></div>
		</div>
		<div class="headerRight">
			<a class="area" href="/restaurant/restmap">지도</a>
			<a class="reg" href="/restaurant/restReg">등록</a>
			<a class="zzim" href="/user/restFavolate">찜</a>
		</div>
	</header>
	<section>
		<jsp:include page="/WEB-INF/view/${view}.jsp"></jsp:include>
	</section>
	<footer>
		<span class="companyInfo">회사정보</span>
	</footer>
	</div>
</body>
</html>