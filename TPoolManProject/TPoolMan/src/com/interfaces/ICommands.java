package com.interfaces;

public interface ICommands {
	

	String tAddSeq="select n.nextval from dual";
	String tAddInsert="insert into trainerDB values(?,?,?,?,?,?,?,?,?,?,?,?)";
	String tSetDate = "insert into trainerDB(tDateBegin,tDateEnd) values(?,?)";
	String tAddTSkill= "insert into tskill values(?,?)";
	String tDel = "delete from trainerDB where tId=?";
	String tList="select * from trainerDB order by tId";
	String tUpDate="update trainerDB set tDateBegin=? , tDateEnd=? where tId=?";
	String tGetByDates = "select * from trainerDB where tDateBegin<=? and tDateEnd>=?";
	String tGetByUName="select * from trainerDB where tUsername=?";
	String tGetById = "select * from trainerDB where tId=?";
	String tGetBySkId="select * from tskill where skillId=?";
	String tGetSkByTId="select skillId from tskill where tId=?";
	String tUpdate = "update trainerDB set tFirstName=?,tLastName=?,tAge=?,tGender=?,tContactNo=?,tMail=?,Username=?,Password=?,tDateBegin=?,tDateEnd=?, tstatus=? where tId=?";
	
	String smeAddSeq = "select n.nextval from dual";
	String smeAddInsert = "insert into smeDB values(?,?,?,?,?,?,?,?,?,?,?,?)";
	String smeSetDate = "insert into smeDB(sDateBegin,sDateEnd) values(?,?)";
	String smeAddSSkill = "insert into sskill values(?,?)";
	String smeDel = "delete from smeDB where sId=?";
	String smeList="select * from smeDB order by sId";
	String smeUpDate="update smeDB set sDateBegin=? , sDateEnd=? where sId=?";
	String smeGetByDates="select sId from smeDB where sDateBegin<=? and sDateEnd>=?";
	String smeGetByUName ="select * from smeDB where sUsername=?";
	String smeGetById = "select * from smeDB where sId=?";
	String smeGetBySkId="select sId from sskill where skillId=?";
	String smeGetSkBySId="select skillId from sskill where sId=?";
	String smeUpdate = "update smeDB set sFirstName=?,sLastName=?,sAge=?,sGender=?,sContactNo=?,sMail=?,Username=?,Password=?,sDateBegin=?,sDateEnd=?, sstatus=? where sId=?";
	
	String cAddSeq = "select n.nextval from dual";
	String cAddInsert = "insert into courseDB values(?,?)";
	String cDel="delete from courseDB where cId=?";
	String cList="select * from courseDB order by cId";
	String cGetById="select * from courseDB where cId=?";
	String cGetByCName ="select * from courseDB where cName=?";
	String cGetByTId = "select cId from allocationDB where tId=?";
	
	String sAddSeq = "select n.nextval from dual";
	String sAddInsert = "insert into skillDB values(?,?)";
	String sDel="delete from skillDB where skillId=?";
	String sList="select * from skillDB order by skillId";
	String sGetById ="select * from skillDB where skillId=?";
	String sGetByCId = "select skillId from cskill where cId=?";
	String sGetBySkNAme = "select * from skillDB where skillName=?";
	
	String aAddAlloc = "insert into allocationDB values(?,?,?,?)";
	String aDelAlloc = "delete from allocationDB where tId=? cId=? aDateBegin=? aDateEnd=?";
	String aList = "select * from allocationDB";
	String aGetByTId = "select * from allocationDB where tId=?";
	String aGetByCId = "select * from allocationDB where cId=?";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
