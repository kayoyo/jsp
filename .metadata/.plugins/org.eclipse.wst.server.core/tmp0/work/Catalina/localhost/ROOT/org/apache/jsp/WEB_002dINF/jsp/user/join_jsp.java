/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.37
 * Generated at: 2020-09-02 04:51:07 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class join_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>회원가입</title>\r\n");
      out.write("<style>\r\n");
      out.write("\t#container {width:700px; margin: 0 auto; background-color: #e7d6c9; }\r\n");
      out.write("\t#container > h1 \r\n");
      out.write("\t.err {color: red;}\r\n");
      out.write("\t\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div id=\"container\">\r\n");
      out.write("\t<h1>회원가입</h1>\r\n");
      out.write("\t<div class=\"err\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</div>\r\n");
      out.write("\t<form id=\"frm\" action=\"/join\" method=\"post\" onsubmit=\"return check()\">\r\n");
      out.write("\t<div id=\"uID\">\r\n");
      out.write("\t\t<label>아이디 \r\n");
      out.write("\t\t\t<input type=\"text\" name=\"user_id\" placeholder=\" 아이디\" required value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${data.user_id}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"uPw\">\r\n");
      out.write("\t\t<label>패스워드\r\n");
      out.write("\t\t\t<input type=\"password\" name=\"user_pw\" placeholder=\" 패스워드\" required>\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"uPwE\">\r\n");
      out.write("\t\t<label>패스워드 확인\r\n");
      out.write("\t\t\t<input type=\"password\" name=\"user_pwEtc\" placeholder=\" 확인 비밀번호\">\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"uNm\">\r\n");
      out.write("\t\t<label>이름\r\n");
      out.write("\t\t\t<input type=\"text\" name=\"user_name\" placeholder=\" 이름\" required value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${data.user_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"uEm\">\r\n");
      out.write("\t\t<label>이메일\r\n");
      out.write("\t\t\t<input type=\"email\" name=\"user_email\" placeholder=\" 이메일\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${data.user_email}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("\t\t</label>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<div id=\"btn\">\r\n");
      out.write("\t\t\t<input type=\"submit\" value=\"가입하기\">\r\n");
      out.write("\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t<script>\r\n");
      out.write("\tfunction check(){\r\n");
      out.write("\t\tif(frm.user_id.value.length < 5){\r\n");
      out.write("\t\t\talert('아이디는 5글자 이상이 되어야 합니다.')\r\n");
      out.write("\t\t\tfrm.user_id.focus()\r\n");
      out.write("\t\t\treturn false\r\n");
      out.write("\t\t} else if(frm.user_pw.value.length < 5){\r\n");
      out.write("\t\t\talert('비밀번호는 5글자 이상이 되어야 합니다.')\r\n");
      out.write("\t\t\tfrm.user_pw.focus()\r\n");
      out.write("\t\t\treturn false\r\n");
      out.write("\t\t} else if(frm.user_pw.value != frm.user_pwEtc.value){\r\n");
      out.write("\t\t\talert('확인 비밀번호를 다시 입력 해 주세요.')\r\n");
      out.write("\t\t\tfrm.user_pw.focus()\r\n");
      out.write("\t\t\treturn false\r\n");
      out.write("\t\t} else if(frm.user_name.value.length > 0){\r\n");
      out.write("\t\t\tconst korean = /[^가-힣]/;\r\n");
      out.write("\t\t\tconst result = korean.test(frm.user_name.value)\r\n");
      out.write("\t\t\tif(result){\r\n");
      out.write("\t\t\t\talert('이름은 한글만 입력 해 주세요.')\r\n");
      out.write("\t\t\t\tfrm.user_name.focus()\r\n");
      out.write("\t\t\t\treturn false\r\n");
      out.write("\t\t\t}\t\t\r\n");
      out.write("\t\t} if(frm.user_email.value.length > 0){\r\n");
      out.write("\t\t\tconst email = /^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$/i\t\t\r\n");
      out.write("\t\t\tif(!email.test(frm.user_email.value)){\r\n");
      out.write("\t\t\t\talert('이메일을 확인 해 주세요.')\r\n");
      out.write("\t\t\t\tfrm.user_email.focus()\r\n");
      out.write("\t\t\t\treturn false\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
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
