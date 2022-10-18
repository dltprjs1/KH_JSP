
-- customer 테이블 생성


create table customer(
	no number(5) unique,            -- 고객 번호
	id varchar2(30) primary key,    -- 고객 아이디
	name varchar2(30) not null,     -- 고객 이름
	age number(3),                  -- 고객 나이
	phone varchar2(20) not null,    -- 고객 연락처
	addr varchar2(500)              -- 고객 주소
);


-- 고객 정보 저장
insert into customer
	values(1, 'hong', '홍길동', 27, '010-1111-1234', '서울시 중구 남대문로');

insert into customer
	values(2, 'leess', '이순신', 33, '010-2222-2345', '전라남도 신안군');
	
insert into customer
	values(3, 'yooks', '유관순', 19, '010-3333-3456', '충청남도 천안시');
	




