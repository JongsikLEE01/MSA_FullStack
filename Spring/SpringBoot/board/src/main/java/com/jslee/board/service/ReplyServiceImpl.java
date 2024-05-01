package com.jslee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslee.board.dto.Reply;
import com.jslee.board.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> list() throws Exception {
        // 댓글 목록
        List<Reply> replyList = replyMapper.list();

        return replyList;
    }

    @Override
    public Reply select(int no) throws Exception {
        // 댓글 조회
        Reply reply = replyMapper.select(no);

        return reply;
    }

    @Override
    public int insert(Reply reply) throws Exception {
        // 댓글 등록
        int result = replyMapper.insert(reply);

        return result;
    }

    @Override
    public int update(Reply reply) throws Exception {
        // 댓글 수정
        int result = replyMapper.update(reply);

        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        // 댓글 삭제
        int result = replyMapper.delete(no);

        return result;
    }
    
}
