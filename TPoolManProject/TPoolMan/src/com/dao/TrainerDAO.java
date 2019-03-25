package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.Skill;
import com.bean.Trainer;
import com.interfaces.ICommands;
import com.interfaces.ITrainerDAO;
import com.util.Conclass;

public class TrainerDAO implements ITrainerDAO{
	
	public boolean addTrainer(Trainer t) throws SQLException, ClassNotFoundException, ParseException
	{
		Connection con=Conclass.getCon();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		int m;
		
		PreparedStatement ps1=con.prepareStatement(ICommands.tAddSeq);
		ResultSet rs1=ps1.executeQuery();
		rs1.next();
		{
			m=rs1.getInt(1);
		}
		
		PreparedStatement ps=con.prepareStatement(ICommands.tAddInsert);
		ps.setInt(1,m);
		ps.setString(2,t.gettFname());
		ps.setString(3,t.gettLname());
		ps.setInt(4,t.gettAge());
		ps.setString(5,t.gettGender());
		ps.setString(6,t.gettContactNumber());
		ps.setString(7, t.getTmail());
		ps.setString(8,t.gettUsername());
		ps.setString(9,t.gettPassword());
		ps.setDate(10,new java.sql.Date(sdf.parse("01/01/1800").getTime()));
		ps.setDate(11,new java.sql.Date(sdf.parse("12/31/1800").getTime()));
		ps.setInt(12,t.gettStatus());
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public boolean setTDates(Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tSetDate);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public boolean addTSkill(int tid,int sid) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tAddTSkill);
		ps.setInt(1,tid);
		ps.setInt(2,sid);
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public boolean deleteTrainer(int tid) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tDel);
		ps.setInt(1,tid);
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<Trainer> getAllTrainers() throws SQLException, ClassNotFoundException
	{
		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tList);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int trainerId=rs.getInt(1);
			String tFname=rs.getString(2);
			String tLname=rs.getString(3);
			int tAge=rs.getInt(4);
			String tGender=rs.getString(5);
			String tContactNumber=rs.getString(6);
			String tmail=rs.getString(7);
			String tUsername=rs.getString(8);
			String tPassword=rs.getString(9);
			Date tDateOfBegin=rs.getDate(10);
			Date tDateOfEnd=rs.getDate(11);
			int tStatus=rs.getInt(12);
			
			Trainer p=new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, new java.sql.Date(tDateOfBegin.getTime()),  new java.sql.Date(tDateOfEnd.getTime()), trainerId, tStatus);
			li.add(p);
		}
		
		return li;
	}
	
	
	public boolean updateTDates(int tid,Date sd,Date ed) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tUpDate);
		ps.setInt(3,tid);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<Trainer> getTrainersByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException
	{
		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tGetByDates);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int trainerId=rs.getInt(1);
			String tFname=rs.getString(2);
			String tLname=rs.getString(3);
			int tAge=rs.getInt(4);
			String tGender=rs.getString(5);
			String tContactNumber=rs.getString(6);
			String tmail=rs.getString(7);
			String tUsername=rs.getString(8);
			String tPassword=rs.getString(9);
			Date tDateOfBegin=rs.getDate(10);
			Date tDateOfEnd=rs.getDate(11);
			int tStatus=rs.getInt(12);
			
			Trainer p=new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, new java.sql.Date(tDateOfBegin.getTime()),  new java.sql.Date(tDateOfEnd.getTime()), trainerId, tStatus);
			li.add(p);
		}
		
		return li;
	}
	
	public List<Trainer> getTrainersByUsername(String tuname) throws ClassNotFoundException, SQLException
	{

		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tGetByUName);
		ps.setString(1,tuname);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int trainerId=rs.getInt(1);
			String tFname=rs.getString(2);
			String tLname=rs.getString(3);
			int tAge=rs.getInt(4);
			String tGender=rs.getString(5);
			String tContactNumber=rs.getString(6);
			String tmail=rs.getString(7);
			String tUsername=rs.getString(8);
			String tPassword=rs.getString(9);
			Date tDateOfBegin=rs.getDate(10);
			Date tDateOfEnd=rs.getDate(11);
			int tStatus=rs.getInt(12);
			
			Trainer p=new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, new java.sql.Date(tDateOfBegin.getTime()),  new java.sql.Date(tDateOfEnd.getTime()), trainerId, tStatus);
			
			li.add(p);
		}
		
		return li;

	}
	
	public List<Trainer> getTrainersById(int tid) throws ClassNotFoundException, SQLException
	{

		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tGetById);
		ps.setInt(1,tid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int trainerId=rs.getInt(1);
			String tFname=rs.getString(2);
			String tLname=rs.getString(3);
			int tAge=rs.getInt(4);
			String tGender=rs.getString(5);
			String tContactNumber=rs.getString(6);
			String tmail=rs.getString(7);
			String tUsername=rs.getString(8);
			String tPassword=rs.getString(9);
			Date tDateOfBegin=rs.getDate(10);
			Date tDateOfEnd=rs.getDate(11);
			int tStatus=rs.getInt(12);
			
			Trainer p=new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, new java.sql.Date(tDateOfBegin.getTime()),  new java.sql.Date(tDateOfEnd.getTime()), trainerId, tStatus);
			
			li.add(p);
		}
		
		return li;

	}
	
	public List<Trainer> getTrainersBySkillId(int skillId) throws SQLException, ClassNotFoundException
	{

		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tGetBySkId);
		ps.setInt(1, skillId);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int trainerId=rs.getInt(1);
			
			li.addAll(getTrainersById(trainerId));
		}
		
		return li;
	}
	

	public List<Skill> getSkillsByTId(int tid) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		ArrayList<Skill> li=new ArrayList<Skill>();
		SkillDAO sdao=new SkillDAO();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tGetSkByTId);
		ps.setInt(1, tid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int skid=rs.getInt(1);
			
			Skill p=new Skill(skid,sdao.getSkillById(skid).getSkillName());
			li.add(p);
		}
		
		return li;

	}
	
	public boolean updateTrainer(Trainer t) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tUpdate);
		
		ps.setString(1,t.gettFname());
		ps.setString(2,t.gettLname());
		ps.setInt(3,t.gettAge());
		ps.setString(4,t.gettGender());
		ps.setString(5,t.gettContactNumber());
		ps.setString(6, t.getTmail());
		ps.setString(7,t.gettUsername());
		ps.setString(8,t.gettPassword());
		ps.setDate(9, new java.sql.Date(t.gettDateOfBegin().getTime()) );
		ps.setDate(10, new java.sql.Date(t.gettDateOfEnd().getTime()) );
		ps.setInt(11,t.gettStatus());
		
		ps.setInt(12, t.getTrainerId());
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	
}
