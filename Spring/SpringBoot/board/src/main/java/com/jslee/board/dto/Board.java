// 게시글
package com.jslee.board.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Data
public class Board {
    private int no;        
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private Date updDate;
    private int views;

    // 파일 정보
    List<MultipartFile> file;

    // 썸네일 이미지 파일
    MultipartFile thumbnail;
    
    // 파일 번호
    private int fileNo;
}
 