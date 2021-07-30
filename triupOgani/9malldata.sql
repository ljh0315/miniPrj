insert into Member values('duke','duke','홍길동','850102-2347529','DUKE@SUNED.CO.KR','02-1234-5678','143-200','서울 강남구 삼성동 덕명빌딩 11층',0,'2002-08-30');
commit;

select * from MEMBER;
						--상품ID, mallID, 상품명, 제조원, 판매가격, 소비자가격, 카드할부여부, 검색어, 상품설명, 상품등록일자, 상품사진경로 
insert into product values(1, 'sams110','sams','서울1','삼성',1500000,1200000,'1','컴퓨터','삼성컴퓨터는 인기품목이다','2007-10-30','seoul_1.jpg');
insert into product values(1, 'sams310','sams','서울2','삼성',300000,250000,'1','모니터','삼성모니터는 인기품목이다','2007-10-30','seoul_2.jpg');
insert into product values(1, 'sams320','sams','서울3','삼성',200000,180000,'1','프린터','삼성프리터는 잉크젯 프린터이다.','2007-10-30','seoul_3.jpg');
insert into product values(1, 'sams340','sams','서울4','삼성',50000,45000,'1','키보트','키보드는 인체공학적으로 설계된 제품이다. ','2007-10-30','seoul_4.jpg');
insert into product values(1, 'sony120','sony','서울5','소니',2500000,2300000,'1','노트북','소니노트북은 경량의 노트북이다.','2007-10-30','seoul_5.jpg');
insert into product values(2, 'epsn320','epsn','서울6','엡손',400000,380000,'1','프턴터','엡손프린터는 잉크젯 프린터이다.','2007-10-30','seoul_6.jpg');
insert into product values(2, 'sony350','sony','서울7','소니',40000,37000,'0','마우스','광마우스','2002-08-30','seoul_7.jpg');
insert into product values(2, 'dawo410','dawo','서울8','대우',1100000,1000000,'1','냉장고','2Door 냉장고','2007-08-30','seoul_8.png');
insert into product values(2, 'sams420','sams','서울9','삼성',1000000,950000,'1','세탁기','드럼세탁기','2007-08-30','seoul_9.jpg');
insert into product values(2, 'sams430','sams','서울10','삼성',1200000,1100000,'1','텔레비젼','평면텔레비젼','2007-08-30','seoul_10.jpg');


commit;

select * from product;