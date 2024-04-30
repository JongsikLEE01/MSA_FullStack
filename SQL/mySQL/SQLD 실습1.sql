-- 1번
select 학번, 성명, 학과, 학점, 학년
from 학생
where 성명 = '홍길동';

-- 2
select *
from 학생
where 학년 >= 2;

-- 3번 테이블 정렬 조회
SELECT *
FROM 학생
order by 학점, 학년 desc;		-- ASC 오름차, DESC 내림차

-- 4
select * 
FROM 학생
where 학과 IN ('컴퓨터공학과','경영학과');
select * 
FROM 학생
where 학과='컴퓨터공학과' OR 학과='경영학과';

-- 5 그룹화
SELECT 학과, COUNT(*) AS "A학점자 수"
from 학생
WHERE 학점 = "A"
group by 학과;

-- 6 그룹화, 조건
SELECT 학과, COUNT(*) AS 'C학점자 수'
FROM 학생
WHERE 학점 = 'C'
GROUP BY 학과 HAVING COUNT(*) >= 3;

-- 7
SELECT * FROM 학생
WHERE 성명 LIKE '김%';

-- 8  조인
SELECT * FROM 학생
JOIN 수강
ON 학생.학과 = 수강.학과;
SELECT * FROM 학생, 수강
WHERE 학생.학과 = 수강.학과;

-- 9 사이값 조회
SELECT * FROM 학생
WHERE 학점 BETWEEN 'A' AND 'B' ;

-- 10 학점이 A인 학생을 뷰로 생성(뷰는 보안 유지를 위해 사용)
CREATE VIEW A학생
AS SELECT * FROM 학생
WHERE 학점 = 'A';
