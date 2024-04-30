package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;

public class InitParamFilter implements Filter{
	private FilterConfig filterConfig = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("필터02 초기화");
		this.filterConfig = filterConfig;
		
	}
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("initFilter 수행...");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		String param1 = filterConfig.getInitParameter("param1");
		String param2 = filterConfig.getInitParameter("param2");
		
		String message;
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter w = response.getWriter();
		
		if(id.equals(param1) && passwd.equals(param2))
			message = "로그인 성공.."; 
		else {
			message="로그인 실패..";
			// 관리자 계정? admin/1234
			
			// 비밀번호가 틀릴 시 forward 방식으로 index.jsp로 이동
			 RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			 rd.forward(request, response);
			
			// 리다이렉트
//			 String root = ((HttpServletRequest) request).getContentType();
//			 ((HttpServletRequest) response).sendRedirect(root);
		}
		w.println(message);
		
		
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Filter02 헤제...");
	}

}
