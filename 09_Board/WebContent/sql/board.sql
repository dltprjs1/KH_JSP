-- board 테이블 만들기.

create table board(							
	board_no number(5) primary key,			-- 게시판 글 번호
	board_writer varchar2(30) not null,		-- 게시판 글 작성자
	board_title varchar2(200) not null,		-- 게시판 글 제목
	board_cont varchar2(1000),				-- 게시판 글 내용
	board_pwd varchar2(30) not null,		-- 게시판 글 비밀번호
	board_hit number(5) default 0 ,			-- 게시판 글 조회수
	board_date date,						-- 게시판 글 작성일
	board_update date						-- 게시판 글 수정일
);

-- board 테이블에 게시글을 추가해 보자

insert into board values(1,'홍길동','제목1','길동님 글입니다.','1234',default,sysdate,'');
insert into board values(1,'이순신','제목2','장군님 글입니다.','2345',default,sysdate,'');
insert into board values(1,'유관순','제목3','열사님 글입니다.','3456',default,sysdate,'');