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
	//request.setCharacterEncoding("UTF-8");
	
	String strI_board = request.getParameter("i_board"); //key 값인 i_board를 가져오는 메소드 request.getParameter 
	String title = request.getParameter("title"); //request에 담겨 있는건 HttpServletRequest주소값
	String ctnt = request.getParameter("ctnt");
	String strI_student = request.getParameter("i_student");//DB에서 값을 가져올 때 String으로 가져오기 때문에 int형으로 바꿔 줄 것 
	
	if("".equals(title) || "".equals(ctnt) || "".equals(strI_student)){
		response.sendRedirect("/jsp/BoardWrite.jsp?err=10");
		return;
	}
	
	int i_student = Integer.parseInt(strI_student);

	int result= -1;	
	Connection con = null;
	PreparedStatement ps = null;
	//select문이 아니기 때문에 resultSet 필요없음
	
	String sql = "insert into t_board(i_board, title, ctnt, i_student)" +
				  "select nvl(max(i_board),0) + 1, ?, ?, ?" + "from t_board";
	//i_board의 최대 숫자값이 null이면 0을 넣어주고 그 i_board에 1을 더해줌(게시판의 순서를 만들어줌) 

	
	try {
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setString(1, title);
		ps.setString(2, ctnt);
		ps.setInt(3, i_student);
		
		result = ps.executeUpdate(); //영향을 미치는 데이터베이스의 레코드의 값 (1)
	
		
	} catch(Exception e){
		e.printStackTrace(); 

	} finally { 
		if(ps != null){ try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} } 
		}
	
	int err = 0;
	
	switch(result){
	case 1:
		response.sendRedirect("/jsp/Boardlist.jsp");
		return; //메소드를 종료 : response.sendRedirect를 중복으로 실행 할 수 없음
	case 0:
		err = 10;
		break; //switch문을 종료
	case -1:
		err = 20;
		break;
	}
	response.sendRedirect("/jsp/BoardWrite.jsp?err=" + err);
	//return;
%>



