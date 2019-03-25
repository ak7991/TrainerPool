drop sequence n;
drop table tskill;
drop table sskill;
drop table cskill;
drop table allocationDB;
drop table courseDB;
drop table adminDB;
drop table trainerDB;
drop table smeDB;
drop table skillDB;




create Sequence n
minvalue 1000
start with 1001
increment by 1;



create table adminDB(
Username varchar(20) not null,
Password varchar(20) not null,
primary key(UserName));
	




create table trainerDB(
tId int not null ,
tFirstName varchar(20) not null,
tLastName varchar(20) not null,
tAge int not null,
tGender varchar(10) not null,
tContactNo varchar(13) not null,
tMail varchar(15) not null,
Username varchar(20) not null unique,
Password varchar(20) not null,
tDateBegin date	not null,
tDateEnd date not null,
CHECK (tDateEnd>tDateBegin),
tstatus int not null,
primary key(tId,tDateBegin,tDateEnd)
);




create table smeDB(
sId int not null ,
sFirstName varchar(20) not null,
sLastName varchar(20) not null,
sAge int not null,
sGender varchar(10) not null,
sContactNo varchar(13) not null,
sMail varchar(15) not null,
Username varchar(20) not null unique,
Password varchar(20) not null,
sDateBegin date	not null,
sDateEnd date not null,
CHECK (sDateEnd>sDateBegin),
sstatus int not null,
primary key(sId));




create table skillDB(
skillId  int not null,
skillName varchar(20) not null,
primary key(skillId));






	
create table courseDB(
cId int not null,
cName varchar(20) not null,
cDateBegin date	not null,
cDateEnd date not null,
primary key(cId));

	
 










create table sskill(
sId int not null,
skillId  int not null,
primary key(skillId,sId),
foreign key(sId)
references smeDB(sId),
foreign key(skillId)
references skillDB(skillId));





create table cskill(
cId int not null,
skillId  int not null,
primary key(skillId,cId),
foreign key(skillId)
references skillDB(skillId),
foreign key(cId)
references courseDB(cId));



create table allocationDB(
tId int not null,
cId int not null,
aDateBegin date	not null,
aDateEnd date not null,
CHECK (aDateEnd>aDateBegin),
primary key(tId,aDateBegin,aDateEnd),
foreign key(cId)
references courseDB(cId));	
	

create table tskill(
tId int not null,
skillId  int not null,
primary key(skillId,tId),
foreign key(skillId)
references skillDB(skillId));






insert into skillDB(skillId,skillName) values (1,'C');
insert into skillDB(skillId,skillName) values (2,'C++');
insert into skillDB(skillId,skillName) values (3,'Java');

insert into courseDB(cId,cName,cDateBegin,cDateEnd) values(1,'course1',to_date('01/08/1999','MM/dd/yyyy'),to_date('18/08/2002','MM/dd/yyyy'));


insert into adminDB(Username,Password) values ('Admin','Password');

insert into trainerDB(tId,tFirstName,tLastName,tAge,tGender,tContactNo,tMail,Username,Password,tDateBegin ,tDateEnd ,tstatus) values(1,'Ishan','Agrawal',21,'Male','123456789','ishan@abc.com','ishan123','12345',to_date('01/01/1997','MM/dd/yyyy'),to_date('01/01/2007','MM/dd/yyyy'),0);

insert into smeDB(sId,sFirstName,sLastName,sAge,sGender,sContactNo,sMail,Username,Password,sDateBegin ,sDateEnd ,sstatus) values(1,'Sandeep','Mohanty',21,'Male','124675789','sandeep@abc.com','sandeep321','54123',to_date('08/01/2005','MM/dd/yyyy'),to_date('15/01/2006','MM/dd/yyyy'),0);

insert into sskill(sId ,skillId) values(1,2);
insert into sskill(sId ,skillId) values(1,3);
insert into tskill(tId ,skillId) values(1,1);
insert into tskill(tId ,skillId) values(1,3);
		   
insert into cskill(cId ,skillId) values(1,2);
insert into cskill(cId ,skillId) values(1,3);
	
insert into allocationDB(tId,cId,aDateBegin,aDateEnd) values(1,1,to_date('01/08/1999','MM/dd/yyyy'),to_date('18/08/2002','MM/dd/yyyy'));														 
														  
@@getPassHash
/

@@AdminTrigger
/

@@TrainerTrigger
/

@@SMETrigger
/
commit;
