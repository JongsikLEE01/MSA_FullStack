package com.jslee.springmybatis.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslee.springmybatis.dto.Board;
import com.jslee.springmybatis.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardMapper boardMapper;

    @Override
    public List<Board> list() throws Exception {
        // TODO : boardMapper 로 list() 호출
        // 게시글 목록
        List<Board> boardList = boardMapper.list();

        return boardList;
    }

    @Override
    public Board select(int no) throws Exception {
        // TODO : boardMapper 로 select(no) 호출
        // 게시글 조회
        Board board = boardMapper.select(no);

        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        // TODO : boardMapper 로 insert(Board) 호출
        // 게시글 등록
        int result = boardMapper.insert(board);

        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        // TODO : boardMapper 로 update(Board) 호출
        // 게시글 수정
        int result = boardMapper.update(board);

        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        // TODO : boardMapper 로 delete(no) 호출
        // 게시글 삭제
        int result = boardMapper.delete(no);

        return result;
    }

    @Override
    public int updateView(int no) throws Exception {
        // TODO : boardMapper로 updateView(no) 호출
        // 게시글 조회시 조회수 증가
        int result = boardMapper.updateView(no);

        return result;
    }
}