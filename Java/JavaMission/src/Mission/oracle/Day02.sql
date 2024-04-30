-- Active: 1709190551102@@127.0.0.1@1521@orcl@HR
-- 35. 테이블 EMPLOYEES 의 FIRST_NAME과 HIRE_DATE 를 검색하되 <예시>와 같이 날짜 형식을 지정하는 SQL 문을 작성
SELECT first_name AS 이름, TO_CHAR(hire_date,'YYYY-MM-DD (DY) HH:MI:SS') AS 입사일자
FROM EMPLOYEES;

-- 36. 테이블 EMPLOYEES 의 FIRST_NAME과 SALARY 를 검색하되 <예시>와 같이 날짜 형식을 지정하는 SQL 문을 작성
SELECT first_name AS 이름, TO_CHAR(SALARY, '$999,999,999') AS 급여
FROM EMPLOYEES;

-- 37. 문자형으로 주어진 데이터를 날짜형 데이터로 변환하는 SQL 문을 작성
SELECT '20240304' AS 문자, TO_DATE('20240304','YYYYMMDD') AS 날짜1
                       , TO_DATE('2024/03/04','YYYY/MM/DD') AS 날짜2
                       , TO_DATE('2024-03-04','YYYY-MM-DD') AS 날짜3
FROM DUAL;

-- 38. 문자형으로 주어진 데이터를 숫자형 데이터로 변환
SELECT '1,200,000' 문자, TO_NUMBER('1,200,000','999,999,999')
FROM DUAL;

-- 39.현재 날짜를 반환하는 SQL 문을 작성
SELECT TO_DATE(SYSDATE-1, 'YYYY/MM/DD') AS 어제
        ,TO_DATE(SYSDATE, 'YYYY/MM/DD') AS 오늘
        ,TO_DATE(SYSDATE+1, 'YYYY/MM/DD') AS 내일
FROM DUAL;

-- 40. 입사일자와 오늘 날짜를 계산하여 근무달수와 근속연수를 구하는 SQL 문
-- MONTHS_BETWEEN(A, B) : A~B 사이의 개월 수 차이를 반환, A는 최근 값을  써야함
SELECT FIRST_NAME 이름, TO_CHAR(HIRE_DATE, 'YYYY.MM.DD') 입사일자
            , TO_CHAR(SYSDATE, 'YYYY.MM.DD') 오늘
            , TRUNC(MONTHS_BETWEEN(SYSDATE ,HIRE_DATE)) || '개월' 근무달수
            , TRUNC(MONTHS_BETWEEN(SYSDATE ,HIRE_DATE) / 12) || '년' 근속연수
FROM EMPLOYEES;

-- 41. 6개월 후 날짜 출력
SELECT TO_CHAR(SYSDATE, 'YY/MM/DD') 오늘
        , TO_CHAR(ADD_MONTHS(SYSDATE,6),'YY/MM/DD') "6개월후"
FROM DUAL;

-- 42. 오늘 날짜와 오늘 이후 돌아오는 토요일의 날짜를 출력
-- 일 월 화 수 목 금 토
--  1  2  3  4  5  6  7
SELECT SYSDATE 오늘, NEXT_DAY(SYSDATE, 1) 다음일요일
, NEXT_DAY(SYSDATE, 2) 다음월요일
, NEXT_DAY(SYSDATE, 3) 다음화요일
, NEXT_DAY(SYSDATE, 4) 다음수요일
, NEXT_DAY(SYSDATE, 5) 다음목요일
, NEXT_DAY(SYSDATE, 6) 다음금요일
, NEXT_DAY(SYSDATE, 7) 다음 토요일
FROM DUAL;

-- 43. 오늘 날짜와 월초, 월말 일자를 구하는 SQL 문
-- 월초 : TRUCN(날짜, 'MM')
-- 월말 : LAST_DAY(날자)
SELECT  TRUNC(SYSDATE, 'MM') 월초
    , SYSDATE 오늘
    , LAST_DAY(SYSDATE) 월말
FROM DUAL;

-- 44. COMMISSION_PCT 를 중복없이 검색하되, NULL 이면 0으로 조회하고 내림차순으로 정렬
-- 실행순서 FROM -> WHERE -> GROUP BY -> HAVING -> SELECT -> ORDER BY
SELECT DISTINCT NVL(COMMISSION_PCT,0) "커미션(%)"
FROM EMPLOYEES
ORDER BY "커미션(%)" DESC;      -- 지정한 별칭을 사용해 출력 가능

