<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<%@ page import = "com.koreait.web.BoardVO" %>
      

<%! 
  Connection getCon() throws Exception{
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String username = "hr";
	String password = "koreait2020";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url, username, password);
	//getConnection은 static 메소드 : static이고, 파라미터가 3개인 멤버필드 > 멤버필드가 static인 경우 static 메소드에서 사용해야함 
	System.out.println("접속 성공"); //Class.forName, Connection con에서 에러가 발생하지 않았다면 출력 되는 것임
	return con;
}
%>
    
<% 
  //scope(변수가 접근 할 수 있는 범위) : 아래의 변수를 이 위치에 선언한 이유는 try부터 finally 까지 사용하기 위해
  List <BoardVO> boardList = new ArrayList();
  Connection con = null; //데이터베이스 연결
  PreparedStatement ps = null; //쿼리문 완성 + 쿼리문 실행
  ResultSet rs = null; //select문의 결과를 담을 객체(read)
  
  String sql = " SELECT i_board, title FROM t_board ORDER BY i_board DESC";

	try {
		con = getCon();
		ps = con.prepareStatement(sql);//static 메소의 경우 클래스명.__으로 시작
		                               // prepareStatement의 파라미터(sql) 타입은 String
		rs = ps.executeQuery(); //쿼리문이 select 일 때  executeQuery 무조건 사용, 리턴타입은 ResultSet
		
		
		while(rs.next()){ //while안에 있는 경우 리턴 타입은 true or flase(boolean)
			int i_board = rs.getInt("i_board"); //파라미터 타입은 String, 리턴타입은 int
			String title = rs.getNString("title"); //파라미터 타입은 String, 리턴타입은 String
			
			BoardVO vo = new BoardVO(); //반드시 while문안에서 객체화 해야함 
			vo.setI_board(i_board);
			vo.setTitle(title);
			
			boardList.add(vo);
			
			//레코드의 첫 줄을 실행해서 값이 있다면 true, 없다면 false가 되고 while문 종료
		}
		
	} catch(Exception e){
		e.printStackTrace(); //이 메소드를 호출하면 메소드가 내부적으로 가장 자세한 예외정보(에러)를 화면에 출력, 리턴타입이 없다
	} finally { //Sting이기 때문에 리소스를 많이 잡아먹음, close하지 않으면 서버에 무리가 감
		if(rs != null){ try{ rs.close(); } catch(Exception e) {} } //1번째 실행
		if(ps != null){ try{ ps.close(); } catch(Exception e) {} } //2번째 실행
		if(con != null){ try{ con.close(); } catch(Exception e) {} } //3번째 실행
		} //rs, ps, con을 하나의 try catch에 담게 되면, rs.close를 했을 때 에러가 터지면 
	      //ps.close와 con.close는 실행이 되지 않고 바로 catch로 넘어감 
	    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<style>
 
 table, tr, td, th {border: 1px solid black; 
                    border-collapse: collapse; 
                    text-align: center;
                    margin : 0px auto;
                    width: 300px;
                    }
 
 th {background-color: gray;}
 tr {line-height: 30px}
 
 .title {margin: 30px; 
         text-align: center; 
         font-weight: bold; font-size: 20px;} 
</style>
</head>
<body>
	<div class = "container">
	<div class = "title">게시판 리스트</div>
	<table>
	<tr>
	<th>No</th>
	<th>제목</th>
	</tr>
	<% for(BoardVO vo : boardList){ %>
	<tr>
	<td><%= vo.getI_board() %> </td>
	<td> 
	<a href="/jsp/boardDetail.jsp?i_board=<%= vo.getI_board() %>"> 
		<%= vo.getTitle() %> 
	</a>
	</td>
	</tr>
	<% } %>
	
	</table>
	<div class=insert>
	<a href = "/jsp/BoardWrite.jsp">글쓰기</a>
	</div>
	</div>	
	<script>
	
	</script>
</body>
</html>