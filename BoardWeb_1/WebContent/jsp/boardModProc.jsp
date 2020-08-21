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
	
	String strI_board = request.getParameter("i_board"); 
	String title = request.getParameter("title"); 
	String ctnt = request.getParameter("ctnt");
	String strI_student = request.getParameter("i_student");
	
	int i_student = Integer.parseInt(strI_student);
	int i_board = Integer.parseInt(strI_board);
	
	
	String sql = "update t_board set title=?, ctnt=?, i_student=? where i_board=?";
	
	
	try {
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setNString(1, title);
		ps.setNString(2, ctnt);
		ps.setInt(3, i_student);
		ps.setInt(4, i_board);
		
		ps.executeUpdate();
				
	
	} catch(Exception e){
		e.printStackTrace(); 

	} finally { 	
		if(ps != null){ try{ ps.close(); } catch(Exception e) {} } 
		if(con != null){ try{ con.close(); } catch(Exception e) {} } 
		}
	
	response.sendRedirect("/jsp/boardDetail.jsp?i_board=" + strI_board);
%>	

  
