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
    public List<Reply> listByBoard(int boardNo) throws Exception {
        // 글번호에 따른 댓글 목록
        List<Reply> replyList = replyMapper.listByBoard(boardNo);

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
        int parentNo = reply.getParentNo();

        // 댓글 등록
        // - 댓글 번호(no)와 부모번호(parent_no)를 똑같이 수정
        if(result > 0 && parentNo == 0){
            int no = replyMapper.max();
            reply.setNo(no);
            reply.setParentNo(no);
            replyMapper.update(reply);
        }
        // 답글 등록
        // - 부모 번호가 지정되서 등록

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

        // 자식 답글 삭제
        if(result > 0)
            result += deleteByParentNo(no);
        
        return result;
    }

    @Override
    public int deleteByBoardNo(int boardNo) throws Exception {
        // 댓글 종속 삭제
        int result = replyMapper.deleteByBoardNo(boardNo);
        return result;
    }

    @Override
    public int deleteByParentNo(int parentNo) throws Exception {
        int result = replyMapper.deleteByParentNo(parentNo);
        return result;
    }
}
