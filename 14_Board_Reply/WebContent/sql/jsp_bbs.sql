-- jsp_bbs 테이블 생성
-- BBS(Bulletin Board System : 전자 게시판)

-- jsp_bbs 게시판 데이블 구성

create table jsp_bbs(
	board_no number(5) primary key,			-- 게시판 글번호
	board_writer varchar2(30) not null,		-- 게시판 글 작성자
	board_title varchar2(100) not null,		-- 게시판 글 제목
	board_cont varchar2(1000) not null,		-- 게시판 글 내용
	board_pwd varchar2(30) not null,		-- 게시판 글 비밀번호
	board_hit number(5) default 0,			-- 게시판 글 조회수
	board_date date,						-- 게시판 글 작성일자
	board_update date,						-- 게시판 글 수정일자
	board_group number(5),					-- 게시판 글 그룹
	board_step number(5),					-- 게시판 글 답변 단계
	board_indent number(5)					-- 게시판 답변글 들여쓰기
);

-- jsp_bbs 테이블에 데이터 추가하기

insert into jsp_bbs values(1,'홍길동','제목1','내용1','1234',default,sysdate,'',1,0,0);
insert into jsp_bbs values(1,'이순신','제목2','내용2','1234',default,sysdate,'',2,0,0);
insert into jsp_bbs values(1,'윤봉길','제목3','내용3','1234',default,sysdate,'',3,0,0);
insert into jsp_bbs values(1,'김원봉','제목4','내용4','1234',default,sysdate,'',4,0,0);
insert into jsp_bbs values(1,'김구','제목5','내용5','1234',default,sysdate,'',5,0,0);
insert into jsp_bbs values(1,'윤길','제목6','내용6','1234',default,sysdate,'',6,0,0);
insert into jsp_bbs values(1,'홍범도','제목7','내용7','1234',default,sysdate,'',7,0,0);
insert into jsp_bbs values(1,'유관순','제목8','내용8','1234',default,sysdate,'',8,0,0);
insert into jsp_bbs values(1,'김채호','제목9','내용9','1234',default,sysdate,'',9,0,0);
insert into jsp_bbs values(1,'김남길','제목10','내용10','1234',default,sysdate,'',10,0,0);
insert into jsp_bbs values(1,'백석','제목11','내용11','1234',default,sysdate,'',11,0,0);
insert into jsp_bbs values(1,'장인환','제목11','내용11','1234',default,sysdate,'',12,0,0);