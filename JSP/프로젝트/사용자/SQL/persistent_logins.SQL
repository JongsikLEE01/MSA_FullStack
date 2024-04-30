-- 자동로그인 테이블
CREATE TABLE persistent_logins (
  p_no      NUMBER                      NOT NULL PRIMARY KEY,
  user_id   varchar2(255)               NOT NULL,
  token     varchar2(255)               NOT NULL,
  reg_date  DATE    DEFAULT SYSDATE NOT NULL,
  upd_date  DATE   DEFAULT SYSDATE NOT NULL
);

SELECT * FROM persistent_logins;

drop TABLE PERSISTENT_LOGINS;

-- 자동 로그인 시퀀스
CREATE SEQUENCE SEQ_PER_LOGIN
INCREMENT BY 1
START WITH 1
MAXVALUE 9999
MINVALUE 1;