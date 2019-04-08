package com.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bean.Skill;

public interface ISkillDAO {
	
	public boolean addSkill(Skill s) throws SQLException, ClassNotFoundException;
	public List<Skill> getAllSkills() throws SQLException, ClassNotFoundException;
	public List<Skill> getSkillsByCId(int cid) throws SQLException, ClassNotFoundException;
	public boolean deleteSkill(int sid) throws SQLException, ClassNotFoundException;
	public Skill getSkillById(int id) throws ClassNotFoundException, SQLException;
	public Skill getSkillBySkName(String skname) throws ClassNotFoundException, SQLException;

}
