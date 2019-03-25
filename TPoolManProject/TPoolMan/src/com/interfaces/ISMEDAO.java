package com.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.bean.SME;
import com.bean.Skill;

public interface ISMEDAO {

	public boolean addSME(SME sme) throws SQLException, ClassNotFoundException, ParseException;
	public boolean setSDates(Date sd,Date ed) throws SQLException, ClassNotFoundException;
	public boolean deleteSME(int smeid) throws SQLException, ClassNotFoundException;
	public List<SME> getAllSMEs() throws SQLException, ClassNotFoundException;
	public boolean addSSkill(int smeid,int sid) throws SQLException, ClassNotFoundException;
	public boolean updateSDates(int smeid,Date sd,Date ed) throws SQLException, ClassNotFoundException;
	public List<SME> getSMEByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException;
	public SME getSMEByUsername(String smeuname) throws ClassNotFoundException, SQLException;
	public SME getSMEById(int smeid) throws ClassNotFoundException, SQLException;
	public List<SME> getSMEsBySkillId(int skillId) throws SQLException, ClassNotFoundException;
	public List<Skill> getSkillsBySId(int sid) throws ClassNotFoundException, SQLException;
	public boolean updateSME(SME sme) throws SQLException, ClassNotFoundException;
	
}
