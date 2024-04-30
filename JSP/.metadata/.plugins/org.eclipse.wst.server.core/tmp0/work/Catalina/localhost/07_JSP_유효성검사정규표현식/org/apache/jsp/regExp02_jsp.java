/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/10.1.19
 * Generated at: 2024-03-27 07:23:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.*;

public final class regExp02_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports,
                 org.apache.jasper.runtime.JspSourceDirectives {

  private static final jakarta.servlet.jsp.JspFactory _jspxFactory =
          jakarta.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("jakarta.servlet");
    _jspx_imports_packages.add("jakarta.servlet.http");
    _jspx_imports_packages.add("jakarta.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile jakarta.el.ExpressionFactory _el_expressionfactory;
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

  public boolean getErrorOnELNotFound() {
    return false;
  }

  public jakarta.el.ExpressionFactory _jsp_getExpressionFactory() {
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

  public void _jspService(final jakarta.servlet.http.HttpServletRequest request, final jakarta.servlet.http.HttpServletResponse response)
      throws java.io.IOException, jakarta.servlet.ServletException {

    if (!jakarta.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
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

    final jakarta.servlet.jsp.PageContext pageContext;
    jakarta.servlet.http.HttpSession session = null;
    final jakarta.servlet.ServletContext application;
    final jakarta.servlet.ServletConfig config;
    jakarta.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    jakarta.servlet.jsp.JspWriter _jspx_out = null;
    jakarta.servlet.jsp.PageContext _jspx_page_context = null;


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
      out.write("<title>Insert title here</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<form name=\"form\" onsubmit=\"return checkForm()\">\r\n");
      out.write("        <p>제목 : <input type=\"text\" name=\"title\" /> </p>\r\n");
      out.write("        \r\n");
      out.write("        <p>아이디 : <input type=\"text\" name=\"id\" /> </p>\r\n");
      out.write("        <p>이름 : <input type=\"text\" name=\"name\" /> </p>\r\n");
      out.write("        \r\n");
      out.write("        <p>전화번호 : <select name=\"phone1\">\r\n");
      out.write("                        <option value=\"010\">010</option>\r\n");
      out.write("                        <option value=\"011\">011</option>\r\n");
      out.write("                        <option value=\"016\">016</option>\r\n");
      out.write("                        <option value=\"017\">017</option>\r\n");
      out.write("                        <option value=\"019\">019</option>\r\n");
      out.write("                    </select> \r\n");
      out.write("                    - <input type=\"text\" maxlength=\"4\" size=\"4\" name=\"phone2\"> \r\n");
      out.write("                    - <input type=\"text\" maxlength=\"4\" size=\"4\" name=\"phone3\">\r\n");
      out.write("                    \r\n");
      out.write("        <p>이메일 : <input type=\"text\" name=\"email\" /> </p>\r\n");
      out.write("        \r\n");
      out.write("        <p><input type=\"submit\" value=\"등록\" /> </p>\r\n");
      out.write("    </form>\r\n");
      out.write("    \r\n");
      out.write("    <script>\r\n");
      out.write("    	// 회원가입 정규 표현식으로 유효성 검사\r\n");
      out.write("    	function checkForm(){\r\n");
      out.write("    		// 아이디 -> 영문자, 한글로 시작\r\n");
      out.write("    		let regExpId = /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+$/\r\n");
      out.write("    		// 이름 -> 한글\r\n");
      out.write("    		let regExpNmae = /^[가-힣]*$/\r\n");
      out.write("    		// 전화번호 [3자리]-[3~4자리]-[4자리] 숫자\r\n");
      out.write("    		let regExpPhone = /^\\d{3}-\\d{3,4}-\\d{4}$/\r\n");
      out.write("    		// 이메일 [숫자,영문자]@[숫자, 영문자].~~.[영문자 2~3자리]\r\n");
      out.write("    		let regExpEmail = /^[0-9a-zA-Z](-_\\.)?[0-9a-zA-Z]*@[0-9a-zA-Z](-_\\.)?[0-9a-zA-Z]*\\.[a-zA-Z]{2,3}$/i\r\n");
      out.write("    		// 부분 분석\r\n");
      out.write("    		// /^[0-9a-zA-Z]	: 시작은 숫자 또는 영어\r\n");
      out.write("    		// (-_\\.)?			: 중간에 -, _, . 0또는 1회 가능\r\n");
      out.write("    		// [0-9a-zA-Z]*		: 그 후 숫자 영어가 0 또는 1이상\r\n");
      out.write("    		\r\n");
      out.write("    		// 전체분석\r\n");
      out.write("    		// 1. [0-9a-zA-Z](-_\\.)?[0-9a-zA-Z]*\r\n");
      out.write("    		// 2. @\r\n");
      out.write("    		// 3. [0-9a-zA-Z](-_\\.)?[0-9a-zA-Z]*\\.\r\n");
      out.write("    		// 4. .\r\n");
      out.write("    		// 5. [a-zA-Z]{2,3}$/i\r\n");
      out.write("    		\r\n");
      out.write("    		let id = form.id.value\r\n");
      out.write("    		let name = form.name.value\r\n");
      out.write("    		let phone = form.phone1.vlaue+ \"-\" +form.phone2.vlaue+ \"-\" +form.phone3.vlaue\r\n");
      out.write("    		let email = form.email.vlaue\r\n");
      out.write("    		\r\n");
      out.write("    		// test : 부합시 true, 부적합시 false\r\n");
      out.write("    		if(!regExpId.test(id)){\r\n");
      out.write("    			alert('아이디 형식에 맞게 입력해주세요')\r\n");
      out.write("    			return false\r\n");
      out.write("    		}\r\n");
      out.write("    		\r\n");
      out.write("    		if(!regExpName.test(name)){\r\n");
      out.write("    			alert('이름 형식에 맞게 입력해주세요')\r\n");
      out.write("    			return false\r\n");
      out.write("    		}\r\n");
      out.write("    		\r\n");
      out.write("    		if(!regExpPhone.test(phone)){\r\n");
      out.write("    			alert('전화 번호 형식에 맞게 입력해주세요')\r\n");
      out.write("    			return false\r\n");
      out.write("    		}\r\n");
      out.write("    		\r\n");
      out.write("    		if(!regExpEmail.test(email)){\r\n");
      out.write("    			alert('이메일 형식에 맞게 입력해주세요')\r\n");
      out.write("    			return false\r\n");
      out.write("    		}\r\n");
      out.write("    		\r\n");
      out.write("    	}\r\n");
      out.write("    </script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof jakarta.servlet.jsp.SkipPageException)){
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
