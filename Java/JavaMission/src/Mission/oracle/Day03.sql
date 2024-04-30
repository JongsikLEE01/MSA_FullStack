-- 72.
-- MS_BOARD 의 WRITER 속성을 NUMBER 타입으로 변경하고
-- MS_USER 의 USER_NO 를 참조하는 외래키로 지정하시오.

-- 1)
-- 데이터 타입 변경
-- ALTER TABLE 테이블명 MODIFY 컬럼명 데이터타입;
ALTER TABLE MS_BOARD MODIFY WRITER NUMBER;
-- 외래키 지정
-- ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명
-- FOREIGN KEY (외래키컬럼) REFERENCES 참조테이블(기본키);
ALTER TABLE MS_BOARD ADD CONSTRAINT MS_BOARD_WRITER_FK
FOREIGN KEY (WRITER) REFERENCES MS_USER(USER_NO);

-- 2) 외래키 : MS_FILE (BOARD_NO)  ---->  MS_BOARD (BOARD_NO)
ALTER TABLE MS_FILE ADD CONSTRAINT MS_FILE_BOARD_NO_FK
FOREIGN KEY (BOARD_NO) REFERENCES MS_BOARD(BOARD_NO);

-- 3) 외래키 : MS_REPLY (BOARD_NO)  ---->  MS_BOARD (BOARD_NO)
ALTER TABLE MS_REPLY ADD CONSTRAINT MS_REPLY_BOARD_NO_FK
FOREIGN KEY (BOARD_NO) REFERENCES MS_BOARD(BOARD_NO);
-- 제약 조건 삭제
-- ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명;

-- 73. MU_USER 속성 추가
ALTER TABLE MS_USER ADD CTZ_NO CHAR(14) NOT NULL UNIQUE;
ALTER TABLE MS_USER ADD GENDER CHAR(6) NOT NULL;

COMMENT ON COLUMN MS_USER.CTZ_NO IS '주민번호';
COMMENT ON COLUMN MS_USER.GENDER IS '성별';

-- 74. MS_USER 의 GENDER 속성이 (‘여‘, ‘남‘, ‘기타‘) 값을 갖도록 하는 제약조건을 추가
ALTER TABLE MS_USER ADD CONSTRAINT MS_USER_GENDER_CHECK
CHECK (GENDER IN ('여','남','기타'));

ALTER TABLE MS_USER DROP CONSTRAINT MS_USER_GENDER_CHECK;

-- 75. MS_FILE의 EXT 속성에 확장자 속성을 추가
ALTER TABLE MS_FILE ADD EXT VARCHAR2(10) NULL;
COMMENT ON COLUMN MS_FILE.EXT IS '확장자';

-- 76.
-- 테이블 MS_FILE 의 FILE_NAME 속성에서 확장자를 추출하여 
-- EXT 속성에 UPDATE 하는 SQL 문을 작성하시오. 
MERGE INTO MS_FILE T      -- 대상 테이블 지정
-- 사용할 데이터 자원을 지정
USING ( SELECT FILE_NO, FILE_NAME FROM MS_FILE ) F 
-- ON (UPDATE 조건)
ON (T.FILE_NO = F.FILE_NO)
-- 매치조건
WHEN MATCHED THEN
    -- 이미지 파일 확장자를 추출하여 수정
    UPDATE SET T.EXT = SUBSTR( F.FILE_NAME, INSTR(F.FILE_NAME, '.', -1) + 1 )
    -- 이미지 확장자(jpeg, jpg, gif, png, webp)가 아니면 삭제
    DELETE WHERE LOWER( SUBSTR( F.FILE_NAME, INSTR(F.FILE_NAME, '.', -1) + 1 ) )
                 NOT IN ('jpeg', 'jpg', 'gif', 'png', 'webp');
-- 매치가 안됐을 때 조건
-- WHEN NOT MATCHED THEN

-- 유저 추가
INSERT INTO MS_USER(
                USER_NO, USER_ID, USER_PW, USER_NAME, BIRTH,
                TEL, ADDRESS, REG_DATE, UPD_DATE,
                CTZ_NO, GENDER )
VALUES ( 1, 'ALOHA', '123456', '김조은', TO_DATE('2003/01/01', 'YYYY/MM/DD'),
         '010-1234-1234', '부평', sysdate, sysdate,
         '030101-3334444', '기타' );
