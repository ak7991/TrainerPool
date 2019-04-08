//Updated as of 5:12PM 2/4/2019

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
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
		Connection con = Conclass.getCon();
		
		//Avoid duplicate Trainer ID's
		int m;
		//SQL sequence to get consecutive tra
		PreparedStatement ps1 = con.prepareStatement(ICommands.tAddSeq);
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		//Get Uniques trainer ID from a SQL sequence to avid duplicacy.
		m = rs1.getInt(1);
		
		
		PreparedStatement ps = con.prepareStatement(ICommands.tAddInsert);
		
		//Existing user update.
		if(t.getTrainerId()>0){
			ps.setInt(1,t.getTrainerId());
			ps.setDate(9, new java.sql.Date(t.gettDateOfBegin().getTime()));
			ps.setDate(10, new java.sql.Date(t.gettDateOfEnd().getTime()));
		}
		//New user SIGNUP.
		else{
			ps.setInt(1, m);
			ps.setNull(9, java.sql.Types.DATE); //Set new Date as null
			ps.setNull(10, java.sql.Types.DATE); //Set new Date as null
		}
		ps.setString(2, t.gettFname());
		ps.setString(3, t.gettLname());
		ps.setInt(4, t.gettAge());
		ps.setString(5, t.gettGender());
		ps.setString(6, t.gettContactNumber());
		ps.setString(7, t.getTmail());
		ps.setString(8, t.gettUsername());

		ps.setInt(11,t.gettStatus());
		
		/*
		 * Storing the ALREADY stored password hash into the database again. The password hash trigger MUST (/will) NOT fire will this insertion happens. 
		 * Why? - Because the trigger fires ONLY for additions and not for updates. And while firing for additions, it verifies if the status is 0 (new user),
		 * this means that this function can NEVER be called while updating a record for a new user (hence it isn't), instead updateTrainer method is invoked.
		 * Although this function is still used if a new user signs up.
		 */
		ps.setString(12, t.gettPassword());
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
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
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public boolean addTSkill(int trainerid,int skillid) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tAddTSkill);
		ps.setInt(1,trainerid);
		ps.setInt(2,skillid);
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public boolean deleteTSkill(int tid, int sid) throws SQLException, ClassNotFoundException
	{
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.tDelTSkill);
		
		ps.setInt(1, tid);
		ps.setInt(2, sid);
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n > 0)
			return true;
		
		return false;

	}
	
	public boolean deleteTrainer(Trainer t) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tDel);
		ps.setInt(1,t.getTrainerId());
		
		//User has not created yet.
		if(t.gettDateOfBegin() == null){
			ps.setNull(2, java.sql.Types.DATE);
			ps.setNull(3, java.sql.Types.DATE);
		}
		//User has created requested before.
		else{
			ps.setDate(2,new java.sql.Date(t.gettDateOfBegin().getTime()));
			ps.setDate(3,new java.sql.Date(t.gettDateOfEnd().getTime()));

		}
		
		
		int n=ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public boolean deleteTrainersById(Trainer t) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tDelById);
		ps.setInt(1,t.getTrainerId());
		
		int n=ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<Trainer> getAllTrainers() throws SQLException, ClassNotFoundException
	{
		ArrayList<Trainer> trainerList=new ArrayList<Trainer>();
		
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
			trainerList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return trainerList;
	}
	
	
	public List<Trainer> getAllActiveTrainers() throws SQLException, ClassNotFoundException
	{
		ArrayList<Trainer> trainerList=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tActiveList);
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
			trainerList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		return trainerList;
	}
	
	public boolean updateTDates(int tid,Date sd,Date ed) throws SQLException, ClassNotFoundException
	{

		Connection con = Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.tUpDate);
		ps.setInt(3,tid);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		
		int n=ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<Trainer> getTrainersByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException
	{
		ArrayList<Trainer> trainerList=new ArrayList<Trainer>();
		
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
			trainerList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return trainerList;
	}
	
	public List<Trainer> getTrainersByUsername(String tuname) throws ClassNotFoundException, SQLException
	{

		ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
		
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.tGetByUName);
		ps.setString(1, tuname);
		ResultSet rs = ps.executeQuery();
		
		Trainer p = null;
		
		while(rs.next())
		{
			int trainerId = rs.getInt(1);
			String tFname = rs.getString(2);
			String tLname = rs.getString(3);
			int tAge = rs.getInt(4);
			String tGender = rs.getString(5);
			String tContactNumber = rs.getString(6);
			String tmail = rs.getString(7);
			String tUsername = rs.getString(8);
			String tPassword = rs.getString(9);
			java.sql.Date tDateOfBegin = rs.getDate(10);
			java.sql.Date tDateOfEnd = rs.getDate(11);
			int tStatus = rs.getInt(12);
			
			//If the existing record had dates entered.
			if(tDateOfBegin != null)
				p = new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, new java.util.Date(tDateOfBegin.getTime()),  new java.util.Date(tDateOfEnd.getTime()), trainerId, tStatus);
			//If no date had been entered
			else
				p = new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, trainerId, tStatus);
			
			trainerList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return trainerList;

	}
	
	public List<Trainer> getTrainersById(int tid) throws ClassNotFoundException, SQLException
	{

		ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
		
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.tGetById);
		ps.setInt(1,tid);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int trainerId = rs.getInt(1);
			String tFname = rs.getString(2);
			String tLname = rs.getString(3);
			int tAge = rs.getInt(4);
			String tGender=rs.getString(5);
			String tContactNumber=rs.getString(6);
			String tmail = rs.getString(7);
			String tUsername = rs.getString(8);
			String tPassword = rs.getString(9);
			Date tDateOfBegin = rs.getDate(10);
			Date tDateOfEnd = rs.getDate(11);
			int tStatus = rs.getInt(12);
			
			Trainer p = new Trainer(tFname, tLname, tAge, tGender, tContactNumber, tmail, tUsername, tPassword, new java.sql.Date(tDateOfBegin.getTime()),  new java.sql.Date(tDateOfEnd.getTime()), trainerId, tStatus);
			
			trainerList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return trainerList;

	}
	
	public List<Trainer> getTrainersBySkillId(int skillId) throws SQLException, ClassNotFoundException
	{

		ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
		
		Connection con = Conclass.getCon();
		
		//The query will return a list of DISTINCT trainer id's.
		PreparedStatement ps = con.prepareStatement(ICommands.tskillAll); 
		ResultSet rs = ps.executeQuery();

		while(rs.next())
		{
			int trainerId = rs.getInt(1);
			
			//Filter results by skillId
			if(rs.getInt(2) == skillId){
				//This should NEVER throw a null pointer exception as the trainer ID's in tskill are foreign key by trainerdb's reference. 
				trainerList.addAll(getTrainersById(trainerId)); //Get ALL records (all unallocated records) [returns trainer list with non-distinct trainer ID's].
	//			System.out.println(getTrainersById(trainerId));
			}
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return trainerList;
	}
	

	public List<Skill> getSkillsByTId(int tid) throws ClassNotFoundException, SQLException
	{

		Connection con = Conclass.getCon();
		
		ArrayList<Skill> trainerList = new ArrayList<Skill>();
		SkillDAO sdao = new SkillDAO();
		
		PreparedStatement ps = con.prepareStatement(ICommands.tGetSkByTId);
		ps.setInt(1, tid);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int skid = rs.getInt(1);
			
			Skill p = new Skill(skid,sdao.getSkillById(skid).getSkillName());
			trainerList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return trainerList;

	}
	
	
	//Updates trainer record based on trainer username AND null date columns.
	public boolean updateTrainer(Trainer t) throws SQLException, ClassNotFoundException
	{

		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.tUpdate);
		
		ps.setString(1,t.gettFname());
		ps.setString(2,t.gettLname());
		ps.setInt(3,t.gettAge());
		ps.setString(4,t.gettGender());
		ps.setString(5,t.gettContactNumber());
		ps.setString(6, t.getTmail());

		ps.setInt(7,t.gettStatus());
		
		//In case trainer tries to edit their profile BEFORE creating a request, i.e. entering available dates
		if(t.gettDateOfBegin() == null || t.gettDateOfEnd() == null){
			ps.setNull(8, java.sql.Types.DATE);
			ps.setNull(9, java.sql.Types.DATE);
		}
		else{
			ps.setDate(8, new java.sql.Date(t.gettDateOfBegin().getTime()) );
			ps.setDate(9, new java.sql.Date(t.gettDateOfEnd().getTime()) );
		}
		
		ps.setString(10, t.gettUsername());
		System.out.println("Username->" + t.gettUsername());
		/*
		 * The ONLY way to update is to DELETE the record to be updated AND then add the updated record to database.
		 * This means that any and all updates will happen on records with null date values.
		 * Hence the SQL command interface has been changed to update only the records with the given trainer object's trainer
		 * ID and NULL dates.
		 */

		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		System.out.println(n);
		if(n>0)
			return true;
		
		return false;
	}
	
	
}
