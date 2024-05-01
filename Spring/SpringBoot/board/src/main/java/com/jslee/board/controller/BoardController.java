package com.jslee.board.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jslee.board.dto.Board;
import com.jslee.board.dto.Files;
import com.jslee.board.dto.Option;
import com.jslee.board.dto.Page;
import com.jslee.board.service.BoardService;
import com.jslee.board.service.FileService;

import lombok.extern.slf4j.Slf4j;

/**
 * /board 경로로 요청이 올때 처리
 * GET - /board/list   : 목록 화면
 * GET - /board/read   : 조회 화면
 * GET - /board/insert : 등록 화면
 * POST - /board/update : 등록 처리
 * GET - /board/update : 수정 화면
 * POST - /board/insert : 수정 처리
 * POST - /board/delete : 삭제 처리
 */
@Slf4j                      // 로그 어노테이션
@Controller                 // 컨트롤러 스프링 빈으로 등록
@RequestMapping("/board")   // 클래스 레벨 요청 경로 매핑
public class BoardController {

/* 데이터 요청과 화면 출력
 * Controller -> Service    (데이터 요청)
 * Service    -> Controller (데이터 전달)
 * Controller -> Model      (모델 등록)
 * model      -> view       (데이터 출력)
 */

    @Autowired
    private BoardService boardService;

    @Autowired
    private FileService fileService;

    /**
     * 게시글 목록 - GET
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model, Page page, 
            // @RequestParam(value = "keyword", defaultValue = "") String keyword
            Option option
            ) throws Exception{
        // 데이터 요청
        // List<Board> boardList = boardService.list(page);         // 페이징
        // List<Board> boardList = boardService.search(keyword);    // 검색
        // List<Board> boardList = boardService.search(option);     // 검색 + 검색 종류 설정
        List<Board> boardList = boardService.list(page, option);    // 검색 + 페이징
        
        // 페이징
        log.info("page? "+page);
        // 검색
        // log.info("keyword? "+ keyword);
        log.info("option? "+ option);

        // 모델 등록
        model.addAttribute("boardList", boardList);
        model.addAttribute("page", page);

        // 동적으로 옵션값을 가져오는 경우
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option("전체",0));
        optionList.add(new Option("제목",1));
        optionList.add(new Option("내용",2));
        optionList.add(new Option("제목+내용",3));
        optionList.add(new Option("작성자",4));
        model.addAttribute("optionList", optionList);

        // 뷰 페이지 지정
        return "/board/list";    // resources/templates/board/list.html
    }
    
    /**
     * 게시글 조회 - GET
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    // @RequestParam("파라미터명")
    // - 스프링 부트 3.2 버전 이하, 생략해도 자동 매핑
    // - 스프링 부트 3.2 버전 이상, 필수로 명시해야 매핑
    @GetMapping("/read")
    public String read(@RequestParam("no") int no , Files file, Model model) throws Exception{
        // 데이터 요청
        Board board = boardService.select(no);

        // 조회수 증가
        boardService.updateView(no);
        
        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(no);
        List<Files> fileList = fileService.listByParent(file);

        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);
        return "/board/read";
    }
    
    /**
     * 게시글 등록 - GET
     * @return
     */
    @GetMapping("/insert")
    public String insert() {

        return "/board/insert";
    }

    /**
     * 게시글 등록 처리 - POST
     * @param board
     * @return
     * @throws Exception
     */
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {

        log.info(board.toString());

        int result = boardService.insert(board);

        if(result == 0){
            log.info("게시글 등록 실패...");
            return "redirect:/board/insert";
        }

        log.info("게시글 등록 성공...");
        return "redirect:/board/list";
    }
    
    /**
     * 게시글 수정 - GET
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no ,Model model, Files file) throws Exception{
        Board board = boardService.select(no);

        // 파일 목록 요청
        file.setParentTable("board");
        file.setParentNo(no);
        List<Files> fileList = fileService.listByParent(file);

        model.addAttribute("board", board);
        model.addAttribute("fileList", fileList);

        model.addAttribute("board", board);
        return "/board/update";
    }

    /**
     * 게시글 수정 처리 - PUT -> POST
     * @param board
     * @return
     * @throws Exception
     */
    // @PutMapping("/update")
    @PostMapping("/update")
    public String updatePro(Board board) throws Exception {
        int result = boardService.update(board);
        int no = board.getNo();

        if(result == 0){
            log.info("게시글 수정 실패...");
            return "redirect:/board/update?no="+no+"&error";
        }

        log.info("게시글 수정 성공...");
        return "redirect:/board/list";
    }

    /**
     * 게시글 삭제 처리 - DELETE -> POST
     * @param board
     * @return
     * @throws Exception
     */
    // @DeleteMapping("/delete")
    @PostMapping("/delete")
    public String delete(@RequestParam("no") int no) throws Exception {
        int result = boardService.delete(no);

        if(result == 0){
            log.info("게시글 삭제 실패...");
            return "redirect:/board/update?no="+no+"&error";
        }

        // 첨부 파일 삭제
        Files file = new Files();
        file.setParentTable("board");
        file.setParentNo(no);
        fileService.deleteByParent(file);

        log.info("게시글 삭제 성공...");
        return "redirect:/board/list";
    }

    /**
     * 게시글 검색 - GET
     * @param model
     * @return
     */
    // @GetMapping("/search")
    // public String search(@RequestParam String keyword,Model model) throws Exception{
    //     // 데이터 요청
    //     List<Board> boardList = boardService.search(keyword);
    //     // 모델 등록
    //     model.addAttribute("boardList", boardList);
    //     // 뷰 페이지 지정
    //     return "/board/list";    // resources/templates/board/list.html
    // }

    @GetMapping("/likes")
    public String updateLikes(int likes, Model model) throws Exception{
        // 데이터 요청
        int result = boardService.updateLike(likes);

        if(result == 0){
            log.info("좋아요 등록 실패...");
            return "redirect:/board/read";
        }
        log.info("좋아요 등록 성공...");
        return "/board/read";
    }
}