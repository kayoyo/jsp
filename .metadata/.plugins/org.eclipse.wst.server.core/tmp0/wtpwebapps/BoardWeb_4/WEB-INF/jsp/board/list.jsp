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

*:focus{
	outline:none;
}

a {
	text-decoration: none;
	color : #45595a;

}

.pageCnt {
	text-align: center;
	font-size: 25px;
	margin-top: 50px;
	
}

.container {
	width: 1000px;
	margin: 0 auto;
}

.nowP {
	color: pink;
}
.user_name { margin-top: 30px; 
			margin-left: 20px;
			color : #727A9A;
}
div.user_write { display: flex; 
				 justify-content: flex-end;
				 margin-top: 30px;
				 margin-right: 15px;
}
.logOutBtn {width: 70px; height: 27px; 
			margin-left:10px; 
			background-color: #faebd7; 
			color: #45595a; border:none;
}
.writeBtn {width: 70px; height: 27px; 
		   background-color: #faebd7; 
		   color: #45595a; border:none;
		   
		   
}
.proBtn {width: 70px; height: 27px; 
			margin-left:10px; 
			background-color: #faebd7; 
			color: #45595a; border:none;
}

.containerPImg {
		display: inline-block;	
		width: 30px;
		height: 30px;
	    border-radius: 50%;
	    overflow: hidden;
	}
	
	.pImg {
	
		 object-fit: cover;
		  max-width:100%;
	}
	
#iiyo {color : pink; }

.highlight {color: red;}

.user {
		font-size : 1.5em; 
		font-weight:bold;
		color: #45595a;  
	}
.title {
		font-size : 3em; 
		font-weight:bold;
		text-align: center;
		margin : 30px;
		color : #727A9A;
		}
#selFrm {
		display : flex;
		justify-content: flex-end;
		margin : 20px;
	}

form#searchCss { width : 500px;
				display : flex;
				justify-content: end;
				margin-top: 40px;
				margin-left: 10px;
}
select#searchCss1{ margin-right: 10px; height: 30px; }

input#searchCss2{ margin-right: 10px;}

input#searchCss3{ margin-left: 0; 
				  width: 50px;
				  background-color: #faebd7; 
		          color: #45595a; border:none
		          }
select.recodeCnt { height: 30px; }
	
table th, td {line-height : 60px;
		 		  border-collapse: collapse;
		 		  border-bottom: 1px solid #45595a;
				  border-top: 1px solid  #45595a;
		 		  			
}

table th {border-bottom: 1px solid #45595a;} 

table { text-align: center; 
	    color : #727A9A; 
	    width : 1000px;
	    border-bottom: 1px solid #45595a;
		border-top: 1px solid  #45595a;
		border-collapse: collapse; 
}
#likeListContainer {
		display: none;			
		padding: 10px;		
		border: 1px solid #bdc3c7;
		position: absolute;
		left: 0px;
		top: 30px;
		width: 130px;
		height: 200px;
		overflow-y: auto;
		background-color: white !important;
		
	}		
	
	
 		
	.profile {
		background-color: white !important;
		display: inline-block;	
		width: 25px;
		height: 25px;
	    border-radius: 50%;
	    overflow: hidden;
	}		
	
	.likeItemContainer {
		display: flex;
		width: 100%;
	}
	
	.likeItemContainer .nm {
		background-color: white !important;
		margin-left: 7px;
		font-size: 0.7em;
		display: flex;
		align-items: center;
	}



</style>

