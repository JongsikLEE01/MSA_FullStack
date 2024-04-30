-- 테이블 삭제
DROP TABLE users;

-- ✅ erdcloud 에서 가져오면 ""가 붙는다. "" 제거후 사용
-- 테이블 생성
CREATE TABLE "USERS" (
	id	VARCHAR2(40)		NOT NULL,
	password	VARCHAR2(40)	NOT	NULL,
	name	VARCHAR2(10)		NOT NULL,
	gender	VARCHAR2(10)		NOT NULL,
	birth	VARCHAR2(10)		NOT NULL,
	mail	VARCHAR2(100)		NULL,
	join_date	Date DEFAULT sysdate NOT NULL ,
	reg_date	Date DEFAULT sysdate NOT NULL ,
	upd_date	Date DEFAULT sysdate NOT NULL 
);

ALTER TABLE USERS ADD CONSTRAINT PK_USERS PRIMARY KEY (
	id
);

select *
from user;

INSERT INTO users (id,password,name,gender,birth,mail)
VALUES ('joeun', '123456', '김조은', '남자', '20011022', 'joeun@naver.com');

commit;
