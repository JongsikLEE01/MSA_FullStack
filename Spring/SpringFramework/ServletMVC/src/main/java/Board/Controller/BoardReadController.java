package Board.Controller;

import javax.servlet.http.HttpServletRequest;

import Board.dto.Board;
import Board.service.BoardService;
import Board.service.BoardServiceImpl;

public class BoardReadController {
	private BoardService boardService = new BoardServiceImpl();
	
	public String process(HttpServletRequest request) throws Exception{
		String reqNo = request.getParameter("no");
		int no = reqNo == null ? 0 : Integer.parseInt(reqNo);
		
		Board board = boardService.select(no);
		request.setAttribute("board", board);
		String view = "/board/read.jsp";
		return view;
	}
}