-- Active: 1709617434225@@127.0.0.1@1521@orcl@JOEUN
CREATE TABLE "users" (
	"user_id"	VARCHAR(50)		NOT NULL,
	"user_pw"	VARCHAR(50)		NULL,
	"name"	VARCHAR(100)		NULL,
	"age"	NUMBER		NULL,
	"job"	VARCHAR(20)		NULL,
	"grade"	VARCHAR(20)		NULL,
	"point"	NUMBER		NULL
);

CREATE TABLE "products" (
	"product_no"	NUMBER		NOT NULL,
	"name"	VARCHAR(50)		NULL,
	"amount"	NUMBER		NULL,
	"price"	NUMBER		NULL
);

CREATE TABLE "orders" (
	"order_no"	NUMBER		NOT NULL,
	"user_id"	VARCHAR(50)		NOT NULL,
	"product_no"	NUMBER		NOT NULL,
	"amount"	NUMBER		NULL,
	"address"	VARCHAR(1000)		NULL,
	"order_date"	DATE		NULL
);

ALTER TABLE "users" ADD CONSTRAINT "PK_USERS" PRIMARY KEY (
	"user_id"
);

ALTER TABLE "products" ADD CONSTRAINT "PK_PRODUCTS" PRIMARY KEY (
	"product_no"
);

ALTER TABLE "orders" ADD CONSTRAINT "PK_ORDERS" PRIMARY KEY (
	"order_no",
	"user_id",
	"product_no"
);

ALTER TABLE "orders" ADD CONSTRAINT "FK_users_TO_orders_1" FOREIGN KEY (
	"user_id"
)
REFERENCES "users" (
	"user_id"
);

ALTER TABLE "orders" ADD CONSTRAINT "FK_products_TO_orders_1" FOREIGN KEY (
	"product_no"
)
REFERENCES "products" (
	"product_no"
);

SELECT *
FROM users;

SELECT *
FROM orders;

SELECT *
FROM products;