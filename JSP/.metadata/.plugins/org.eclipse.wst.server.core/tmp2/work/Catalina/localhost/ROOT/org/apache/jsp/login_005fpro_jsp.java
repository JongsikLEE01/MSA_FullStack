/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.100
 * Generated at: 2024-04-01 03:54:59 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.oreilly.servlet.CookieNotFoundException;
import java.net.URLEncoder;
import shop.Service.UserServiceImpl;
import shop.Service.UserService;
import shop.DAO.UserDAO;
import shop.DTO.Users;

public final class login_005fpro_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(5);
    _jspx_dependants.put("jar:file:/C:/JSLEE/JAVA/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/shop/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/c.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar", Long.valueOf(1711692764518L));
    _jspx_dependants.put("jar:file:/C:/JSLEE/JAVA/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/shop/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/fn.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("jar:file:/C:/JSLEE/JAVA/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/shop/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/sql.tld", Long.valueOf(1425946270000L));
    _jspx_dependants.put("jar:file:/C:/JSLEE/JAVA/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/shop/WEB-INF/lib/taglibs-standard-impl-1.2.5.jar!/META-INF/fmt.tld", Long.valueOf(1425946270000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(3);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.LinkedHashSet<>(6);
    _jspx_imports_classes.add("com.oreilly.servlet.CookieNotFoundException");
    _jspx_imports_classes.add("java.net.URLEncoder");
    _jspx_imports_classes.add("shop.Service.UserServiceImpl");
    _jspx_imports_classes.add("shop.DTO.Users");
    _jspx_imports_classes.add("shop.Service.UserService");
    _jspx_imports_classes.add("shop.DAO.UserDAO");
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

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	Users user = new Users();
	user.setId(id);
	user.setPassword(password);
	
	// 로그인 요청
	UserService userService = new UserServiceImpl();
	Users loginUser = userService.login(user);
	
	// 로그인 실패 여부 체크 및 반환
	if(loginUser == null){
		response.sendRedirect("login.jsp?msg=0");
		return;
	}
	
	// 아이디 저장
	String rememberId = request.getParameter("rememberId");
	Cookie cookieRememberId = new Cookie("rememberId", "");
	Cookie cookieUserId = new Cookie("userId", "");
	
	// 체크박스 체크 시, 값 : on
	if( rememberId != null && rememberId.equals("on") ) {
		// 쿠키 생성
		// UTF-8 으로 인코딩하여 값을 저장해야한다.
		cookieRememberId.setValue( URLEncoder.encode( rememberId, "UTF-8" ) );
		cookieUserId.setValue( URLEncoder.encode( id, "UTF-8" ) );
	}
	// 체크박스 미체크 시
	else {
		// 쿠키 삭제
		cookieRememberId.setMaxAge(0);
		cookieUserId.setMaxAge(0);
	}
	// 쿠키 응답에 등록
	response.addCookie(cookieRememberId);
	response.addCookie(cookieUserId);
	// 아이디 저장 끝
	
	
	// 자동 로그인
	String rememberMe  = request.getParameter("rememberMe");
	Cookie cookieRememberMe = new Cookie("rememberMe", "");
	Cookie cookieToken = new Cookie("token", "");
	
	// 자동 로그인 체크 여부, 토큰 쿠키에 대한 경로 설정
	cookieRememberMe.setPath("/");
	cookieToken.setPath("/");
	// 쿠키 유효기간 설정
	cookieRememberMe.setMaxAge(7 * 60 * 60 * 24);		// 초단위 -> 7일
	cookieToken.setMaxAge(7 * 60 * 60 * 24);
	
	// 자동로그인 체크 여부에 따른 쿠키 생성 및 삭제
	if( rememberMe != null && rememberMe.equals("on") ) {
		// 토큰 발행
		String tokenValue = userService.refreshToken(id);
		// 쿠키 생성
		cookieRememberMe.setValue(URLEncoder.encode(rememberMe,"UTF-8"));
		cookieToken.setValue(URLEncoder.encode(tokenValue, "UTF-8"));
	}else{
		// 자동 로그인 미체크
		// 쿠키 삭제
		cookieRememberMe.setMaxAge(0);
		cookieToken.setMaxAge(0);
	}
	
	// 응답에 쿠키 등록
	response.addCookie(cookieRememberMe);
	response.addCookie(cookieToken);	
	// 자동 로그인 끝
	
	
	if( loginUser != null ) {
		// 로그인 성공
		// 세션에 아이디 등록 후, 메인 페이지로 이동
		session.setAttribute("loginId", loginUser.getId());
		response.sendRedirect("index.jsp");
	}
	else {
		// 로그인 실패
		response.sendRedirect("login.jsp?msg=0");
	}

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