<title>리스트</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
</head>
<body>
	<div class="container">

		<div class="user_name">
		<span class="user">${loginUser.user_name}</span> 님 환영합니다! 
		<a href="/profile"><button class="proBtn">프로필</button></a>
			<a href="/logout"><button class="logOutBtn">로그아웃</button></a>
		</div>
		<div class="user_write">
			<a href="/board/regmod"><button class="writeBtn">글쓰기</button></a>
		</div>
		<div class="title">List</div>
		<form id="selFrm" action="/board/list" method="get">
		<input type="hidden" name="page" value="1">
		<select name="record_cnt" onchange="changeRecord()" class="recodeCnt">
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
		<table>
			<tr>
				<th width=50>번호</th>
				<th width=300>제목</th>
				<th width=80>조회수</th>
				<th width=50>좋아요</th>
				<th width=50></th>
				<th width=150>작성자</th>
				<th width=200>작성날짜</th>
			</tr>
			<!--forEach items는 리스트 형태-->
			<c:forEach items="${list}" var="liItem">
				<tr>
					<td onclick="moveToDetail(${liItem.i_board})">${liItem.i_board}</td>
					<td onclick="moveToDetail(${liItem.i_board})">${liItem.title}</td>
					<td>${liItem.hits}</td>
					<td>
						<span onclick="getLikeList(${liItem.i_board}, ${liItem.like_cnt}, this)">${liItem.like_cnt}</span>
					</td>
					<td>
			 			<c:if test = "${liItem.like_cnt == 0}">
             				<span class="material-icons" id="yadayo">thumb_up_alt</span></c:if>
			 			<c:if test = "${liItem.like_cnt >= 1}">
			 				<span class="material-icons" id="iiyo">thumb_up_alt</span></c:if>
					</td>
					<td>${liItem.user_nm}
					<div class="containerPImg">
					<c:choose>
						<c:when test="${liItem.profile_img != null}">
							<img class="pImg" src="/img/user/${liItem.i_user}/${liItem.profile_img}">
						</c:when>
						<c:otherwise>
							<img class="pImg" src="/img/default_profile.jpg">
						</c:otherwise>
						</c:choose>
						</div>
						</td>
						<td>${liItem.r_dt}</td>
				</tr>
			</c:forEach>
		</table>
			<form action="/board/list" id="searchCss">
				<select name="searchType" id="searchCss1">
					<option value="a" ${searchType == 'a' ? 'selected' : ''}>제목</option>
					<option value="b" ${searchType == 'b' ? 'selected' : ''}>내용</option>
					<option value="c" ${searchType == 'c' ? 'selected' : ''}>제목 + 내용</option>
				</select>
				<input type="search" name="searchText" value="${param.searchText}" id="searchCss2">
				<input type="submit" value="검색" id="searchCss3">
				<input type="hidden" name="record_cnt" value="${recordCnt}">				
			</form>
		<div class="pageCnt">
			<c:forEach var="pItem" begin="1" end="${pagingCnt}">
				<c:choose>
					<c:when test="${pItem == page}">
						<span class="nowP">${pItem}</span>
					</c:when>
					<c:otherwise>
						<span class="page"><a href="/board/list?page=${pItem}&record_cnt=${recordCnt}&searchText=${param.searchText}&searchType=${searchType}">${pItem}</a></span>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</div>
		<div id="likeListContainer">
		</div>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
	<script>
	
	let beforeI_board = 0//전역변수(최초의 i_board값) 
	
	function getLikeList(i_board, cnt, span){ //현재 글의 i_board값, like_cnt, span(this)
			
		if(cnt == 0) { return } //like_cnt값이 0이면 함수 종료(좋아요가 없기 때문에 좋아요 한 사람들의 목록을 안 보여 줘도 됨)
		
		if(beforeI_board == i_board) { //같다면 숨김
			likeListContainer.style.display = 'none'
			return
			
		} else if(beforeI_board != i_board) { //같지 않다면 beforeI_board에 현재 i_board 값을 넣어 노출
			beforeI_board = i_board
			likeListContainer.style.display = 'unset'
		}
		
		const locationX = window.scrollX + span.getBoundingClientRect().left //like_cnt를 감싸고 있는 span의 위치에서 left의 x값
		const locationY = window.scrollY + span.getBoundingClientRect().top + 30 //like_cnt를 감싸고 있는 span의 위치에서 top + 30의 y값
		
		likeListContainer.style.left = `\${locationX}px` //좋아요 목록을 나타내는 div에 locationX 위치값
		likeListContainer.style.top = `\${locationY}px`//좋아요 목록을 나타내는 div에 locationY 위치값
		
		likeListContainer.style.opacity = 1
		likeListContainer.innerHTML = "" //초기화
		
		axios.get('/board/like', { //get방식으로 보내기 때문에 쿼리 스트링으로 받을 수 있다.
			
			params : {
				'i_board': i_board //key값과 변수명이 같을 때는 하나만 적어도 됨(i_board)				
			}
		
		}).then(function(res){ //axios 통신의 결과값을 res로 받음
			if(res.data.length > 0) { //배열로 값이 넘어옴
				for(let i=0; i<res.data.length; i++){
					const result = makeLikeUser(res.data[i]) //makeLikeUser의 형식으로 값을 result에 넣어줌
					likeListContainer.innerHTML += result
				}
			}
		})
	}
	function makeLikeUser(one) {
		const img = one.profile_img == null ? 
				'<img class="pImg" src="/img/default_profile.jpg">'
				: 
				`<img class="pImg" src="/img/user/\${one.i_user}/\${one.profile_img}">`
		
		const ele = `<div class="likeItemContainer"> 
				     <div class="profileContainer"> <div class="profile"> \${img} </div> </div>
			         <div class="nm"> \${one.nm} </div> </div>`			
		return ele
	}
	
	function moveToDetail(i_board){
		location.href = '/board/detail?page=${page}&record_cnt=${param.record_cnt}&searchType=${searchType}&searchText=${param.searchText}&i_board=' + i_board
						
	}
	
	function changeRecord(){
		selFrm.submit()
	} 
	
	</script>
</body>
</html>