-- 45.EMPLOYEES 의 FIRST_NAME, SALARY, COMMISSION_PCT 속성을 이용하여 SQL 작성
-- 최종급여 = 급여 +(급여*커미션)
-- 최종급여를 기준으로 내림차순 정렬
SELECT FIRST_NAME 이름
      , SALARY 급여
      , NVL(COMMISSION_PCT, 0) 커미션
      , NVL2(COMMISSION_PCT, SALARY+(SALARY*COMMISSION_PCT), SALARY) 최종급여
FROM EMPLOYEES
ORDER BY 최종급여 DESC;

-- 46. 테이블 EMPLOYEES 의 FIRST_NAME, DEPARTMENT_ID 속성을 이용하여 SQL 작성
-- DECODE 함수 이용
-- 부서명은 HR계정의 DEPARTMENTS 테이블 이용
SELECT FIRST_NAME 이름
        , DECODE( department_id, 10, 'Administration',
        20, 'Marketing',
        30, 'Purchasing',
        40, 'Human Resources',
        50, 'Shipping',
        60, 'IT',
        70, 'Public Relations',
        80, 'Sales',
        90, 'Executive',
        100, 'Finance' ) 부서
FROM EMPLOYEES;

-- 47. FIRST_NAME, DEPARTMENT_ID 속성을 이용하여 46과 같이 작성
-- CASE 함수 이용
SELECT first_name 이름
      ,CASE WHEN department_id = 10 THEN 'Administration'
    WHEN department_id = 20 THEN 'Marketing'                 
    WHEN department_id = 30 THEN 'Purchasing'                 
    WHEN department_id = 40 THEN 'Human Resources'                 
    WHEN department_id = 50 THEN 'Shipping'                 
    WHEN department_id = 60 THEN 'IT'                 
    WHEN department_id = 70 THEN 'Public Relations'                 
    WHEN department_id = 80 THEN 'Sales'                 
    WHEN department_id = 90 THEN 'Executive'                 
    WHEN department_id = 100 THEN 'Finance'
    ELSE '부서없음'   
      END 부서
FROM employees;

-- 48.테이블 EMPLOYEES 의 사원 수를 구하는 SQL 문을 작성
SELECT COUNT(*) AS 사원수
FROM EMPLOYEES;
SELECT COUNT(COMMISSION_PCT) "성과급이 있는 사원수"
FROM EMPLOYEES

-- 49. EMPLOYEES 의 최고급여, 최저급여를 구하는 SQL 문
SELECT MAX(SALARY) 최고급여, MIN(SALARY) 최저급여
FROM EMPLOYEES;

-- 50. EMPLOYEES 의 급여합계, 급여평균을 구하는 SQL 문
SELECT SUM(SALARY) 급여합계, ROUND(AVG(SALARY),2) 급여평균
FROM EMPLOYEES;

-- 51.EMPLOYEES 의 급여표준편자와 급여분산을 구하는 SQL 문
SELECT ROUND(STDDEV(SALARY),2) 급여표준편차, ROUND(VARIANCE(SALARY),2) 급여분산
FROM EMPLOYEES;

-- 52. TABLE 기술서를 참고하여 MS_STUDENT 테이블을 생성
CREATE TABLE MS_STUDENT (
  ST_NO NUMBER NOT NULL 
, NAME VARCHAR2(20) NOT NULL 
, CTZ_NO CHAR(14) NOT NULL 
, EMAIL VARCHAR2(100) NOT NULL 
, ADDRESSS VARCHAR2(1000) 
, DEPT_NO NUMBER NOT NULL 
, MJ_NO NUMBER NOT NULL 
, REG_DATE DATE DEFAULT SYSDATE NOT NULL 
, UPD_DATE DATE DEFAULT SYSDATE NOT NULL 
, ETC VARCHAR2(20) DEFAULT '없음' 
, CONSTRAINT MS_STUDENT_PK PRIMARY KEY  ( ST_NO ) ENABLE
);
  -- UQ(고유키) 추가
ALTER TABLE MS_STUDENT ADD CONSTRAINT MS_STUDENT_UK1 UNIQUE  ( EMAIL ) ENABLE;

COMMENT ON TABLE MS_STUDENT IS '학생들의 정보를 관리한다.';
COMMENT ON COLUMN MS_STUDENT.ST_NO IS '학생 번호';
COMMENT ON COLUMN MS_STUDENT.NAME IS '이름';
COMMENT ON COLUMN MS_STUDENT.CTZ_NO IS '주민번호';
COMMENT ON COLUMN MS_STUDENT.EMAIL IS '이메일';
COMMENT ON COLUMN MS_STUDENT.ADDRESSS IS '주소';
COMMENT ON COLUMN MS_STUDENT.DEPT_NO IS '부서번호';
COMMENT ON COLUMN MS_STUDENT.MJ_NO IS '전공번호';
COMMENT ON COLUMN MS_STUDENT.REG_DATE IS '등록일자';
COMMENT ON COLUMN MS_STUDENT.UPD_DATE IS '수정일자';
COMMENT ON COLUMN MS_STUDENT.ETC IS '특이사항';

