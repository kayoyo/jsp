<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import = "java.sql.*" %>

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
	Connection con = null;
	PreparedStatement ps = null;
	
	
	String strI_board = request.getParameter("i_board");//request.getParameter : key값을 입력하면 value값을 받는 메소드
	int i_board = Integer.parseInt(strI_board);
	
	String sql = "delete from t_board where i_board = ?"; 
	
	
	int result = -1; //에러부분을 확인하기 위해 -1을 초기값으로 지정
	
	try {
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board); //첫번째 물음표에 i_board의 값을 넣어줌(sql의 물음표는 1가 위치)

		result = ps.executeUpdate(); // executeUpdate() 리턴값은 0,1 : 0일 때는 아무런 값이 없을 때 , 1은 INSERT, DELETE, UPDATE가 성공했을 때
	
	} catch(Exception e){
		e.printStackTrace(); 

	} finally { 
		if(ps != null){ try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} } 
		}
	
		System.out.println("result : " + result);
		

	 switch(result){
	      case -1 : 
	    %> <script>alert('삭제를 할 수 없습니다.')</script>	
	  	 <% response.sendRedirect("/jsp/boardDetail.jsp?err=-1&i_board=" + i_board);
	 	  break;
	 	  case  0 :
	 	 %> <script>alert('삭제를 할 수 없습니다.')</script> 
	      <% response.sendRedirect("/jsp/boardDetail.jsp?err=0&i_board=" + i_board);
	      break;
		  case 1 : //성공했을 때
		  %> <script>alert('삭제 완료 되었습니다.')</script> 
		  <% response.sendRedirect("/jsp/Boardlist.jsp");
	      break; 
	     
	      } 
	
%> 
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제페이지</title>
</head>
<body>
	<div class = "back">
	<a href="/jsp/Boardlist.jsp">리스트로 가기</a>
	</div>
</body>
</html>