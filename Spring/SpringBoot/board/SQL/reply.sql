-- 댓글 테이블 생성
-- 게시글(board)에 종속된 테이블
CREATE TABLE reply(
    no              INT             NOT NULL AUTO_INCREMENT,              -- 댓글 번호
    board_no        INT             NOT NULL,                             -- 글 번호
    parent_no       INT             NOT NULL,                             -- 부모 번호
    writer          VARCHAR(100)    NOT NULL,                             -- 작성자
    content         TEXT            NOT NULL,                             -- 내용
    reg_date        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,   -- 등록일자
    upd_date        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,   -- 수정일자
    PRIMARY KEY(no)
);

-- 댓글 조회
SELECT *
FROM reply;