-- 게시글 추가
INSERT INTO MS_BOARD (
                BOARD_NO, TITLE, CONTENT, WRITER, HIT, LIKE_CNT,
                DEL_YN, DEL_DATE, REG_DATE, UPD_DATE )
VALUES ( 1, '제목', '내용', 1, 0, 0, 'N', NULL, sysdate, sysdate);
-- 파일 추가
INSERT INTO MS_FILE ( 
            FILE_NO, BOARD_NO, FILE_NAME, FILE_DATA, REG_DATE, UPD_DATE, EXT )
VALUES (1, 1, '강아지.png', '123', sysdate, sysdate, 'jpg' );
INSERT INTO MS_FILE ( 
            FILE_NO, BOARD_NO, FILE_NAME, FILE_DATA, REG_DATE, UPD_DATE, EXT )
VALUES (2, 1, 'main.html', '123', sysdate, sysdate, 'jpg' );

-- 77. MS_FILE 의 EXT 속성이 (‘jpg’, ‘jpeg’, ‘gif’, ‘png’) 값을 갖도록 하는 제약조건을 추가
ALTER TABLE MS_FILE ADD CONSTRAINT MS_FILE_EXT_CHECK
CHECK (EXT IN ('jpg', 'jpeg', 'gif', 'png'));

ALTER TABLE MS_FILE DROP CONSTRAINT MS_FILE_EXT_CHECK;

-- 78. MS_USER,BOARD, FILE, REPLY 모든 행 삭제
-- DDL(데이터 정의어) : TRUNCATE
--                   -> 모든 행을 삭제, 삭제된 데이터를 되돌릴 수 없음
-- DML(데이터 조작어) : DELETE 
--                   -> 한 행을 단위로 데이터를 삭제
--  WHERE 조건을 기준으로 일부만 삭제도 가능
--  COMMIT, ROLLBACK을 이용해 변경사항을 적용하거나 되돌릴 수 있음
DELETE TABLE MS_USER;
DELETE TABLE MS_FILE;
DELETE TABLE MS_BOARD;
DELETE TABLE MS_REPLY;
SELECT * FROM MS_BOARD;
SELECT * FROM MS_FILE;
SELECT * FROM MS_USER;
SELECT * FROM MS_REPLY;

-- 79. 테이블의 속성 삭제
-- * MS_BOARD 테이블의 WRITER 속성
ALTER TABLE MS_BOARD DROP COLUMN WRITER;
-- * MS_FILE 테이블의 BOARD_NO 속성
ALTER TABLE MS_FILE DROP COLUMN BOARD_NO;
-- * MS_REPLY 테이블의 BOARD_NO 속성
ALTER TABLE MS_REPLY DROP COLUMN BOARD_NO;

-- 80. 
-- ON DELETE [NO ACTION, RESTRICT, CASCADE, SET NULL]
-- NO ACTION : 아무 행위 없음
-- RESTRICT  : 자식 테이블에 데이터가 존재하면 삭제하지 않음
-- CASCADE   : 자식 테이블에 데이터가 존재하면 함께 삭제
-- SET NULL  : 부모 테이블의 데이터가 변경되면 자식 테이블의 데이터는 NULL로 수정
-- * MS_BOARD 테이블의 WRITER 속성
ALTER TABLE MS_BOARD ADD WRITER NUMBER NOT NULL;
ALTER TABLE MS_BOARD ADD CONSTRAINT MS_BOARD_WRITER_FK
FOREIGN KEY (WRITER) REFERENCES MS_USER(USER_NO)
ON DELETE CASCADE;
-- * MS_FILE 테이블의 BOARD_NO 속성
ALTER TABLE MS_FILE ADD BOARD_NO NUMBER NOT NULL;
ALTER TABLE MS_FILE ADD CONSTRAINT MS_FILE_BOARD_NO_FK
FOREIGN KEY (BOARD_NO) REFERENCES MS_BOARD(BOARD_NO)
ON DELETE CASCADE;
-- * MS_REPLY 테이블의 BOARD_NO 속성
ALTER TABLE MS_REPLY ADD BOARD_NO NUMBER NOT NULL;
ALTER TABLE MS_REPLY ADD CONSTRAINT MS_REPLY_BOARD_NO_FK
FOREIGN KEY (BOARD_NO) REFERENCES MS_BOARD(BOARD_NO)
ON DELETE CASCADE;

