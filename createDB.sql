drop table tskill;
drop table sskill;
drop table cskill;



drop table adminDB;
drop table allocationDB;
drop table smeDB;
drop table courseDB;
drop table skillDB;
drop table trainerDB;








create table skillDB(
skillId  int not null,
skillName varchar(20) not null,
primary key(skillId));




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
tstatus int not null,
primary key(tId));




create table smeDB(
sId int not null ,
sFirstName varchar(20) not null,
sLastName varchar(20) not null,
sAge int not null,
sGender varchar(10) not null,
sContactNo varchar(13) not null,
tMail varchar(15) not null,
Username varchar(20) not null unique,
Password varchar(20) not null,
sDateBegin date	not null,
sDateEnd date not null,
sstatus int not null,
primary key(sId));




create table adminDB(
Username varchar(20) not null,
Password varchar(20) not null,
primary key(UserName));
	


	
create table courseDB(
cId int not null,
cName varchar(20) not null,
cDateBegin date	not null,
cDateEnd date not null,
primary key(cId));

	
 

create table allocationDB(
tId int not null,
skillId int not null,
cId int not null,
allStatus int not null,
primary key(tId,skillId),
foreign key(tId)
references trainerDB(tId),
foreign key(cId)
references courseDB(cId),
foreign key(skillId)
references skillDB(skillId));	




create table tskill(
tId int not null,
skillId  int not null,
primary key(skillId,tId),
foreign key(tId)
references trainerDB(tId),
foreign key(skillId)
references skillDB(skillId));





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


	


insert into skillDB(skillId,skillName) values (1,'C');
insert into skillDB(skillId,skillName) values (2,'C++');
insert into skillDB(skillId,skillName) values (3,'Java');




insert into adminDB(Username,Password) values ('Admin','Password');



commit;
