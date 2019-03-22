create or replace function getPassHash(p_username in varchar, p_password in varchar) return varchar as 

encryptedPassword char(128);

begin

	--Get hash from dbms_utility
	--Hash uses a string that is username concatenated with password to produce unique hashes
	encryptedPassword := to_char(dbms_utility.get_hash_value( upper(trim(p_username))||'/'||upper(trim(p_password)), 1000000000, power(2,30) ) );
	return trim(encryptedPassword);
end;