SELECT * FROM MS_USER;
SELECT * FROM MS_FILE;
SELECT * FROM MS_REPLY;
SELECT * FROM MS_BOARD;

DELETE FROM MS_BOARD WHERE BOARD_NO=1;

-- Gㅚ원 탈퇴 1번
DELETE FROM MS_USER
WHERE USER_NO = 1;

-- 외래키 제약 조건
ALTER TABLE 테이블명
ADD CONSTRAINT 제약조건명 FOREIGN KEY (외래키 속성)
REFERENCES 참조테이블(참조 속성)
ON UPDATE [NO ACTION, RESTRICT, CASCADE, SET NULL]
ON DELETE [NO ACTION, RESTRICT, CASCADE, SET NULL];
-- 옵션
-- ON UPDATE            -- 참조 테이블 수정 시,
--  * CASCADE           : 자식 데이터 수정
--  * SET NULL          : 자식 데이터는 NULL 
--  * SET DEFAULT       : 자식 데이터는 기본값
--  * RESTRICT          : 자식 테이블의 참조하는 데이터가 존재하면, 부모 데이터 수정 불가
--  * NO ACTION         : 아무런 행위도 취하지 않는다 (기본값)

-- ON DELETE            -- 참조 테이블 삭제 시,
--  * CASCADE           : 자식 데이터 삭제
--  * SET NULL          : 자식 데이터는 NULL 
--  * SET DEFAULT       : 자식 데이터는 기본값
--  * RESTRICT          : 자식 테이블의 참조하는 데이터가 존재하면, 부모 데이터 삭제 불가
--  * NO ACTION         : 아무런 행위도 취하지 않는다 (기본값)

-- 81. 서브쿼리, 조인
-- EMPLOYEE, DEPARTMENT, JOB 테이블을 사용하여
-- 스칼라 서브쿼리로 출력결과와 같이 조회하시오
SELECT * FROM EMPLOYEE;      -- 사원
SELECT * FROM DEPARTMENT;  -- 부서
SELECT * FROM JOB;              -- 직급

-- 사원번호, 직원명, 부서명, 직급명
SELECT E.EMP_ID 사원번호, E.EMP_NAME 직원명, (SELECT DEPT_TITLE
                                                            FROM DEPARTMENT D WHERE E.DEPT_CODE = D.DEPT_ID ) 부서명
                                                            ,(SELECT JOB_NAME
                                                            FROM JOB J
                                                            WHERE E.JOB_CODE = J.JOB_CODE) 직급명
FROM EMPLOYEE E;

-- 82. 인라인 뷰 작성
-- 1. 부서별로 최고급여를 조회 (서브쿼리)
SELECT DEPT_CODE 부서코드 ,MAX(SALARY) 최고급여 ,MIN(SALARY) 최저급여 , ROUND(AVG(SALARY),2) 평균급여
FROM EMPLOYEE
GROUP BY DEPT_CODE;
-- 2. 부서별로 최고급여 조회결과를 서브쿼리(인라인 뷰)로 지정
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_TITLE 부서명, e.SALARY 급여, t.최고급여, t.최저급여, t.평균급여
FROM EMPLOYEE e, DEPARTMENT d,
        (SELECT DEPT_CODE 부서코드 
                ,MAX(SALARY) 최고급여 
                ,MIN(SALARY) 최저급여 
                ,ROUND(AVG(SALARY),2) 평균급여
        FROM EMPLOYEE
        GROUP BY DEPT_CODE) t
WHERE e.DEPT_CODE = d.DEPT_ID AND e.SALARY = t.최고급여;

-- 83. 중첩 서브쿼리를 이용하여 직원명이 ‘이태림'인 사원과 같은 부서의 직원들을 조회
SELECT EMP_ID 전화번호, EMP_NAME 직원명, EMAIL 이메일, PHONE 전화번호
FROM EMPLOYEE
WHERE DEPT_CODE = (SELECT DEPT_CODE
                    FROM EMPLOYEE
                    WHERE EMP_NAME = '이태림');
                            
