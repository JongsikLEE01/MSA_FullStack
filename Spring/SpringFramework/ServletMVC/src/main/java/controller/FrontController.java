package controller;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Board.Controller.BoardController;
import Board.Controller.BoardDeleteProController;
import Board.Controller.BoardInsertController;
import Board.Controller.BoardInsertProController;
import Board.Controller.BoardListController;
import Board.Controller.BoardReadController;
import Board.Controller.BoardUpdateController;
import Board.Controller.BoardUpdateProController;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}
	
	@Override		// doGet() : GET 메소드 방식으로 요청이 올때 실행되는 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// jsp 응답 하기
		String view = "index.jsp";
		boolean redirect = false;
		ModelView modelView = null;
		
		String requsetURI = request.getRequestURI();
		String pathInfo = request.getPathInfo();
		StringBuffer requestURL = request.getRequestURL();
		System.out.println("요청 URI : " + requsetURI);
		System.out.println("requestURL : " + requestURL);
		System.out.println("pathInfo : " + pathInfo);
		
		if(requsetURI.contains("/board")) {
			BoardController boardController =  new BoardController();
			try {
				modelView = boardController.process(request);				
			} catch (Exception e) {
				e.printStackTrace();
			}
			view = modelView.getView();
			redirect = modelView.isRedirect();
		}
		
		 // 모델 등록
		 createModel(modelView, request);
		
		// 컨트롤러 선택
		// 목록
//		if(requsetURI.contains("/board/list.do")) {
//			BoardListController boardListController = new BoardListController();
//			// .process(request) : 참조 자료형이므로 return을 찍지 않아도 값이 넘어옴
//			try { view = boardListController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }		
//		}
//		// 조회
//		if(requsetURI.contains("/board/read.do")) {
//			BoardReadController boardReadController = new BoardReadController();
//			try { view = boardReadController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }		
//		}
//		// 삽입
//		if(requsetURI.contains("/board/insert.do")) {
//			BoardInsertController boardInsertController = new BoardInsertController();
//			try { view = boardInsertController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }		
//		}
//		if(requsetURI.contains("/board/insertPro.do")) {
//			BoardInsertProController boardInsertProController = new BoardInsertProController();
//			try { view = boardInsertProController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }
//			redirect = true;
//		}
//		// 수정
//		if(requsetURI.contains("/board/update.do")) {
//			BoardUpdateController boardUpdateController = new BoardUpdateController();
//			try { view = boardUpdateController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }		
//		}
//		if(requsetURI.contains("/board/updatePro.do")) {
//			BoardUpdateProController boardUpdateProController = new BoardUpdateProController();
//			try { view = boardUpdateProController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }
//			redirect = true;
//		}
//		// 삭제
//		if(requsetURI.contains("/board/deletePro.do")) {
//			BoardDeleteProController boardDeleteProController = new BoardDeleteProController();
//			try { view = boardDeleteProController.process(request); } 
//			catch (Exception e) { e.printStackTrace(); }
//			redirect = true;
//		}
		
		// 페이지 이동
		// redirect : 최초 요청의 request, response가 유지되지 않으며 페이지를 이동
		// forword  : 최초 요청의 request, response가 유지되며 페이지를 이동
		if(redirect == true) {
			response.sendRedirect(request.getContextPath() + view);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	@Override		// doPost() : POST 메소드 방식으로 요청이 올때 실행되는 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 모델 등록 메소드
	 * @param modelView
	 * @param request
	 */
	public void createModel(ModelView modelView, HttpServletRequest request) {
		if(modelView == null) return;
			Map<String, Object> model = modelView.getModel();
		
		if(model == null) return;
		
		Set<String> ketSet = model.keySet();
		for (String key : ketSet) {
			Object value = model.get(key);
			request.setAttribute(key, value);
		}
	}
}