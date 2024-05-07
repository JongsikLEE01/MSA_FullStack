// 댓글
package com.jslee.board.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Reply {
    private int no;
    private int boardNo;
    private int parentNo;
    private String writer;
    private String content;
    private Date regDate;
    private Date updDate;

    List<Reply> childList;
}