-- 84.
-- 사원 테이블에 존재하는 부서코드만 포함하는 부서를 조회하시오.
-- (사원이 존재하는 부서만 조회하시오.)
SELECT DEPT_ID FROM DEPARTMENT;                 -- D1~D9 (9개)
SELECT DISTINCT DEPT_CODE FROM EMPLOYEE ORDER BY DEPT_CODE ASC; -- D1,D2,D5,D6,D8,D9,NULL
-- 사원이 없는 부서 : D3, D4, D7

-- 1) 서브쿼리
SELECT DEPT_ID 부서번호, DEPT_TITLE 부서명, LOCATION_ID 지역명
FROM DEPARTMENT
WHERE DEPT_ID IN (SELECT DEPT_CODE
                  FROM EMPLOYEE
                  WHERE DEPT_CODE IS NOT NULL)
ORDER BY 부서번호;
-- 2) EXISIS
SELECT DEPT_ID 부서번호, DEPT_TITLE 부서명, LOCATION_ID 지역명
FROM DEPARTMENT d
WHERE EXISTS (SELECT *
              FROM EMPLOYEE e
              WHERE e.DEPT_CODE = d.DEPT_ID)
ORDER BY 부서번호;

-- 85.
-- 사원 테이블에 존재하지 않는 부서코드만 포함하는 부서를 조회하시오.
-- (사원이 존재하지 않는 부서만 조회하시오.)
-- 사원이 있는 부서 : D1, D2, D5, D6, D8, D9
-- 사원이 없는 부서 : D3, D4, D7 
-- 1) 서브쿼리
SELECT DEPT_ID 부서번호, DEPT_TITLE 부서명, LOCATION_ID 지역명
FROM DEPARTMENT
WHERE DEPT_ID NOT IN (SELECT DEPT_CODE
                      FROM EMPLOYEE
                      WHERE DEPT_CODE IS NOT NULL)
ORDER BY 부서번호;
-- 2) NOT EXISIS
SELECT DEPT_ID 부서번호, DEPT_TITLE 부서명, LOCATION_ID 지역명
FROM DEPARTMENT d
WHERE NOT EXISTS (SELECT *
                  FROM EMPLOYEE e
                  WHERE e.DEPT_CODE = d.DEPT_ID)
ORDER BY 부서번호;

-- 86. EMPLOYEE 테이블의 DEPT_CODE 가 'D1' 인 부서의 최대급여 보다
-- 더 큰 급여를 받는 사원을 조회하시오.
SELECT *
FROM EMPLOYEE, DEPARTMENT;  -- 사원*부서 : 23*9=207(카타시안 곱, 크로스 조인)
-- 사원번호, 직원명, 부서번호, 부서명, 급여
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명, e.SALARY 급여
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID;
-- 1) D1중 가장 큰 월급을 반환
-- 1. 부서코드가 'D1'인 사원의 최대급여
SELECT MAX(SALARY)
FROM EMPLOYEE
WHERE DEPT_CODE='D1';
-- 2. 급여가 ????? 보다 큰 사원을 조회
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명, e.SALARY 급여
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID
AND e.SALARY > 3660000
-- 3. 3660000자리에 1번 쿼리를 대입
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명, e.SALARY 급여
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID
AND e.SALARY > (SELECT MAX(SALARY)
                FROM EMPLOYEE
                WHERE DEPT_CODE='D1')
ORDER BY e.SALARY;
--2) D1의 모든 월급을 반환
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명, e.SALARY 급여
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID
AND e.SALARY > ALL(SELECT SALARY
                   FROM EMPLOYEE
                   WHERE DEPT_CODE='D1')
ORDER BY e.SALARY;

-- 87. EMPLOYEE 의 DEPT_CODE 가 ‘D9’인 부서의 최저급여보다 더 큰 급여를 받는 사원을 조회하는 SQL 문을 작성
-- 1) D9중 가장 작은 월급을 반환
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명, e.SALARY 급여
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID
AND e.SALARY > (SELECT MIN(SALARY)
                FROM EMPLOYEE
                WHERE DEPT_CODE='D9')
ORDER BY e.SALARY DESC;
--2) D1의 모든 월급을 반환
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명, e.SALARY 급여
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID
AND e.SALARY > ANY(SELECT SALARY
                   FROM EMPLOYEE
                   WHERE DEPT_CODE='D9')
ORDER BY e.SALARY DESC;

