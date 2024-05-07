-- 게시판
CREATE TABLE joeun.`board` (
  `no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `content` text,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `views` int NOT NULL DEFAULT '0',
  `likes` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`)
) COMMENT='게시판';

DROP Table board;

-- 게시판 샘플 데이터
INSERT INTO joeun.board(title, writer, content)
VALUES ('제목01', '작성자01', '내용01'),
	   ('제목02', '작성자02', '내용02'),
	   ('제목03', '작성자03', '내용03'),
	   ('제목04', '작성자04', '내용04'),
     ('제목05', '작성자05', '내용05');
INSERT INTO joeun.board(title, writer, content)
SELECT title, writer, content
FROM joeun.board;

-- 좋아요 추가
ALTER TABLE board ADD COLUMN likes INT;

-- 게시글 데이터 삭제
truncate board;
-- 게시글 조회
select * from joeun.board;

-- board, file 테이블 조인 조회
SELECT b.*
      , f.no file_no
      , f.file_name
      , f.file_path
      , f.file_code
FROM joeun.board b LEFT JOIN (  SELECT *
                                FROM joeun.file
                                WHERE parent_table = "board"
                                    AND file_code = 1       #file_code = 1 (대표 썸네일) 
                            ) f
                ON (b.no = f.parent_no)
-- WHERE f.parent_table = "board"
-- AND f.file_code = 1;

-- 게시글 목록 페이징
SELECT *
FROM joeun.board
LIMIT 0, 10;
-- 마이바티스 사용시
SELECT *
FROM joeun.board
LIMIT #{index}, #{rows};