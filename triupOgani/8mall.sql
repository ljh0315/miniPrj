--��ǰ
	--��ǰID : ��ID+code
	--��ǰ��
	--������/���Կ�
	--�ǸŰ���
	--�ϹݼҺ��ڰ���
	--ī���Һο���
	--�˻���
	--��ǰ����
	--��ǰ�������
	--��ǰ�������
DROP TABLE Product ;
CREATE TABLE Product (
       cateNum				int NOT NULL,
       ProductID              VARCHAR(7) NOT NULL,
       MallID		     VARCHAR(4) NOT NULL,
       ProductName           VARCHAR(40) NOT NULL,
       Company              VARCHAR(40) NOT NULL,
       Price1               int NOT NULL,
       Price2               int ,
       Install              CHAR(1) NOT NULL,
       keyword              VARCHAR(50) NOT NULL,
       Detail               VARCHAR(100) ,
       ProductDate         DATE NOT NULL,
       PHOTODIR	VARCHAR(100)
);

ALTER TABLE Product
       ADD   PRIMARY KEY (ProductID, MallID)  ;

--��ٱ���
	--�ֹ���ȣ
	--��ǰID
	--ȸ��ID
	--����
	--����
DROP TABLE Basket ;
CREATE TABLE Basket (
       OrderNum             int NOT NULL,
       ProductID            VARCHAR(7) NOT NULL,
       MemID		    VARCHAR(15) NOT NULL,	
       Quantity             int NOT NULL,
       Price                int NOT NULL
);

ALTER TABLE Basket
       ADD   PRIMARY KEY (OrderNum)  ;

--������
	--�ֹ���ȣ
	--�ּұ���(1:��,2:����)
	--ȸ��ID
	--�������̸�
	--�������ּ�
	--����ó
	--�̸���
	--������(CARD / REMIT)
	--���Ż�ǰ����
	--�ѱ��ž�
	--ó������(Y/N)
	--ī������
	--ī���ȣ
DROP TABLE Purchaser ;
CREATE TABLE Purchaser (
       OrderNum             int NOT NULL,
       Place		    CHAR(1) NOT NULL,
       MemID                VARCHAR(15) NOT NULL,
       Name                 VARCHAR(20) NOT NULL,
       Address              VARCHAR(70) NOT NULL,
       Tel		    VARCHAR(20) ,
       Email		    VARCHAR(60) ,
       PayType              CHAR(5) NOT NULL,
       Tcount               int ,
       Amount               int NOT NULL,
       PayStatus            CHAR(1) ,
       PurchaseDate         DATE NOT NULL,
       CardType		    VARCHAR(10) ,		
       CardNumber	    VARCHAR(16) 
);

ALTER TABLE Purchaser
       ADD   PRIMARY KEY (OrderNum)  ;

--ȸ��
	--ȸ��ID
	--�н�����
	--ȸ����
	--�ֹι�ȣ
	--�̸���
	--��ȭ��ȣ
	--�����ȣ
	--�ּ�
	--����Ʈ
	--ȸ����������
DROP TABLE Member ;
CREATE TABLE Member (
       MemID                VARCHAR(15) NOT NULL,
       password             VARCHAR(10) NOT NULL,
       Name                 VARCHAR(30) NOT NULL,
       SSN                  VARCHAR(14) ,
       Email                VARCHAR(60) ,
       Tel                  VARCHAR(20) ,
       Zipcode              VARCHAR(10) ,
       Address              VARCHAR(200) ,
       POINT                int ,
       memberDate           DATE
);

ALTER TABLE Member
       ADD   PRIMARY KEY (MemID)  ;

commit;

select * from member;

--������ �Խ���
drop table qnaBoard;
create table qnaBoard (
	num  int  not  null,
	writer   varchar(50),
	email   varchar(30),	
	subject   varchar(100),	
	passwd   varchar(10),
	reg_date   DATE,
	readcount  int,
	ref   int,
	re_step   int,
	re_level  int,
	content    varchar(3000),
	ip   varchar(15)
);

ALTER TABLE qnaBoard
       ADD   PRIMARY KEY (num)  ;

drop Sequence seq_qna;
Create Sequence seq_qna
start with 1
increment by 1
maxvalue 10000000;

select * from qnaBoard;

--�ı� �Խ���
drop table reviewBoard;
create table reviewBoard (
	num  int  not  null,
	writer   varchar(50),
	email   varchar(30),	
	subject   varchar(100),	
	passwd   varchar(10),
	reg_date   DATE,
	readcount  int,
	ref   int,
	re_step   int,
	re_level  int,
	content    varchar(3000),
	ip   varchar(15)
);

ALTER TABLE reviewBoard
       ADD   PRIMARY KEY (num)  ;

drop Sequence seq_review;
Create Sequence seq_review
start with 1
increment by 1
maxvalue 10000000;

select * from reviewBoard;