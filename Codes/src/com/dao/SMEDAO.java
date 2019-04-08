//Updated as of 2:14Pm 5/4/2019

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.SME;
import com.bean.Skill;
import com.interfaces.ICommands;
import com.interfaces.ISMEDAO;
import com.util.Conclass;

public class SMEDAO implements ISMEDAO{
	
	public boolean addSME(SME sme) throws SQLException, ClassNotFoundException, ParseException
	{
		Connection con = Conclass.getCon();
		int m;
		
		PreparedStatement ps1 = con.prepareStatement(ICommands.smeAddSeq);
		ResultSet rs1 = ps1.executeQuery();
		rs1.next();
		m = rs1.getInt(1);
		
		PreparedStatement ps = con.prepareStatement(ICommands.smeAddInsert);
		ps.setInt(1, m);
		ps.setString(2, sme.getsFname());
		ps.setString(3, sme.getsLname());
		ps.setInt(4, sme.getsAge());
		ps.setString(5, sme.getsGender());
		ps.setString(6, sme.getsContactNumber());
		ps.setString(7, sme.getSmail());
		ps.setString(8, sme.getsUsername());
		ps.setString(9, sme.getsPassword());
		
		/*
		 * SMEs technically SHOULD have had a field (and indeed have one) to reflect their date availability. But right now
		 * the dates are set to perpetually be null as application guidelines dictate that dates can be updated once SMEs have
		 * been nominated successfully as trainers and NOT before that. This is particularly sub-optimal as this way the admins
		 * would NOT be able to see potential trainers in a date period and nominate SMEs to match demand of trainers, hence this 
		 * column has been left intact in the database.
		 */
		if((sme.getsStatus() < 1) ){
			//New SMEs will have null inserted in their date columns.
			ps.setNull(10, java.sql.Types.DATE);
			ps.setNull(11, java.sql.Types.DATE);
		}
		else{
			//New SMEs will have null inserted in their date columns.
			ps.setNull(10, java.sql.Types.DATE);
			ps.setNull(11, java.sql.Types.DATE);	
		}
		ps.setInt(12, sme.getsStatus());
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	//Is used when an SME migrates from SMEdb to trainerdb.
	public boolean deleteSME(int smeid) throws SQLException, ClassNotFoundException
	{

		Connection con = Conclass.getCon();
		PreparedStatement ps = con.prepareStatement(ICommands.smeDel);
		ps.setInt(1, smeid);
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<SME> getAllSMEs() throws SQLException, ClassNotFoundException
	{

		Connection con = Conclass.getCon();
		PreparedStatement ps = con.prepareStatement(ICommands.smeList);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<SME> SMEList = new ArrayList<SME>();
		
		while(rs.next())
		{
			int sMEId = rs.getInt(1);
			String sFname = rs.getString(2);
			String sLname = rs.getString(3);
			int sAge = rs.getInt(4);
			String sGender = rs.getString(5);
			String sContactNumber = rs.getString(6);
			String smail = rs.getString(7);
			String sUsername = rs.getString(8);
			String sPassword = rs.getString(9);
			int sStatus = rs.getInt(12);

			SME sme = new SME(sFname, sLname, sAge, sGender, sContactNumber, smail, sUsername, sPassword, null, null, sMEId, sStatus);
			SMEList.add(sme);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return SMEList;
	}

	//Is used for fetching SME's details after they have logged in.
	public SME getSMEByUsername(String smeuname) throws ClassNotFoundException, SQLException
	{
		
		Connection con = Conclass.getCon();
		PreparedStatement ps = con.prepareStatement(ICommands.smeGetByUName);
		ps.setString(1,smeuname);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) //Username in SMEdb is unique
		{
			
			int sMEId = rs.getInt(1);
			String sFname = rs.getString(2);
			String sLname = rs.getString(3);
			int sAge = rs.getInt(4);
			String sGender = rs.getString(5);
			String sContactNumber = rs.getString(6);
			String smail = rs.getString(7);
			String Username = rs.getString(8);
			String Password = rs.getString(9);
			int sStatus = rs.getInt(12);
			//Date functionality not implemented
			//Date sDateOfBegin = rs.getDate(10);
			//Date sDateOfEnd = rs.getDate(11);
			
			SME p = new SME(sFname, sLname, sAge, sGender, sContactNumber, smail, Username, Password, null, null , sMEId, sStatus);
			
			//Closing resources
			con.close();
			ps.close();
			rs.close();
			
			return p;
		}
		else{
		
			//Closing resources
			con.close();
			ps.close();
			rs.close();
			
			return null;
		}

	}
	
	public SME getSMEById(int smeid) throws ClassNotFoundException, SQLException
	{

		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.smeGetById);
		ps.setInt(1,smeid);
		ResultSet rs = ps.executeQuery();
		
		SME sme = null;
		
		if(rs.next())
		{
			int sMEId = rs.getInt(1);
			String sFname = rs.getString(2);
			String sLname = rs.getString(3);
			int sAge = rs.getInt(4);
			String sGender = rs.getString(5);
			String sContactNumber = rs.getString(6);
			String smail = rs.getString(7);
			String sUsername = rs.getString(8);
			String sPassword = rs.getString(9);
			int sStatus = rs.getInt(12);
			
			//Date functionality not implemented
			//Date sDateOfBegin = rs.getDate(10);
			//Date sDateOfEnd = rs.getDate(11);
			
			sme = new SME(sFname, sLname, sAge, sGender, sContactNumber, smail, sUsername, sPassword, null, null, sMEId, sStatus);
			
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
			
		return sme;
	}
	
	/*
	 * Updates SME based on the basis of an sme object. The basis of update is SMEId and right now essentially updates ONLY 
	 * SME status, and could have input args as id and status of the sme but has an SME object for future extension. 
	 */
	public boolean updateSME(SME sme) throws SQLException, ClassNotFoundException
	{

		Connection con = Conclass.getCon();
		PreparedStatement ps = con.prepareStatement(ICommands.smeUpdate);
		
		ps.setString(1, sme.getsFname());
		ps.setString(2, sme.getsLname());
		ps.setInt(3, sme.getsAge());
		ps.setString(4, sme.getsGender());
		ps.setString(5, sme.getsContactNumber());
		ps.setString(6, sme.getSmail());
		ps.setString(7, sme.getsUsername());
		ps.setString(8, sme.getsPassword());
		ps.setInt(11, sme.getsStatus());
		ps.setInt(12, sme.getSMEId());
		
		//Date functionality not implemented
		//ps.setDate(9, new java.sql.Date(sme.getsDateOfBegin().getTime()));
		//ps.setDate(10, new java.sql.Date(sme.getsDateOfEnd().getTime()));
		ps.setNull(9, java.sql.Types.DATE);
		ps.setNull(10, java.sql.Types.DATE);
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	
	//===================Redundant/Unusable code/Probable Future extension===================
	/*
	 * These contain codes that:
	 * 1. Cannot be used due to fundamental flaws
	 * 2. Can be used but are very inefficient to use
	 * 3. Are not needed as requirements of the project.
	 */
	public List<SME> getSMEsBySkillId(int skillId) throws SQLException, ClassNotFoundException
	{
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.smeGetBySkId);
		ps.setInt(1, skillId);
		ResultSet rs = ps.executeQuery();
		
		ArrayList<SME> SMEList = new ArrayList<SME>();

		while(rs.next())
		{
			int SMEid = rs.getInt(1);
			SME p = getSMEById(SMEid);
			SMEList.add(p);
		}
		
		
		return SMEList;
	}
	
	public boolean updateSDates(int smeid,Date sd,Date ed) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeUpDate);
		ps.setInt(3,smeid);
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
	
	public List<Skill> getSkillsBySId(int sid) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		ArrayList<Skill> SMEList=new ArrayList<Skill>();
		SkillDAO sdao=new SkillDAO();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeGetSkBySId);
		ps.setInt(1, sid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int skid=rs.getInt(1);
			
			Skill p=new Skill(skid,sdao.getSkillById(skid).getSkillName());
			SMEList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return SMEList;

	}
	
	public List<SME> getSMEByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException
	{
		ArrayList<SME> SMEList = new ArrayList<SME>();
		
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.smeGetByDates);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			int smeid = rs.getInt(1);
			
			SME p = getSMEById(smeid);
			SMEList.add(p);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return SMEList;
	}
	
	public boolean setSDates(Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		Connection con = Conclass.getCon();
		
		//Glaring flaws in the SQL execution
		PreparedStatement ps = con.prepareStatement(ICommands.smeSetDate);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	
	public boolean addSSkill(int smeid,int skillid) throws SQLException, ClassNotFoundException
	{
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.smeAddSSkill);
		ps.setInt(1, smeid);
		ps.setInt(2, skillid);
		
		int n = ps.executeUpdate();

		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;

	}
	public boolean deleteAllSSkillsBySMEId(int smeid) throws SQLException, ClassNotFoundException
	{
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.smeDeleteAllSSkillsById);
		
		ps.setInt(1, smeid);
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
		
		if(n > 0)
			return true;
		
		return false;

	}
	
}
