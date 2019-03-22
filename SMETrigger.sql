create or replace trigger passHash3
	before insert or update on SMEdb
	for each row

begin

	--Getting hash password from function
 :new.password:= getPassHash(rtrim(:new.username),rtrim(:new.password));


end;
