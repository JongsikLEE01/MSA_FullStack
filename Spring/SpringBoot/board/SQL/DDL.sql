-- 게시판
CREATE TABLE joeun.`board` (
  `no` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `content` text,
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `views` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`no`)
) COMMENT='게시판';

-- 게시판 샘플 데이터
INSERT INTO joeun.board(title, writer, content)
VALUES ('제목01', '작성자01', '내용01'),
	   ('제목02', '작성자02', '내용02'),
       ('제목03', '작성자03', '내용03');
       
-- 파일
CREATE TABLE joeun.`file` (
  `no` int NOT NULL AUTO_INCREMENT,
  `parent_table` varchar(45) NOT NULL,
  `parent_no` int NOT NULL,
  `file_name` text NOT NULL,
  `origin_name` text,
  `file_path` text NOT NULL,
  `file_size` int NOT NULL DEFAULT '0',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `upd_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `file_code` int NOT NULL DEFAULT '0',         
  PRIMARY KEY (`no`)
) COMMENT='파일';