-- 88. EMPLOYEE 와 DEPARTMENT 테이블을 조인하여 출력하되,
-- 부서가 없는 직원도 포함하여 출력하시오.
-- 사원번호, 직원명, 부서번호, 부서명
--1) LEFT OUTER JOIN(ANSI 표준)
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, NVL(e.DEPT_CODE, '(없음)') 부서번호, NVL(d.DEPT_TITLE,'(없음)') 부서명
FROM EMPLOYEE e LEFT JOIN DEPARTMENT d ON(e.DEPT_CODE = d.DEPT_ID);
--2) (+) 연산자 사용
SELECT e.EMP_ID 사원번호, e.EMP_NAME 직원명, NVL(e.DEPT_CODE, '(없음)') 부서번호, NVL(d.DEPT_TITLE,'(없음)') 부서명
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE = d.DEPT_ID(+);

-- 89. 테이블 EMPLOYEE 와 DEPARTMENT 를 조인하여 출력하되, 직원이 없는 부서도 포함하여 출력
--1) RIGHT OUTER JOIN(ANSI 표준)
SELECT NVL(e.EMP_ID, '(없음)') 사원번호, NVL(e.EMP_NAME, '(없음)') 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명
FROM EMPLOYEE e RIGHT OUTER JOIN DEPARTMENT d ON(e.DEPT_CODE = d.DEPT_ID);
--2) (+) 연산자 사용
SELECT NVL(e.EMP_ID, '(없음)') 사원번호, NVL(e.EMP_NAME, '(없음)') 직원명, d.DEPT_ID 부서번호, d.DEPT_TITLE 부서명
FROM EMPLOYEE e, DEPARTMENT d
WHERE e.DEPT_CODE(+) = d.DEPT_ID;

-- 90. EMPLOYEE 와 DEPARTMENT 를 조인하여 출력하되, 직원 및 부서 유무에 상관없이 출력
SELECT NVL(e.EMP_ID, '(없음)') 사원번호, 
        NVL(e.EMP_NAME, '(없음)') 직원명,
        NVL(d.DEPT_ID, '(없음)') 부서번호,
        -- NVL(d.DEPT_TITLE, '(없음)') 부서명
FROM EMPLOYEE e FULL OUTER JOIN DEPARTMENT d ON(e.DEPT_CODE = d.DEPT_ID);

-- 91.
-- 사원번호, 직원명, 부서번호, 지역명, 국가명, 급여, 입사일자를 출력하시오.

SELECT * FROM EMPLOYEE;
SELECT * FROM DEPARTMENT;
SELECT * FROM LOCATION;
SELECT * FROM NATIONAL;

SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_ID 부서번호
      ,D.DEPT_TITLE 부서명
      ,L.LOCAL_NAME 지역명
      ,N.NATIONAL_NAME 국가명
      ,E.SALARY 급여
      ,E.HIRE_DATE 입사일자
FROM EMPLOYEE E
     LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
     LEFT JOIN LOCATION L ON D.LOCATION_ID = L.LOCAL_CODE
     LEFT JOIN NATIONAL N ON L.NATIONAL_CODE = N.NATIONAL_CODE
;



-- 92.
-- 사원들 중 매니저를 출력하시오.
-- 사원번호, 직원명, 부서명, 직급, 구분('매니저')

-- 1. 
-- MANAGER_ID 컬럼이 NULL 이 아닌 사원을 중복없이 조회
-- ➡ 매니저들의 사원 번호
SELECT DISTINCT MANAGER_ID
FROM EMPLOYEE
WHERE MANAGER_ID IS NOT NULL;

-- 2.
-- EMPLOYEE, DEPARTMENT, JOB 테이블을 조인하여 조회
SELECT *
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
      JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
;


-- 3.
-- 조인 결과 중, EMP_ID 매니저 사원번호인 경우만 조회
SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,'매니저' 직급
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
      JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
WHERE E.EMP_ID IN (
                        SELECT DISTINCT MANAGER_ID
                        FROM EMPLOYEE
                        WHERE MANAGER_ID IS NOT NULL
                  )
;





-- 93.
-- 사원(매니저가 아닌)만 조회하시오.
SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,'사원' 직급
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
      JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
WHERE E.EMP_ID NOT IN (
                        SELECT DISTINCT MANAGER_ID
                        FROM EMPLOYEE
                        WHERE MANAGER_ID IS NOT NULL
                  )
