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

	String strI_board = request.getParameter("i_board"); //디테일 페이지에서 수정 버튼을 눌렀을 때 받은 쿼리스트링의 값 
	int i_board = Integer.parseInt(strI_board);
	
	String sql = "SELECT title, ctnt, i_student FROM t_board WHERE i_board= ? ";
 	
	try {
		con = getCon();
		ps = con.prepareStatement(sql);
		ps.setInt(1, i_board);
		
		rs = ps.executeQuery();
		
	while(rs.next()){
			
		String title = rs.getNString("title");
		String ctnt = rs.getNString("ctnt");
		int i_student = rs.getInt("i_student");
			
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
<title>글 수정</title>
</head>
<body>
<div>
	<form id="frm" action="/jsp/boardModProc.jsp?i_board=<%=i_board%>" method="post" onsubmit="return chk()">
	<!--  <input type="hidden" name="i_board" value="<%=i_board%>">-->
		<div>
			<label>제목 : <input type="text" name="title" value=<%=vo.getTitle()%>></label> 
		</div> 
		<div>
			<label>내용 : <textarea name="ctnt" ><%=vo.getCtnt()%></textarea></label>
		</div>
		<div>
			<label>작성자 : <input type="text" name="i_student" value=<%=vo.getI_student()%>></label>
		</div>
		<div>
			<button type="submit">수정완료</button>
		</div>
		
	</form>
	<script>
	
	function chk(){ 
		if(frm.title.value == ''){
			alert('제목을 입력해 주세요!')
			frm.title.focus()
			return false
		} else if(frm.ctnt.value == ''){
			alert('내용을 입력해 주세요!')
			frm.ctnt.focus()
			return false	
		} else if(frm.i_student.value.length === 0){
			alert('내용을 입력해 주세요!')
			frm.i_student.focus()
			return false		
		}
	}
	</script>
</div>
</body>
</html>