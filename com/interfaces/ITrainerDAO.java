package com.interfaces;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.bean.Skill;
import com.bean.Trainer;

public interface ITrainerDAO {
	
	public boolean addTrainer(Trainer t) throws SQLException, ClassNotFoundException;
	public boolean deleteTrainer(int tid) throws SQLException, ClassNotFoundException;
	public List<Trainer> getAllTrainers() throws SQLException, ClassNotFoundException;
	public boolean addTSkill(int tid,int sid) throws SQLException, ClassNotFoundException;
	public boolean updateTDates(int tid,Date sd,Date ed) throws SQLException, ClassNotFoundException;
	public Trainer getTrainerByUsername(String tuname) throws ClassNotFoundException, SQLException;
	public Trainer getTrainerById(int tid) throws ClassNotFoundException, SQLException;
	public List<Trainer> getTrainerBySkillId(int skillId) throws SQLException, ClassNotFoundException;
	public List<Skill> getSkillByTId(int tid) throws ClassNotFoundException, SQLException;
	

}
