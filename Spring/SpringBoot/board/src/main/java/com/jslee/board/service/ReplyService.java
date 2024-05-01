package com.jslee.board.service;

import java.util.List;

import com.jslee.board.dto.Reply;

public interface ReplyService {
    // 게시글 목록
    public List<Reply> list() throws Exception;

    // 게시글 조회
    public Reply select(int no) throws Exception;

    // 게시글 등록
    public int insert(Reply reply) throws Exception;

    // 게시글 수정
    public int update(Reply reply) throws Exception;

    // 게시글 삭제
    public int delete(int no) throws Exception;
}