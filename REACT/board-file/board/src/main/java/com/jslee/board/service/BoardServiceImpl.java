package com.jslee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jslee.board.dto.Board;
import com.jslee.board.dto.Files;
import com.jslee.board.mapper.BoardMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardMapper boardMapper;

    @Autowired
    private FileService fileService;

    @Override
    public List<Board> list() throws Exception {
       return boardMapper.list();
    }

    @Override
    public Board select(int no) throws Exception {
        return boardMapper.select(no);
    }

    @Override
    public int update(Board board) throws Exception {
        return boardMapper.update(board);
    }

    @Override
    public Board insert(Board board) throws Exception {
        int result = boardMapper.insert(board);
        log.info("board {}", board);
        log.info("result {}", result);
        int newBoardNo = board.getNo();
        Board newBoard = boardMapper.select(newBoardNo);

        // 파일 업로드
        Files fileInfo = new Files();
        String parentTable = "board";
        fileInfo.setParentTable(parentTable);
        fileInfo.setParentNo(newBoardNo);
        List<MultipartFile> fileList = board.getFiles();

        if(fileList == null || fileList.isEmpty()){
            log.info("첨부 파일 없음...");
            return newBoard;
        }

        List<Files> uploadedFileList = fileService.uploadFiles(fileInfo, fileList);
        if (uploadedFileList == null || uploadedFileList.isEmpty()) {
            log.info("파일 업로드 실패...");
        }else{
            log.info("파일 업로드 성공...");
            log.info(uploadedFileList.toString());
        }

        return newBoard;
    }

    @Override
    public int delete(int no) throws Exception {
        return boardMapper.delete(no);
    }
    
}
