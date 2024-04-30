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
                            
-- 84. EMPLOYEE 와 DEPARTMENT 의 DEPT_CODE 와 DEPT_ID 속성이 일치하는 행이 존재하는 경우,
--      테이블 DEPARTMENT 의 데이터를 조회
