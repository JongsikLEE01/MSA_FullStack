package com.jslee.springresponse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jslee.springresponse.dto.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    /** 전체 출력
     * [GET]
     * /board/list
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String list(Model model) {
        log.info("GET - /board/list");

        //데이터 요청
        List<Board> boardList = new ArrayList<>();
        boardList.add(new Board(1,"제목1","작성자1","내용1"));
        boardList.add(new Board(2,"제목2","작성자2","내용2"));
        boardList.add(new Board(3,"제목3","작성자3","내용3"));

        // 모델 등록
        model.addAttribute("boardList", boardList);

        // 뷰 페이지 지정
        return "/board/list";
    }

    /** 조회
     * [GET]
     * /board/read?no=?
     * @param model
     * @param no
     * @return
     */
    @GetMapping("/read")
    public String read(Model model, @RequestParam("no") int no) {
        log.info("GET - /board/read");

        // 데이터 요청
        Board board = new Board(no,"제목","작성자","내용");

        // 모델 등록
        model.addAttribute("board", board);

        return "/board/read";
    }

    /** 수정
     * [GET]
     * /board/update?no=?
     * @param model
     * @param no
     * @return
     */
    @GetMapping("/update")
    public String update(Model model, @RequestParam("no") int no) {
        log.info("GET - /board/read");

        // 데이터 요청
        Board board = new Board(no,"제목","작성자","내용");

        // 모델 등록
        model.addAttribute("board", board);

        return "/board/update";
    }

    /** 등록
     * [GET]
     * /board/insert
     * @return
     */
    @GetMapping("/insert")
    public String insert() {
        log.info("GET - /board/read");

        return "/board/insert";
    }



    /** 등록처리
     * @param board
     * @return
     */
    @PostMapping("/insert")
    public String insertPro(Board board){
        log.info("post - /board/insert");
        log.info(board.toString());

        int result = new Random().nextInt(2);

        if(result == 0){
            log.info("등록 실패");
            return "redirect:/board/insert";
        }

        log.info("등록 성공");
        return "redirect:/board/list";
    }

    /* 수정처리
     * put
     * board/update
     * o -> /board/list
     * x -> /board/update?no=10
     */
    @PostMapping("/update")
    public String updatePro(Board board){
        log.info("post - /board/update");
        log.info(board.toString());
        
        int result = new Random().nextInt(2);
        int no = board.getNo();

        if(result == 0){
            log.info(no+"번 게시글 수정 실패");
            return "redirect:/board/update?no="+no;
        }
        
        log.info(no+"번 게시글수정 성공");
        return "redirect:/board/list";
    }

     /** 삭제처리
      *  delete
      *  /board/delete
      *  o -> /board/list
      *  x -> /board/delete?no=10
      */
      @PostMapping("/delete")
    //   public String deletePro(Board board){
      public String deletePro(@RequestParam("no") int no){
        log.info("post - /board/delete");
        // int no = board.getNo();
        int result = new Random().nextInt(2);
        
        if(result == 0){
            log.info(no+"번 게시글 삭제 실패");
            return "redirect:/board/update?no=" + no;
        }
        
        log.info(no+"번 게시글 삭제 성공");
        return "redirect:/board/list";
    }

    /**
     * put 수정 처리
     * @param board
     * @return
     */
    @PutMapping("")
    public ResponseEntity<String> putUpdate(Board board){
        log.info("PUT - /board?no=10");
        log.info(board.toString());
        int no = board.getNo();

        // 게시글 수정 처리
        int result = new Random().nextInt(2);

        if(result == 0){
            log.info(no+"번 게시글 수정 실패");
            return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        log.info(no+"번 게시글수정 성공");
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }

    /**
     * delete 삭제 처리
     * @param no
     * @return
     */
    @DeleteMapping("")
    public ResponseEntity<String> putDelete(@RequestParam("no") int no){
        log.info("DELETE - /board?no=10");

        // 게시글 수정 처리
        int result = new Random().nextInt(2);

        if(result == 0){
            log.info(no+"번 게시글 삭제 실패");
            return new ResponseEntity<>("FAIL",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        log.info(no+"번 게시글 삭제 성공");
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }
}