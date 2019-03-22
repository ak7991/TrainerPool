package com.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.bean.Course;
import com.bean.SME;
import com.bean.Skill;
import com.bean.Trainer;
import com.dao.AdminDAO;
import com.dao.CourseDAO;
import com.dao.SMEDAO;
import com.dao.SkillDAO;
import com.dao.TrainerDAO;

public class ServClass {
	
	TrainerDAO tdao=new TrainerDAO();
	SkillDAO skdao=new SkillDAO();
	AdminDAO adao=new AdminDAO();
	SMEDAO smedao=new SMEDAO();
	CourseDAO cdao=new CourseDAO();
	
	
	
	
	public boolean addTrainer(Trainer t) throws SQLException, ClassNotFoundException
	{
		return tdao.addTrainer(t);
	}
	public boolean setTDates(Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		return tdao.setTDates(sd, ed);
	}
	public boolean deleteTrainer(int tid) throws SQLException, ClassNotFoundException
	{
		return tdao.deleteTrainer(tid);
	}
	public List<Trainer> getAllTrainers() throws SQLException, ClassNotFoundException
	{
		return tdao.getAllTrainers();
	}
	public boolean addTSkill(int tid,int sid) throws SQLException, ClassNotFoundException
	{
		return tdao.addTSkill(tid, sid);
	}
	public boolean updateTDates(int tid,Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		return tdao.updateTDates(tid, sd, ed);
	}
	public List<Trainer> getTrainersByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException
	{
		return tdao.getTrainersByDates(sd, ed);
	}
	public Trainer getTrainerByUsername(String tuname) throws ClassNotFoundException, SQLException
	{
		return tdao.getTrainerByUsername(tuname);
	}
	public Trainer getTrainerById(int tid) throws ClassNotFoundException, SQLException
	{
		return tdao.getTrainerById(tid);
	}
	public List<Trainer> getTrainersBySkillId(int skillId) throws SQLException, ClassNotFoundException
	{
		return tdao.getTrainersBySkillId(skillId);
	}
	public List<Skill> getSkillsByTId(int tid) throws ClassNotFoundException, SQLException
	{
		return tdao.getSkillsByTId(tid);
	}
	public boolean updateTrainer(Trainer t) throws SQLException, ClassNotFoundException
	{
		return tdao.updateTrainer(t);
	}
	public boolean tRequest(Trainer t) throws SQLException, ClassNotFoundException
	{
		return tdao.tRequest(t);
	}
	
	
	
	
	public boolean addSME(SME sme) throws SQLException, ClassNotFoundException
	{
		return smedao.addSME(sme);
	}
	public boolean setSDates(Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		return smedao.setSDates(sd, ed);
	}
	public boolean deleteSME(int smeid) throws SQLException, ClassNotFoundException
	{
		return smedao.deleteSME(smeid);
	}
	public List<SME> getAllSMEs() throws SQLException, ClassNotFoundException
	{
		return smedao.getAllSMEs();
	}
	public boolean addSSkill(int smeid,int sid) throws SQLException, ClassNotFoundException
	{
		return smedao.addSSkill(smeid, sid);
	}
	public boolean updateSDates(int smeid,Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		return smedao.updateSDates(smeid, sd, ed);
	}
	public SME getSMEByUsername(String smeuname) throws ClassNotFoundException, SQLException
	{
		return smedao.getSMEByUsername(smeuname);
	}
	public SME getSMEById(int smeid) throws ClassNotFoundException, SQLException
	{
		return smedao.getSMEById(smeid);
	}
	public List<SME> getSMEsBySkillId(int skillId) throws SQLException, ClassNotFoundException
	{
		return smedao.getSMEsBySkillId(skillId);
	}
	public List<Skill> getSkillsBySId(int sid) throws ClassNotFoundException, SQLException
	{
		return smedao.getSkillsBySId(sid);
	}
	public boolean updateSME(SME sme) throws SQLException, ClassNotFoundException
	{
		return smedao.updateSME(sme);
	}
	public boolean nominate(SME sme) throws SQLException, ClassNotFoundException
	{
		return smedao.nominate(sme);
	}
	
	
	
	
	public boolean addSkill(Skill s) throws SQLException, ClassNotFoundException
	{
		return skdao.addSkill(s);
	}
	public List<Skill> getAllSkills() throws SQLException, ClassNotFoundException
	{
		return skdao.getAllSkills();
	}
	public List<Skill> getSkillsByCId(int cid) throws SQLException, ClassNotFoundException
	{
		return skdao.getSkillsByCId(cid);
	}
	public boolean deleteSkill(int sid) throws SQLException, ClassNotFoundException
	{
		return skdao.deleteSkill(sid);
	}
	public Skill getSkillById(int id) throws ClassNotFoundException, SQLException
	{
		return skdao.getSkillById(id);
	}
	
	
	
	
	
	
	public boolean addCourse(Course c) throws SQLException, ClassNotFoundException
	{
		return cdao.addCourse(c);
	}
	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException
	{
		return cdao.getAllCourses();
	}
	public boolean deleteCourse(int cid) throws SQLException, ClassNotFoundException
	{
		return cdao.deleteCourse(cid);
	}
	public Course getCourseById(int id) throws ClassNotFoundException, SQLException
	{
		return cdao.getCourseById(id);
	}
	public Course getCourseByCName(String cname) throws ClassNotFoundException, SQLException
	{
		return cdao.getCourseByCName(cname);
	}

}
