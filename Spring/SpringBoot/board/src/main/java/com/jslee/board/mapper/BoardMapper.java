package com.jslee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jslee.board.dto.Board;
import com.jslee.board.dto.Option;
import com.jslee.board.dto.Page;

@Mapper
public interface BoardMapper {

    // 게시글 목록
    public List<Board> list(@Param("page") Page page, @Param("option") Option option) throws Exception;
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
    // @Param(파라미터명) :  한개일 경우 생략 가능, 여러개 일 경우 명시 해야함
    // public List<Board> search(@Param("keyword") String keyword) throws Exception;
    public List<Board> search(@Param("option") Option option) throws Exception;

    // 게시글 번호(기본키) 최댓값
    public int maxPk() throws Exception;

    // 게시글 데이터 개수 조회
    public int count(@Param("option") Option option) throws Exception;

    // 게시글 좋아요
    public int updateLike(int likes) throws Exception;
}