;



-- 94.
-- UNION 키워드를 사용하여,
-- 매니저와 사원 구분하여 조회하시오.
SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,'매니저' 직급
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
      JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
WHERE E.EMP_ID IN (
                        SELECT DISTINCT MANAGER_ID
                        FROM EMPLOYEE
                        WHERE MANAGER_ID IS NOT NULL
                  )
UNION
SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,'사원' 직급
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
      JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
WHERE E.EMP_ID NOT IN (
                        SELECT DISTINCT MANAGER_ID
                        FROM EMPLOYEE
                        WHERE MANAGER_ID IS NOT NULL
                  )
;




-- 95.
-- CASE 키워드를 사용하여,
-- 매니저와 사원을 구분하여 출력하시오
SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,CASE
            WHEN EMP_ID IN (
                              SELECT DISTINCT MANAGER_ID
                              FROM EMPLOYEE
                              WHERE MANAGER_ID IS NOT NULL
                            )
            THEN '매니저'
            ELSE '사원'
       END 구분
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON E.DEPT_CODE = D.DEPT_ID
      JOIN JOB J ON E.JOB_CODE = J.JOB_CODE
;



-- 96.
-- EMPLOYEE, DEPARTMENT, JOB 테이블을 조인하여 조회하시오
-- 사원의 나이와 성별을 구하여 출력하고,
-- 주민등록번호 뒷자리 첫글자를 제외하고 마스킹하여 출력하시오.

SELECT E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,CASE
            WHEN EMP_ID IN (
                              SELECT DISTINCT MANAGER_ID
                              FROM EMPLOYEE
                              WHERE MANAGER_ID IS NOT NULL
                            )
            THEN '매니저'
            ELSE '사원'
       END 구분
       -- 성별
       -- * 주민등록번호(EMP_NO) 뒷자리 첫글자
       -- 1,3 (남자), 2,4(여자)
       , 
       CASE
            WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','3') THEN '남자'
            WHEN SUBSTR(EMP_NO, 8, 1) IN ('2','4') THEN '여자'
       END 성별
      
      -- 나이
      -- 1900년대 출생 (주민번호 뒷자리 첫글자가 1,2)
      -- 2000년대 출생 (주민번호 뒷자리 첫글자가 3,4)
      ,TO_CHAR(sysdate, 'YYYY')
      -
      (
            CASE 
                  WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','2') THEN '19'  
                  WHEN SUBSTR(EMP_NO, 8, 1) IN ('3','4') THEN '20'  
            END || SUBSTR(EMP_NO, 1, 2)
      ) + 1 현재나이
      -- 만나이
      -- * 태어나면 0살, 햇수가 아니라 생일이 지나야 +1살
      ,TRUNC(
            MONTHS_BETWEEN(  SYSDATE, 
                              TO_DATE(
                                    CASE 
                                          WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','2') THEN '19'  
                                          WHEN SUBSTR(EMP_NO, 8, 1) IN ('3','4') THEN '20'  
                                    END || SUBSTR(EMP_NO, 1, 6)
                              , 'YYYYMMDD') -- 생년월일(문자) -- (날짜)
                        ) / 12
      ) 만나이
      -- 주민번호 마스킹
      ,RPAD( SUBSTR(EMP_NO, 1, 8), 14, '*' ) 주민등록번호
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
      JOIN JOB J USING(JOB_CODE)

;

-- USING : 조인하고자 하는 두 테이블 컬럼명이 같으면,
--         ON 키워드 대신 조인 조건을 간단하게 작성하는 키워드



-- 생년월일
SELECT 
      TO_DATE(
            CASE 
                  WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','2') THEN '19'  
                  WHEN SUBSTR(EMP_NO, 8, 1) IN ('3','4') THEN '20'  
            END || SUBSTR(EMP_NO, 1, 6)
      , 'YYYYMMDD') -- 생년월일(문자) -- (날짜)
FROM EMPLOYEE
;

-- 오늘 날짜
SELECT SYSDATE
FROM DUAL;


