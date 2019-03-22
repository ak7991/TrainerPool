create or replace trigger passHash2
	before insert or update on trainerdb
	for each row

begin

	--Getting hash password from function
 :new.password:= getPassHash(rtrim(:new.username),rtrim(:new.password));


end;
