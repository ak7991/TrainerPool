create or replace trigger passHash1
	before insert or update on admindb
	for each row

begin

	--Getting hash password from function
 :new.password:= getPassHash(rtrim(:new.username),rtrim(:new.password));


end;
