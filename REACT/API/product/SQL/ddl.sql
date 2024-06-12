-- Active: 1717979949380@@127.0.0.1@3306@joeun
-- 상품 테이블
CREATE TABLE product
(
  no        INT             NOT NULL AUTO_INCREMENT COMMENT '상품 번호',
  id        CHAR(36)        NOT NULL DEFAULT (UUID()) COMMENT '상품 아이디',
  name      VARCHAR(100)    NOT NULL COMMENT '상품 이름',
  price     INT             NOT NULL DEFAULT 0 COMMENT '상품 가격',
  img       TEXT            NULL COMMENT '상품 이미지',
  reg_date  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '상품 등록일자',
  upd_date  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '상품 수정일자',
  PRIMARY KEY (no)
) COMMENT '상품';

drop Table product;

-- stock    : 재고 수
-- quantity : 수량