select *
from all_users;

select *
from DBA_users;
-- hr이 있을때, 계정 잠금 해제
alter user joeun account unlock;

conn system/1234
-- c## 없이 계정 생성
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- CREATE USER 계정명 IDENTIFIED BY 비밀번호;
CREATE USER joeun IDENTIFIED BY 1234;

-- 테이블 스페이스 변경
ALTER USER joeun DEFAULT TABLESPACE users;

-- 계정이 사용할 수 있는 용량 설정 (무한대)
ALTER USER joeun QUOTA UNLIMITED ON users;

-- 계정에 권한 부여 
GRANT DBA TO joeun;


-- 계정 삭제
drop user joeun cascade;