<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "com.koreait.web.BoardVO" %>

<%!  
    Connection getCon() throws Exception{
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String username = "hr";
	String password = "koreait2020";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url, username, password);
	System.out.println("접속 성공"); 
	return con;
}
%>

<%	
	BoardVO vo = new BoardVO();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
%>	
<% 	
	//String nm="";
	String strI_board = request.getParameter("i_board");//boardList.jsp에서 a태그로 주소값을 받았음(쿼리스트링)
	//PK의 값은 레코드를 구분하기 위한 유일성
	
	if(strI_board == null) { //null이 아니라면 alert가 실행되지 않고 아래가 실행됨
%>   <script>
      alert('잘못된 접근 입니다');
      location.href='/jsp/Boardlist.jsp';
      </script>
<% 	
	 return;	
	}
	
	int i_board = Integer.parseInt(strI_board);
	//String sql = "SELECT title, ctnt, i_student FROM t_board WHERE i_board=" + strI_board;
	//String sql = "SELECT B.nm, A.title, A.ctnt, A.i_student FROM t_board A left join t_student B on A.i_student = B.i_student WHERE i_board=" + strI_board;
	String sql = "SELECT title, ctnt, i_student FROM t_board WHERE i_board= ? "; 

	
	try {
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board); //첫번째의 값이 String sql의 ?로 자리로 대입된다.
		//ps.setString(1, strI_board);
		
		rs = ps.executeQuery(); //문장 완성하고 executeQuery() 반드시 실행할 것
		
		
		//if(rs.next()){
			//String title = rs.getNString("title");
			//String ctnt = rs.getNString("ctnt");
			//int i_student = rs.getInt("i_student");
			
			//vo.setTitle(title);
			//vo.setCtnt(ctnt);
			//vo.setI_student(i_student); }
		
		
		while(rs.next()){
			
			//nm = rs.getNString("nm");//
			//String nm = rs.getNString("nm");
			String title = rs.getNString("title");
			String ctnt = rs.getNString("ctnt");
			int i_student = rs.getInt("i_student");
			
		
			//vo.setNm(nm);//
			vo.setTitle(title);
			vo.setCtnt(ctnt);
			vo.setI_student(i_student);			
		}
	} catch(Exception e){
		e.printStackTrace(); 
	} finally {
		if(rs != null){ try{ rs.close(); } catch(Exception e) {} } 
		if(ps != null){ try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} } 
		} 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세페이지</title>
<style>

.title {margin: 30px; text-align: center; 
        font-weight: bold; font-size: 20px;}
</style>
</head>
<body>
	<div class = "container">
	<div class="title">상세 페이지<%=strI_board%></div>
	<div class="back">
	<a href="/jsp/Boardlist.jsp">리스트로 가기</a>
	</div>
	<div class=delete>
	<a href="#" onclick="procDel(<%=i_board %>)">삭제</a>
	</div>
	<div class=update>
	<a href="/jsp/boardMod.jsp?i_board=<%=i_board %>">수정</a>
	</div>
	<!--<div><%=vo.getNm()%></div>--> 
	<div>제목 : <%=vo.getTitle()%></div>
	<div>내용 : <%=vo.getCtnt()%></div>
	<div>작성자 : <%=vo.getI_student()%><div>
	
	<script>
	function procDel(i_board){
		//alert('i_board : ' + i_board)
		//var result = confirm('삭제하시겠습니까?')
		//if(result){
		//location.href = '/jsp/boardDel.jsp?i_board=' + i_board }
		if(confirm('삭제하시겠습니까?')){
			location.href = '/jsp/boardDel.jsp?i_board=' + i_board;
		}
	}
	</script>
	</div>
</body>
</html>