-- 53. 테이블에 성별, 재적, 입학일자, 졸업일자 속성 추가
ALTER TABLE MS_STUDENT ADD GENDER CHAR(6) DEFAULT '기타' NOT NULL;
COMMENT ON COLUMN MS_STUDENT.GENDER IS '성별';
ALTER TABLE MS_STUDENT ADD STATUS VARCHAR2(10) DEFAULT '대기' NOT NULL;
COMMENT ON COLUMN MS_STUDENT.STATUS IS '재적';
ALTER TABLE MS_STUDENT ADD ADM_DATE DATE;
COMMENT ON COLUMN MS_STUDENT.ADM_DATE IS '입학일자';
ALTER TABLE MS_STUDENT ADD GRD_DATE DATE;
COMMENT ON COLUMN MS_STUDENT.GRD_DATE IS '졸업일자';

-- 54. 주민번호 속성을 생년원일 속성으로 수정하는 SQL
-- CTZ_NO -> BIRTH 이름 변경 후 DATE으로 변경, 설명 변경
ALTER TABLE MS_STUDENT RENAME COLUMN CTZ_NO TO BIRTH;
ALTER TABLE MS_STUDENT MODIFY BIRTH DATE;
COMMENT ON COLUMN MS_STUDENT.BIRTH IS '생년월일';

-- 55. 학부 번호 속성을 삭제하는 SQL문
ALTER TABLE MS_STUDENT DROP COLUMN DEPT_NO;

-- 56. MS_STUDENT 테이블 삭제
DROP TABLE MS_STUDENT;

-- 57. MS_STUDENT 테이블 생성
CREATE TABLE MS_STUDENT (
     ST_NO      NUMBER          NOT NULL 
    ,NAME       VARCHAR2(20)    NOT NULL
    ,BIRTH      DATE            NOT NULL
    ,EMAIL      VARCHAR2(100)   NOT NULL
    ,ADDRESS    VARCHAR2(1000)  NULL
    ,MJ_NO      NUMBER          NOT NULL
    ,GENDER     CHAR(6)         DEFAULT '기타'    NOT NULL
    ,STATUS     VARCHAR2(10)    DEFAULT '대기'    NOT NULL
    ,ADM_DATE   DATE    NULL
    ,GRD_DATE   DATE    NULL
    ,REG_DATE   DATE    DEFAULT sysdate NOT NULL
    ,UPD_DATE   DATE    DEFAULT sysdate NOT NULL
    ,ETC        VARCHAR2(1000)  DEFAULT '없음' NULL
    ,
    CONSTRAINT MS_STUDENT_PK PRIMARY KEY(ST_NO) ENABLE
);
-- UQ (고유키) 추가
ALTER TABLE MS_STUDENT ADD CONSTRAINT MS_STUDENT_UK1 UNIQUE ( EMAIL ) ENABLE;
COMMENT ON TABLE MS_STUDENT IS '학생들의 정보를 관리한다.';
COMMENT ON COLUMN MS_STUDENT.ST_NO IS '학생 번호';
COMMENT ON COLUMN MS_STUDENT.NAME IS '이름';
COMMENT ON COLUMN MS_STUDENT.BIRTH IS '생년월일';
COMMENT ON COLUMN MS_STUDENT.EMAIL IS '이메일';
COMMENT ON COLUMN MS_STUDENT.ADDRESS IS '주소';
COMMENT ON COLUMN MS_STUDENT.MJ_NO IS '전공번호';
COMMENT ON COLUMN MS_STUDENT.GENDER IS '성별';
COMMENT ON COLUMN MS_STUDENT.STATUS IS '재적';
COMMENT ON COLUMN MS_STUDENT.ADM_DATE IS '입학일자';
COMMENT ON COLUMN MS_STUDENT.GRD_DATE IS '졸업일자';
COMMENT ON COLUMN MS_STUDENT.REG_DATE IS '등록일자';
COMMENT ON COLUMN MS_STUDENT.UPD_DATE IS '수정일자';
COMMENT ON COLUMN MS_STUDENT.ETC IS '특이사항';

-- 58. 데이터 삽입
ALTER TABLE MS_STUDENT MODIFY MJ_NO CHAR(4);

INSERT INTO MS_STUDENT (ST_NO, NAME, BIRTH, EMAIL, ADDRESS, MJ_NO, GENDER, STATUS, ADM_DATE,
                        GRD_DATE, REG_DATE, UPD_DATE, ETC
                        )
