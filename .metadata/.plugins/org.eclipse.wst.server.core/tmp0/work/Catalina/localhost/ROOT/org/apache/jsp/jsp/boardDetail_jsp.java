/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.37
 * Generated at: 2020-08-03 03:46:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import com.koreait.web.BoardVO;

public final class boardDetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  
    Connection getCon() throws Exception{
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String username = "hr";
	String password = "koreait2020";
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection(url, username, password);
	System.out.println("접속 성공"); 
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
	
	BoardVO vo = new BoardVO();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

      out.write('	');
      out.write('\r');
      out.write('\n');
 	
	//String nm="";
	String strI_board = request.getParameter("i_board");//boardList.jsp에서 a태그로 주소값을 받았음(쿼리스트링)
	//PK의 값은 레코드를 구분하기 위한 유일성
	
	if(strI_board == null) { //null이 아니라면 alert가 실행되지 않고 아래가 실행됨

      out.write("   <script>\r\n");
      out.write("      alert('잘못된 접근 입니다');\r\n");
      out.write("      location.href='/jsp/Boardlist.jsp';\r\n");
      out.write("      </script>\r\n");
 	
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>상세페이지</title>\r\n");
      out.write("<style>\r\n");
      out.write("\r\n");
      out.write(".title {margin: 30px; text-align: center; \r\n");
      out.write("        font-weight: bold; font-size: 20px;}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class = \"container\">\r\n");
      out.write("\t<div class=\"title\">상세 페이지");
      out.print(strI_board);
      out.write("</div>\r\n");
      out.write("\t<div class=\"back\">\r\n");
      out.write("\t<a href=\"/jsp/Boardlist.jsp\">리스트로 가기</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=delete>\r\n");
      out.write("\t<a href=\"#\" onclick=\"procDel(");
      out.print(i_board );
      out.write(")\">삭제</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div class=update>\r\n");
      out.write("\t<a href=\"/jsp/boardMod.jsp?i_board=");
      out.print(i_board );
      out.write("\">수정</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<!--<div>");
      out.print(vo.getNm());
      out.write("</div>--> \r\n");
      out.write("\t<div>제목 : ");
      out.print(vo.getTitle());
      out.write("</div>\r\n");
      out.write("\t<div>내용 : ");
      out.print(vo.getCtnt());
      out.write("</div>\r\n");
      out.write("\t<div>작성자 : ");
      out.print(vo.getI_student());
      out.write("<div>\r\n");
      out.write("\t\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction procDel(i_board){\r\n");
      out.write("\t\t//alert('i_board : ' + i_board)\r\n");
      out.write("\t\t//var result = confirm('삭제하시겠습니까?')\r\n");
      out.write("\t\t//if(result){\r\n");
      out.write("\t\t//location.href = '/jsp/boardDel.jsp?i_board=' + i_board }\r\n");
      out.write("\t\tif(confirm('삭제하시겠습니까?')){\r\n");
      out.write("\t\t\tlocation.href = '/jsp/boardDel.jsp?i_board=' + i_board;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t</div>\r\n");
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
