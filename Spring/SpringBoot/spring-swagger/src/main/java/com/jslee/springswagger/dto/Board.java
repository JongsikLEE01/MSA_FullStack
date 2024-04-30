package com.jslee.springswagger.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Board {
    @Schema(description = "글번호 입니다(GET, PUT, DELETE 요청시 필요)")
    private int no;
    @Schema(description = "제목 입니다(POST, PUT 요청시 필요)")
    private String title;
    @Schema(description = "작성자 입니다(POST, PUT 요청시 필요)")
    private String writer;
    @Schema(description = "내용 입니다(POST, PUT 요청시 필요)")
    private String content;
    @Schema(description = "등록일자 입니다(DEFAULT : now(), sysdate() 현재 시각으로 등록)")
    private Date regDate;
    @Schema(description = "수정일자 입니다(DEFAULT : now(), sysdate() 현재 시각으로 등록)")
    private Date updDate;
    @Schema(description = "조회수 입니다(DEFAULT : 0, 사용자 조회시 1씩 증가)")
    private int views;

    public Board(String title, String writer, String content){
        this.title = title;
        this.writer = writer;
        this.content = content;
    }
}
