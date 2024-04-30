-- 1. 시스템 접속 SQL
conn system/1234

-- 2. hr 계정이 있는지 확인하는 sql
select *
from all_users
where username='HR';
-- hr이 있을때, 계정 잠금 해제
alter user HR account unlock;
-- hr이 없을때 계정 생성
-- c## 없이 계정 생성
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
-- CREATE USER 계정명 IDENTIFIED BY 비밀번호;
CREATE USER HR IDENTIFIED BY 123456;
-- 테이블 스페이스 변경
ALTER USER HR DEFAULT TABLESPACE users;
-- 계정이 사용할 수 있는 용량 설정 (무한대)
ALTER USER HR QUOTA UNLIMITED ON users;
-- 계정에 권한 부여 
GRANT connect, resource TO HR;
-- 계정 삭제
drop user HR cascade;

-- 3. employees 테이블 구조 조회 명령
desc employees;
-- employees 테이블 사원번호, 이름 조회
select employee_id, first_name
from employees;

-- 4. 사원번호, 이름. 성, 여, 이메일, 전화번호, 입사일자, 급여로 조회
-- AS (alias) : 출력되는 컬럼의 별명을을 생성
-- as 사원번호
-- as "사원 번호" -> 띄어쓰기
-- as '사원번호'    (에러)
select employee_id as 사원번호
     , first_name as 이름
     , last_name as 성
     , email as 이메일
     , phone_number as 전화번호
     , hire_date as 입사일자
     , salary as 급여
from employees;

-- 모든 컬럼 조회
select *
from employees;

-- 5. job_id 중복 제거하고 조회
select distinct job_id
from employees;

-- 6. salary가 6000초과인 사원 조회
select *
from employees
where salary > 6000;

-- 7. salary가 10000인 사원 조회
select *
from employees
where salary = 10000;

-- 8. salary를 기준으로 내림차, first_name을 기준으로 오름차순 정렬 후 조회 
-- asc 오름차순, desc 내림차순
-- order by 테이블명 asc | desc
select *
from employees
order by salary desc, first_name asc;

-- 9. FI_ACCOUNT 이거나 IT_PROG 인 사원의 모든 컬럼을 조회
select *
from employees 
where job_id = 'FI_ACCOUNT' or  job_id = 'IT_PROG';

-- 10. FI_ACCOUNT 이거나 IT_PROG 인 사원의 모든 컬럼을 조회(IN 연산자 사용)
select *
from employees 
where job_id IN ('FI_ACCOUNT', 'IT_PROG');

-- 11. FI_ACCOUNT 이거나 IT_PROG가 아닌 사원의 모든 컬럼을 조회(IN 연산자 사용)
select *
from employees 
where job_id NOT IN ('FI_ACCOUNT', 'IT_PROG');

-- 12. JOB_ID가 ‘IT_PROG’ 이면서 SALARY 가 6000 이상인 사원의 모든 컬럼을 조회
SELECT *
FROM employees
WHERE JOB_ID = 'IT_PROG' AND SALARY >= 6000;

-- 13.  FIRST_NAME이 ‘S’로 시작하는 사원의 모든 컬럼을 조회
-- LIKE -> '_' : 한글자 대체 | '%' : 여러 글자 대체
SELECT *
FROM employees
WHERE FIRST_NAME LIKE 'S%';

-- 14. FIRST_NAME 이 ‘s’로 끝나는 사원의 모든 컬럼을 조회
SELECT *
FROM employees
WHERE FIRST_NAME LIKE '%s';

-- 15.  FIRST_NAME 에 ‘s’가 포함되는 사원의 모든 컬럼을 조회
SELECT *
FROM employees
WHERE FIRST_NAME LIKE '%s%';

-- 16.  FIRST_NAME 이 5글자인 사원의 모든 컬럼을 조회
SELECT *
FROM employees
WHERE FIRST_NAME LIKE '_____';
-- 16-1
SELECT *
FROM employees
WHERE LENGTH(FIRST_NAME)=5;  

-- 17. COMMISSION_PCT가 NULL 인 사원의 모든 컬럼을 조회
-- IS NULL : NULL 조회
-- IS NOT NULL : NULL이 아닌 것을 조회
SELECT *
FROM employees
WHERE COMMISSION_PCT IS NULL;

-- 18. COMMISSION_PCT가 NULL이 아닌 사원의 모든 컬럼을 조회
SELECT *
FROM employees
WHERE COMMISSION_PCT IS NOT NULL;

-- 19. 사원의 HIRE_DATE가 04년 이상인 모든 컬럼을 조회
-- TO_DATE : 날짜로 변경
SELECT *
FROM employees
WHERE hire_date >= TO_DATE('04-01-01', 'YY-MM-DD'); 

-- 19-1. 암시적 형변환
SELECT *
FROM employees
WHERE hire_date >= '01/JAN/04'  --데이터의 월값이 숫자일 경우 04/01/01
ORDER BY hire_date ASC;

-- 20. HIRE_DATE가 04년도부터 05년도인 모든 컬럼을 조회하는 SQL 문을 작성
SELECT *
FROM employees
WHERE hire_date BETWEEN TO_DATE('04-01-01', 'YY-MM-DD') AND TO_DATE('05-12-31', 'YY-MM-DD');

-- 21. 12.45, -12.45 보다 크거나 같은 정수 중 제일 작은 수를 계산하는 SQL
-- CEIL : 크거나 같은 정수 중 가장 큰수
SELECT CEIL(12.45), CEIL(-12.45)
FROM DUAL;

-- 22. 12.55와 -12.55 보다 작거나 같은 정수 중 가장 큰 수를 계산하는 SQL
SELECT CEIL(12.55), CEIL(-12.55)
FROM DUAL;

-- 23. 