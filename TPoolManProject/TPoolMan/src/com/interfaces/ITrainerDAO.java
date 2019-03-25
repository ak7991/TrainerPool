package com.interfaces;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.bean.Skill;
import com.bean.Trainer;

public interface ITrainerDAO {
	
	public boolean addTrainer(Trainer t) throws SQLException, ClassNotFoundException, ParseException;
	public boolean setTDates(Date sd,Date ed) throws SQLException, ClassNotFoundException;
	public boolean deleteTrainer(int tid) throws SQLException, ClassNotFoundException;
	public List<Trainer> getAllTrainers() throws SQLException, ClassNotFoundException;
	public boolean addTSkill(int tid,int sid) throws SQLException, ClassNotFoundException;
	public boolean updateTDates(int tid,Date sd,Date ed) throws SQLException, ClassNotFoundException;
	public List<Trainer> getTrainersByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException;
	public List<Trainer> getTrainersByUsername(String tuname) throws ClassNotFoundException, SQLException;
	public List<Trainer> getTrainersById(int tid) throws ClassNotFoundException, SQLException;
	public List<Trainer> getTrainersBySkillId(int skillId) throws SQLException, ClassNotFoundException;
	public List<Skill> getSkillsByTId(int tid) throws ClassNotFoundException, SQLException;
	public boolean updateTrainer(Trainer t) throws SQLException, ClassNotFoundException;
	

}
