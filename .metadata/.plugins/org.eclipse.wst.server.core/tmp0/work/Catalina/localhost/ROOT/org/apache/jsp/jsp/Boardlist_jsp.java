/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.37
 * Generated at: 2020-08-03 03:46:28 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.util.*;
import com.koreait.web.BoardVO;

public final class Boardlist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

 
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

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("java.sql");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("com.koreait.web.BoardVO");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("      \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
 
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
	    

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>게시판 리스트</title>\r\n");
      out.write("<style>\r\n");
      out.write(" \r\n");
      out.write(" table, tr, td, th {border: 1px solid black; \r\n");
      out.write("                    border-collapse: collapse; \r\n");
      out.write("                    text-align: center;\r\n");
      out.write("                    margin : 0px auto;\r\n");
      out.write("                    width: 300px;\r\n");
      out.write("                    }\r\n");
      out.write(" \r\n");
      out.write(" th {background-color: gray;}\r\n");
      out.write(" tr {line-height: 30px}\r\n");
      out.write(" \r\n");
      out.write(" .title {margin: 30px; \r\n");
      out.write("         text-align: center; \r\n");
      out.write("         font-weight: bold; font-size: 20px;} \r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class = \"container\">\r\n");
      out.write("\t<div class = \"title\">게시판 리스트</div>\r\n");
      out.write("\t<table>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t<th>No</th>\r\n");
      out.write("\t<th>제목</th>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t");
 for(BoardVO vo : boardList){ 
      out.write("\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t<td>");
      out.print( vo.getI_board() );
      out.write(" </td>\r\n");
      out.write("\t<td> \r\n");
      out.write("\t<a href=\"/jsp/boardDetail.jsp?i_board=");
      out.print( vo.getI_board() );
      out.write("\"> \r\n");
      out.write("\t\t");
      out.print( vo.getTitle() );
      out.write(" \r\n");
      out.write("\t</a>\r\n");
      out.write("\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t");
 } 
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<div class=insert>\r\n");
      out.write("\t<a href = \"/jsp/BoardWrite.jsp\">글쓰기</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</div>\t\r\n");
      out.write("\t<script>\r\n");
      out.write("\t\r\n");
      out.write("\t</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
