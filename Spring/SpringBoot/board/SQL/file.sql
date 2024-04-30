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

-- 파일 데이터 삭제
truncate joeun.file;

-- 파일 조회
select * from joeun.file;