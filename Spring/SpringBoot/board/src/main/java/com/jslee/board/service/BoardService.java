package com.jslee.board.service;

import java.util.List;

import com.jslee.board.dto.Board;

public interface BoardService {
    // 게시글 목록
    public List<Board> list() throws Exception;
    // 게시글 조회
    public Board select(int no) throws Exception;
    // 게시글 등록
    public int insert(Board board) throws Exception;
    // 게시글 수정
    public int update(Board board) throws Exception;
    // 게시글 삭제
    public int delete(int no) throws Exception;
    // 조회수 증가
    public int updateView(int no) throws Exception;
    // 게시글 검색
    public List<Board> serch(String keyword) throws Exception;
}