-- 댓글 테이블 생성
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

-- 데이터 삭제
TRUNCATE Table reply;

-- 테이블 삭제
DROP Table reply;

-- 댓글 샘플 데이터
INSERT INTO reply(board_no, parent_no, writer, content)
VALUES (11,1, "테스트1", "테스트1")
     ,(11,2, "테스트2", "테스트2")
     ,(11,3, "테스트3", "테스트3")
     ,(11,4, "테스트4", "테스트4");

-- 댓글 테이블에 외래키 추가
AFTER TABLE reply
ADD CONSTRAINT FK_REPLY_BOARD_NO
FOREIGN KEY (board_no)
REFERENCES board(no)
ON DELETE CASCADE;   -- 게시글 삭제 시, 종속된 댓글 삭제

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
    PRIMARY KEY(no),
    -- 외래키 지정
    FOREIGN KEY (board_no) REFERENCES board(no) 
                            ON DELETE CASCADE   -- CASCADE, RESTRICT, SET NULL 
                            ON UPDATE CASCADE
);
