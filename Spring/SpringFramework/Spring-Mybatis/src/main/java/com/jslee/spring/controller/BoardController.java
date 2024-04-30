package com.jslee.spring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jslee.spring.dto.Board;
import com.jslee.spring.service.BoardService;

@Controller					// 컨트롤러로 빈 등록
@RequestMapping("/board")	// 클래스 레벨 경로 /board로 지정
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired			// 의존성 자동 주입
	private BoardService boardService;
	
	/**
	 * 게시글 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping(value =  "/list", method = RequestMethod.GET)
	@GetMapping("/list")		// Spring 4.3 버전 이후부터 사용 가능
	public String list(Model model) throws Exception{
		List<Board> boardList = boardService.list();
		model.addAttribute("boardList",boardList);
		
		return "board/list";	// board/list.jsp 화면 응답
	}
	
	/**
	 * 게시글 조회
	 * @param no
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/read")
	public String read(int no ,Model model) throws Exception{
		Board board = boardService.select(no);
		model.addAttribute("board", board);
		
		return "board/read";
	}
	
	/**
	 * 게시글 등록		-> /board/insert - GET
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/insert")
	public String insert() throws Exception {
		
		return "board/insert";
	}
	
	/**
	 * 게시글 등록	처리 	-> /board/insert - POST
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/insert")
	public String insertPro(Board board) throws Exception{
		int result = boardService.insert(board);
		logger.info("게시글 등록 요청 result? "+result);
		
		return "redirect:/board/list";
	}
	
	/**
	 * 게시글 수정		-> /board/update - GET
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/update")
	public String update(int no ,Model model) throws Exception{
		Board board = boardService.select(no);
		model.addAttribute("board", board);
		
		return "board/update";
	}
	
	/**
	 * 게시글 수정 처리 -> /board/update - POST
	 * @param board
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/update")
	public String updatePro(Board board) throws Exception{
		int result = boardService.update(board);
		logger.info("게시글 수정 요청 result? "+result);
		
		return "redirect:/board/list";
	}

	/**
	 * 게시글 삭제		-> /board/delete - POST
	 * @param no
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/delete")
	public String delete(int no) throws Exception{
		int result =  boardService.delete(no);
		logger.info("게시글 삭제 요청 result? "+result);
		
		return "redirect:/board/list";
	}
}