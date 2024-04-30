-- Active: 1709190551102@@127.0.0.1@1521@orcl@HR
-- 1. 시스템 접속 SQL
conn system/1234;

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
-- dual : 산술연산, 함수 결과를 확인하는 임시 테이블
-- CEIL : 크거나 같은 정수 중 가장 큰수
SELECT CEIL(12.45), CEIL(-12.45)
FROM DUAL;

-- 22. 12.55와 -12.55 보다 작거나 같은 정수 중 가장 큰 수를 계산하는 SQL
-- FLOOR : 작거나 같은 정수 중 가장 큰 수를 계산
SELECT FLOOR(12.55), FLOOR(-12.55)
FROM DUAL;

-- 23. 소문제에 제시된 수와 자리 수를 이용하여 반올림하는 SQL문을 작성
-- ROUND(값, 자리수) : 지정한 값을 해당 자리수에서 반올림(-1의 경우 일의 자리)
-- 1) 0.54 를 소수점 아래 첫째 자리에서 반올림하시오.
SELECT ROUND(0.54,0)
FROM dual;
-- 2) 0.54 를 소수점 아래 둘째 자리에서 반올림하시오.
SELECT ROUND(0.54,1)
FROM dual;
-- 3) 125.67 을 일의 자리에서 반올림하시오.
SELECT ROUND(125.67,-1)
FROM dual;
-- 4) 125.67 을 십의 자리에서 반올림하시오.
SELECT ROUND(125.67,-2)
FROM dual;

-- 24.소문제에 제시된 두 수를 이용하여 나머지를 구하는 SQL문
-- MOD(A,B) : B를 A로 나눈 나머지를 반환
-- 1) 3을 8로 나눈 나머지를 구하시오.
SELECT MOD(3,8)
FROM dual;
-- 2)30을 4로 나눈 나머지를 구하시오.
SELECT MOD(30,4)
FROM dual;

-- 25. 소문제에 제시된 두 수를 이용하여 제곱수를 구하는 SQL문
-- POWER(a, b) : A의 B 제곱을 구하는 함수
-- 1) 2의 10제곱
select power(2,10)
FROM dual;
-- 2) 2의 31제곱
select power(2,31)
FROM dual;

-- 26. 소문제에 제시된 두 수를 이용하여 제곱근를 구하는 SQL문
-- sqrt(값) : 값의 제곱근을 구하는 함수
-- 1) 2의 제곱근
SELECT sqrt(2)
FROM DUAL;
-- 2) 100의 제곱근
SELECT sqrt(100)
FROM DUAL;

-- 27. 각 소문제에 제시된 수와 자리 수를 이용하여 해당 수를 절삭하는 SQL문
-- TRUNC(값, 위치) : 해당위치에서 절삭하는 함수
-- 1) 527425.1234 을 소수점 아래 첫째 자리에서 절삭하시오.
SELECT TRUNC(527425.1234, 1)
FROM DUAL;
-- 2) 527425.1234 을 소수점 아래 둘째 자리에서 절삭하시오.
SELECT TRUNC(527425.1234, 2)
FROM DUAL;
-- 3) 527425.1234 을 일의 자리에서 절삭하시오.
SELECT TRUNC(527425.1234, -1)
FROM DUAL;
-- 4) 527425.1234 을 십의 자리에서 절삭하시오.
SELECT TRUNC(527425.1234, -2)
FROM DUAL;

-- 28. 소문제에 제시된 수를 이용하여 절댓값을 구하는 SQL문을 작성하시오.
-- ABS(값) : 절대값을 구하는 함수
-- 1) -20 절대값
SELECT ABS(-20)
FROM DUAL;
-- 1) -12.456 절대값
SELECT ABS(-12.456)
FROM DUAL;

-- 29. 문자열 대문자, 소문자, 첫글자만 대문자로 변환
-- UPPER(값) : 모두 대문자 변환
SELECT UPPER('AlOhA WoRlD~!')
FROM DUAL;
-- LOWER(값) : 모두 소문자 변환
SELECT LOWER('AlOhA WoRlD~!')
FROM DUAL;
-- INITCAP(값) : 첫글자만 대문자로 변환
SELECT INITCAP('AlOhA WoRlD~!')
FROM DUAL;

-- 30. 문자열의 글자수와 바이트 수를 출력
-- LENGTH(값) : 문자열의 길이를 출력
-- LENGTHB(값) : 문자열의 바이트 수를 출력
SELECT LENGTH('ALOHA WORLD'), LENGTHB('ALOHA WORLD')
FROM DUAL;
SELECT LENGTH('알로하 월드'), LENGTHB('알로하 월드')
FROM DUAL;

-- 31. 두 문자열 병합 및 출력
-- CONCAT(A, B), A || B : A와 B를 병합해서 출력
SELECT CONCAT('ALOHA','WORLD')
FROM DUAL;
SELECT 'ALOHA' || 'WORLD'
FROM DUAL;

-- 32. 주어진 문자열의 일부만 출력하는 SQL
-- SUBSTR(문자열, 시작번호, 글자수) : 문자를 시작번호부터 지정한 문자까지 추출해서 반환
SELECT SUBSTR('www.alohacampus.com', 1,3)
          , SUBSTR('www.alohacampus.com', 5,11)
          , SUBSTR('www.alohacampus.com',-3, 3)
FROM DUAL;
SELECT SUBSTR('www.알로하캠퍼스.com', 0,3) AS "1"
     , SUBSTR('www.알로하캠퍼스.com', 5,6) AS "2"
     , SUBSTR('www.알로하캠퍼스.com', 12,3) AS "3"
FROM DUAL;
-- 33. 문자열의 특정 문자 위치를 구하는 SQL
-- INSTR(문자열, 문자, 시작번호, 순서) : 지정한 시작번호부터 문자를 찾아 지정한 순서에 있는
-- 문자의 위치를 반환
SELECT INSTR('ALOHACAMPUS', 'A', 1, 1) AS "1 A"
     , INSTR('ALOHACAMPUS', 'A', 1, 2) AS "2 A"
     , INSTR('ALOHACAMPUS', 'A', 1, 3) AS "3 A"
FROM DUAL;

-- 34. 대상 문자열을 왼쪽/오른쪽에 출력하고 빈공간을 특정 문자로 채우는 SQL문을 작성하시오.
-- LPAD(문자열, 총 길이, 문자) : 지정한 길이에서 왼쪽 빈 공간을 특정 문자로 채우는 함수
-- RPAD(문자열, 총 길이, 문자) : 지정한 길이에서 오른쪽 빈 공간을 특정 문자로 채우는 함수
SELECT LPAD('ALOHACAMPUS', 19, '#') AS "왼쪽"
     , RPAD('ALOHACAMPUS', 19, '#') AS "오른쪽"
FROM DUAL;
SELECT RPAD(SUBSTR('123456-1234567',1,8), 14, '*') -- 주민번호
FROM DUAL;