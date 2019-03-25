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

import com.bean.SME;
import com.bean.Skill;
import com.interfaces.ICommands;
import com.interfaces.ISMEDAO;
import com.util.Conclass;

public class SMEDAO implements ISMEDAO{
	
	public boolean addSME(SME sme) throws SQLException, ClassNotFoundException, ParseException
	{
		Connection con=Conclass.getCon();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		int m;
		
		PreparedStatement ps1=con.prepareStatement(ICommands.smeAddSeq);
		ResultSet rs1=ps1.executeQuery();
		rs1.next();
		{
			m=rs1.getInt(1);
		}
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeAddInsert);
		ps.setInt(1,m);
		ps.setString(2,sme.getsFname());
		ps.setString(3,sme.getsLname());
		ps.setInt(4,sme.getsAge());
		ps.setString(5,sme.getsGender());
		ps.setString(6,sme.getsContactNumber());
		ps.setString(7, sme.getSmail());
		ps.setString(8,sme.getsUsername());
		ps.setString(9,sme.getsPassword());
		ps.setDate(10,new java.sql.Date(sdf.parse("01/01/1800").getTime()));
		ps.setDate(11,new java.sql.Date(sdf.parse("12/31/1800").getTime()));
		ps.setInt(12,sme.getsStatus());
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public boolean setSDates(Date sd,Date ed) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeSetDate);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public boolean addSSkill(int smeid,int sid) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeAddSSkill);
		ps.setInt(1,smeid);
		ps.setInt(2,sid);
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public boolean deleteSME(int smeid) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeDel);
		ps.setInt(1,smeid);
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<SME> getAllSMEs() throws SQLException, ClassNotFoundException
	{
		ArrayList<SME> li=new ArrayList<SME>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeList);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int sMEId=rs.getInt(1);
			String sFname=rs.getString(2);
			String sLname=rs.getString(3);
			int sAge=rs.getInt(4);
			String sGender=rs.getString(5);
			String sContactNumber=rs.getString(6);
			String smail=rs.getString(7);
			String sUsername=rs.getString(8);
			String sPassword=rs.getString(9);
			Date sDateOfBegin=rs.getDate(10);
			Date sDateOfEnd=rs.getDate(11);
			int sStatus=rs.getInt(12);
			
			SME p=new SME(sFname, sLname, sAge, sGender, sContactNumber, smail, sUsername, sPassword,new java.sql.Date(sDateOfBegin.getTime()) ,new java.sql.Date(sDateOfEnd.getTime()) , sMEId, sStatus);
			li.add(p);
		}
		
		return li;
	}
	
	
	public boolean updateSDates(int smeid,Date sd,Date ed) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeUpDate);
		ps.setInt(3,smeid);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public List<SME> getSMEByDates(Date sd,Date ed) throws ClassNotFoundException, SQLException
	{
		ArrayList<SME> li=new ArrayList<SME>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeGetByDates);
		ps.setDate(1,new java.sql.Date(sd.getTime()));
		ps.setDate(2,new java.sql.Date(ed.getTime()));
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int smeid=rs.getInt(1);
			
			SME p=getSMEById(smeid);
			li.add(p);
		}
		
		return li;
	}
	
	public SME getSMEByUsername(String smeuname) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeGetByUName);
		ps.setString(1,smeuname);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int sMEId=rs.getInt(1);
			String sFname=rs.getString(2);
			String sLname=rs.getString(3);
			int sAge=rs.getInt(4);
			String sGender=rs.getString(5);
			String sContactNumber=rs.getString(6);
			String smail=rs.getString(7);
			String sUsername=rs.getString(8);
			String sPassword=rs.getString(9);
			Date sDateOfBegin=rs.getDate(10);
			Date sDateOfEnd=rs.getDate(11);
			int sStatus=rs.getInt(12);
			SME p=new SME(sFname, sLname, sAge, sGender, sContactNumber, smail, sUsername, sPassword,new java.sql.Date(sDateOfBegin.getTime()) ,new java.sql.Date(sDateOfEnd.getTime()) , sMEId, sStatus);
			
			return p;
		}
		
		return null;

	}
	
	public SME getSMEById(int smeid) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeGetById);
		ps.setInt(1,smeid);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int sMEId=rs.getInt(1);
			String sFname=rs.getString(2);
			String sLname=rs.getString(3);
			int sAge=rs.getInt(4);
			String sGender=rs.getString(5);
			String sContactNumber=rs.getString(6);
			String smail=rs.getString(7);
			String sUsername=rs.getString(8);
			String sPassword=rs.getString(9);
			Date sDateOfBegin=rs.getDate(10);
			Date sDateOfEnd=rs.getDate(11);
			int sStatus=rs.getInt(12);
			SME p=new SME(sFname, sLname, sAge, sGender, sContactNumber, smail, sUsername, sPassword,new java.sql.Date(sDateOfBegin.getTime()) ,new java.sql.Date(sDateOfEnd.getTime()) , sMEId, sStatus);
			
			return p;
		}
		
		return null;

	}
	
	public List<SME> getSMEsBySkillId(int skillId) throws SQLException, ClassNotFoundException
	{

		ArrayList<SME> li=new ArrayList<SME>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeGetBySkId);
		ps.setInt(1, skillId);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int tid=rs.getInt(1);
			
			SME p=getSMEById(tid);
			li.add(p);
		}
		
		return li;
	}
	
	public List<Skill> getSkillsBySId(int sid) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		ArrayList<Skill> li=new ArrayList<Skill>();
		SkillDAO sdao=new SkillDAO();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeGetSkBySId);
		ps.setInt(1, sid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int skid=rs.getInt(1);
			
			Skill p=new Skill(skid,sdao.getSkillById(skid).getSkillName());
			li.add(p);
		}
		
		return li;

	}
	
	public boolean updateSME(SME sme) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.smeUpdate);
		
		ps.setString(1,sme.getsFname());
		ps.setString(2,sme.getsLname());
		ps.setInt(3,sme.getsAge());
		ps.setString(4,sme.getsGender());
		ps.setString(5,sme.getsContactNumber());
		ps.setString(6, sme.getSmail());
		ps.setString(7,sme.getsUsername());
		ps.setString(8,sme.getsPassword());
		ps.setDate(9, new java.sql.Date(sme.getsDateOfBegin().getTime()) );
		ps.setDate(10, new java.sql.Date(sme.getsDateOfEnd().getTime()) );
		ps.setInt(11,sme.getsStatus());
		
		ps.setInt(12, sme.getSMEId());
		
		int n=ps.executeUpdate();
		
		if(n>0)
			return true;
		
		return false;
	}
	
}