VALUES ( '20180001', '최서아', '1999/10/05', 'csa@univ.ac.kr', '서울', 'I01',
         '여', '재학', '2018/03/01', NULL, sysdate, sysdate, NULL );

SELECT * FROM MS_STUDENT WHERE ST_NO IN ('20160001','20150010','20130007','20110002');
SELECT * FROM MS_STUDENT;
-- 59. 데이터 수정
UPDATE MS_STUDENT 
SET ADDRESS='서울', STATUS='휴학'
WHERE ST_NO='20160001';
UPDATE MS_STUDENT 
SET ADDRESS='서울', STATUS='졸업', GRD_DATE='20200220', UPD_DATE='20200101', ETC='수석'
WHERE ST_NO='20150010';
UPDATE MS_STUDENT 
SET STATUS='졸업', GRD_DATE='20200220', UPD_DATE='20200101'
WHERE ST_NO='20130007';
UPDATE MS_STUDENT 
SET STATUS='퇴학', UPD_DATE='20130210', ETC='자진 퇴학'
WHERE ST_NO='20110002';

-- 60. 데이터 삭제
DELETE FROM 
WHERE ST_NO='20110002';

-- 61. 모든 데이터 조회
SELECT *
FROM MS_STUDENT;

-- 62. MS_STUDENT 테이블의 모든 속성을 조회하여 MS_STUDENT_BACK 테이블을 생성
CREATE TABLE MS_STUDENT_BACK AS SELECT * FROM MS_STUDENT;

-- 63. MS_STUDENT 테이블의 튜플을 삭제
-- 데이터 삭제 및 내부 구조 삭제 : TRUNCATE
-- 구조 삭제 : DROP
-- 데이터 삭제 : DELETE
DELETE FROM MS_STUDENT;

-- 64. MS_STUDENT_BACK 테이블의 모든 속성을 조회하여 MS_STUDENT 테이블에 삽입
INSERT INTO MS_STUDENT
SELECT * FROM MS_STUDENT_BACK;

-- 65. 성별 속성이 (‘여’, ‘남‘, ‘기타‘ ) 값만 입력가능하도록 하는 제약조건을 추가
ALTER TABLE MS_STUDENT
ADD CONSTRAINT MS_STUDENT_GENDER_CHECK
CHECK (GENDER IN ('여','남','기타'));

-- * 조건으로 지정한 값이 아닌 다른 값으로 입력이나 수정
-- 체크 제약조건에 위배되었다는 에러 발생 -> 도메인 무결성 보장
UPDATE MS_STUDENT
SET GENDER='???';


-- 66~69 과제
-- 66. 테이블 생성
CREATE TABLE MS_USER(
    USER_NO NUMBER NOT NULL PRIMARY KEY,
    USER_ID VARCHAR2(100) NOT NULL UNIQUE,
    USER_PW VARCHAR2(200) NOT NULL,
    USER_NAME VARCHAR2(50) NOT NULL,
    BIRTH DATE NOT NULL,
    TEL VARCHAR2(20) NOT NULL UNIQUE,
    ADDRESS VARCHAR2(200) NULL
    REG_DATE DATE DEFAULT SYSDATE NOT NULL,
    UPD_DATE DATE DEFAULT SYSDATE NOT NULL
);
COMMENT ON COLUMN MS_USER IS '커뮤니티 회원의 정보를 관리한다'
COMMENT ON COLUMN MS_USER.USER_NO IS '회원정보'
COMMENT ON COLUMN MS_USER.USER_ID IS '아이디'
COMMENT ON COLUMN MS_USER.USER_PW IS '비밀번호'
COMMENT ON COLUMN MS_USER.USER_NAME IS '이름'
COMMENT ON COLUMN MS_USER.BIRTH IS '생년월일'
COMMENT ON COLUMN MS_USER.TEL IS '전화번호'
COMMENT ON COLUMN MS_USER.ADDRESS IS '주소'
COMMENT ON COLUMN MS_USER.REG_DATE IS '등록일자'
COMMENT ON COLUMN MS_USER.UPD_DATE IS '수정일자'


-- 덤프파일 IMPORT
-- IMP USERID=관리자계정/비밀번호 FILE=덤프 경로 fromuser=덤프소유계정 touser= 임포트할 계정
IMP USERID=SYSTEM/1234 FILE=C:\JSLEE\Oracle\joeun.dmp fromuser=joeun touser=joeun

-- 71. joeun 계정의 데이터를 덤프파일로 export하기
exp userid=joeun/1234 file=C:\JSLEE\Oracle\community.dmp log==C:\JSLEE\Oracle\community1.dmp