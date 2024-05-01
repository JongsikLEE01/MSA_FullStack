package com.jslee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jslee.board.dto.Board;
import com.jslee.board.dto.Files;
import com.jslee.board.dto.Option;
import com.jslee.board.dto.Page;
import com.jslee.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
    
    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private FileService fileService;

    @Override
    // public List<Board> list(Page page) throws Exception {
    public List<Board> list(Page page, Option option) throws Exception {
        // 게시글 데이터 개수 조회
        int total =  boardMapper.count(option);
        page.setTotal(total);

        // boardMapper 로 list() 호출
        // 게시글 목록
        List<Board> boardList = boardMapper.list(page, option);

        return boardList;
    }

    @Override
    public Board select(int no) throws Exception {
        // boardMapper 로 select(no) 호출
        // 게시글 조회
        Board board = boardMapper.select(no);

        return board;
    }

    @Override
    public int insert(Board board) throws Exception {
        // boardMapper 로 insert(Board) 호출
        // 게시글 등록
        int result = boardMapper.insert(board);

        String parentTable = "board";
        int parentNo = boardMapper.maxPk();

        // 썸네일 업로드
        // - 부모 테이블, 부모 번호, 멀티파트파일, 파일 코드(1)-> 썸네일
        MultipartFile thumbnailFile = board.getThumbnail();

        // 썸네일 파일 업로드한 경우만 추가
        if(thumbnailFile != null && !thumbnailFile.isEmpty()){
            Files thumbnail = new Files();
            thumbnail.setFile(thumbnailFile);
            thumbnail.setParentTable(parentTable);
            thumbnail.setParentNo(parentNo);
            thumbnail.setFileCode(1);   // 썸네일 파일 코드(1)
            fileService.upload(thumbnail);       // 썸네일 파일 업로드
        }
        
        // 파일 업로드
        List<MultipartFile> fileList = board.getFile();
        if( !fileList.isEmpty() ){
            for (MultipartFile file : fileList) {
                if (file.isEmpty()) continue;

                // 파일 정보 등록
                Files  uploadFile = new Files();
                uploadFile.setFileCode(1);
                uploadFile.setParentTable(parentTable);
                uploadFile.setParentNo(parentNo);
                uploadFile.setFile(file);

                fileService.upload(uploadFile);
            }
        }

        return result;
    }

    @Override
    public int update(Board board) throws Exception {
        // ""boardMapper 로 update(Board) 호출
        // 게시글 수정
        int result = boardMapper.update(board);

        return result;
    }

    @Override
    public int delete(int no) throws Exception {
        // boardMapper 로 delete(no) 호출
        // 게시글 삭제
        int result = boardMapper.delete(no);

        return result;
    }

    @Override
    public int updateView(int no) throws Exception {
        // boardMapper로 updateView(no) 호출
        // 게시글 조회시 조회수 증가
        int result = boardMapper.updateView(no);

        return result;
    }

    @Override
    // public List<Board> search(String keyword) throws Exception {
    public List<Board> search(Option option) throws Exception {
        // boardMapper 로 list() 호출
        // List<Board> boardList = boardMapper.search(keyword);
        List<Board> boardList = boardMapper.search(option);

        return boardList;
    }

    @Override
    public int updateLike(int likes) throws Exception {
        // boardMapper로 updateLike(likes) 호출
        // 버튼 클릭시 좋아요 수 증가
        int result = boardMapper.updateLike(likes);

        return result;
    }
}