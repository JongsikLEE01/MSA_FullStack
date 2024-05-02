package com.jslee.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jslee.board.dto.Reply;
import com.jslee.board.service.ReplyService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 댓글 목록 : [GET]    /reply/{boardNo}
 * 댓글 등록 : [POST]   /reply
 * 댓글 수정 : [PUT]    /reply
 * 댓글 삭제 : [DELETE] /reply/{no}
 */
@Slf4j
@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    /**
     * 댓글 목록
     * @param boardNo
     * @return
     * @throws Exception
     */
    /**
     1. JSON 데이터 응답 후, 클라이언트 측에서 렌더링 (CSR)
     @GetMapping("/{boardNo}")
     public ResponseEntity<List<Reply>> list(@PathVariable("boardNo") int boardNo) throws Exception {
         // 글번호에 따른 댓글 목록 요청
         List<Reply> replyList = replyService.listByBoard(boardNo);
         log.info("replyList? "+replyList);
         
         return new ResponseEntity<>(replyList, HttpStatus.OK);
        }
    */
    // 2. 서버 측에서 렌더링 후 HTML 응답 (SSR)
    @GetMapping("/{boardNo}")
    public String list(@PathVariable("boardNo") int boardNo, Model model) throws Exception {
        // 글번호에 따른 댓글 목록 요청
        List<Reply> replyList = replyService.listByBoard(boardNo);
        // 모델 등록
        model.addAttribute("replyList",replyList);
        // 뷰 페이지 지정
        return "reply/list";
    }
    

    /**
     * 댓글 등록
     * @param reply
     * @return 
     * @throws Exception
     */
    @PostMapping("")
    public ResponseEntity<String> insert(@RequestBody Reply reply) throws Exception {
        log.info("reply? "+reply);

        // 데이터 요청
        int result = replyService.insert(reply);
        if (result == 0)
            // 댓글 등록 실패
            return new ResponseEntity<>("FAIL",HttpStatus.OK);
         
        // 댓글 등록 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.CREATED);
    }
    
    /**
     * 댓글 수정
     * @param reply
     * @return
     * @throws Exception
     */
    @PutMapping("")
    public ResponseEntity<String> update(@RequestBody Reply reply) throws Exception {
        // 데이터 요청
        int result = replyService.update(reply);
        if (result == 0)
            // 댓글 수정 실패
            return new ResponseEntity<>("FAIL",HttpStatus.OK);
        
        // 댓글 수정 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }


    /**
     * 댓글 삭제
     * @param no
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{no}")
    public ResponseEntity<String> delete(@PathVariable("no") int no) throws Exception {
        // 데이터 요청
        int result = replyService.delete(no);
        if (result == 0)
            // 댓글 삭제 실패
            return new ResponseEntity<>("FAIL",HttpStatus.OK);
        
        // 댓글 삭제 성공
        return new ResponseEntity<>("SUCCESS",HttpStatus.OK);
    }
}