-- 만나이
-- MONTHS_BETWEEN( 오늘날짜, 생년월일 ) 
SELECT TRUNC(
            MONTHS_BETWEEN(  SYSDATE, 
                              TO_DATE(
                                    CASE 
                                          WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','2') THEN '19'  
                                          WHEN SUBSTR(EMP_NO, 8, 1) IN ('3','4') THEN '20'  
                                    END || SUBSTR(EMP_NO, 1, 6)
                              , 'YYYYMMDD') -- 생년월일(문자) -- (날짜)
                        ) / 12
      ) 만나이
FROM EMPLOYEE
;


-- 97.
-- 96번 조회결과에 
-- 순번, 만나이, 근속연수, 입사일자, 연봉을 추가하시오.

SELECT ROWNUM 순번
      ,E.EMP_ID 사원번호
      ,E.EMP_NAME 직원명
      ,D.DEPT_TITLE 부서명
      ,J.JOB_NAME 직급명
      ,CASE
            WHEN EMP_ID IN (
                              SELECT DISTINCT MANAGER_ID
                              FROM EMPLOYEE
                              WHERE MANAGER_ID IS NOT NULL
                            )
            THEN '매니저'
            ELSE '사원'
       END 구분
       -- 성별
       -- * 주민등록번호(EMP_NO) 뒷자리 첫글자
       -- 1,3 (남자), 2,4(여자)
       , 
       CASE
            WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','3') THEN '남자'
            WHEN SUBSTR(EMP_NO, 8, 1) IN ('2','4') THEN '여자'
       END 성별
      
      -- 나이
      -- 1900년대 출생 (주민번호 뒷자리 첫글자가 1,2)
      -- 2000년대 출생 (주민번호 뒷자리 첫글자가 3,4)
      ,TO_CHAR(sysdate, 'YYYY')
      -
      (
            CASE 
                  WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','2') THEN '19'  
                  WHEN SUBSTR(EMP_NO, 8, 1) IN ('3','4') THEN '20'  
            END || SUBSTR(EMP_NO, 1, 2)
      ) + 1 현재나이
      -- 만나이
      -- * 태어나면 0살, 햇수가 아니라 생일이 지나야 +1살
      ,TRUNC(
            MONTHS_BETWEEN(  SYSDATE, 
                              TO_DATE(
                                    CASE 
                                          WHEN SUBSTR(EMP_NO, 8, 1) IN ('1','2') THEN '19'  
                                          WHEN SUBSTR(EMP_NO, 8, 1) IN ('3','4') THEN '20'  
                                    END || SUBSTR(EMP_NO, 1, 6)
                              , 'YYYYMMDD') -- 생년월일(문자) -- (날짜)
                        ) / 12
      ) 만나이
      -- 근속연수
      ,TRUNC(
            MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12
      ) 근속연수
      -- 주민번호 마스킹
      ,RPAD( SUBSTR(EMP_NO, 1, 8), 14, '*' ) 주민등록번호
      -- 입사일자
      ,TO_CHAR(HIRE_DATE, 'YYYY.MM.DD') 입사일자
      -- 연봉 :  (급여 + (급여*보너스)) * 12
      ,TO_CHAR( 
            (SALARY + (SALARY * NVL(BONUS,0) )) * 12
            , '999,999,999,999'
      ) 연봉
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
      JOIN JOB J USING(JOB_CODE)

;


-- 98.
-- employee, department 테이블을 조인하여,
-- 사원번호, 직원명, 부서번호, 부서명, 이메일, 전화번호
-- 주민번호, 입사일자, 급여, 연봉을 조회하시오.
-- CREATE OR REPLACE 객체
-- - 없으면, 새로 생성
-- - 있으면, 대체 (기존에 생성 되어 있어도 에러발생X)


-- 뷰 생성
CREATE OR REPLACE VIEW VE_EMP_DEPT AS
SELECT E.EMP_ID 
      ,E.EMP_NAME 
      ,D.DEPT_TITLE 
      ,E.EMAIL 
      ,E.PHONE 
      ,RPAD( SUBSTR(EMP_NO, 1, 8), 14, '*' ) EMP_NO
      ,TO_CHAR(HIRE_DATE, 'YYYY.MM.DD') HIRE_DATE
      ,E.SALARY 
      ,TO_CHAR( 
            (SALARY + (SALARY * NVL(BONUS,0) )) * 12
            , '999,999,999,999'
      ) YR_SALARY
FROM EMPLOYEE E
      LEFT JOIN DEPARTMENT D ON (E.DEPT_CODE = D.DEPT_ID)
;