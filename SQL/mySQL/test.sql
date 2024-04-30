 CREATE TABLE STUDY.수강(
	학과 CHAR(10), 
	과목 CHAR(10), 
	교수 CHAR(10), 
	학점 INT );
-- 테이블 생성
CREATE TABLE STUDY.학생
 ( 학번 INT, 
 성명 CHAR(5) NOT NULL, 
 학과 CHAR(10), 
 학점 CHAR(1), 
 PRIMARY KEY (학번));
 
 -- 데이터 추가
INSERT INTO study.학생
VALUES ( 20190101, '홍길동', '컴퓨터공학과', 'A');

-- 테이블 검색
SELECT * FROM study.학생;
SELECT * FROM study.수강;

-- 테이블 생성
CREATE TABLE 수강(
 학과 CHAR(10), 
 과목 CHAR(10), 
 교수 CHAR(10), 
 학점 INT );
 
-- 테이블 수정
ALTER TABLE study.학생
ADD 학년 INT ; 

-- 데이터 수정
UPDATE study.학생
SET 학년 = 1
WHERE 성명 = '홍길동';

-- 세이프 모드 종료
SET SQL_SAFE_UPDATES = 0;

-- 1번
select 학번, 성명, 학과, 학점, 학년
from 학생
where 성명 = '홍길동';

-- 2
select *
from 학생
where 학년 >= 2;

-- 3번
SELECT *
FROM 학생
order by 학점, 학년 desc;		-- ASC 오름차, DESC 내림차

-- 4
select * 
FROM 학생
where 학과 IN ('컴퓨터공학과','경영학과');