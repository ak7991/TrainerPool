//Updated as of 3:22PM, 4/1/2019

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Skill;
import com.interfaces.ICommands;
import com.interfaces.ISkillDAO;
import com.util.Conclass;

public class SkillDAO implements ISkillDAO{
	
	public boolean addSkill(Skill s) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		int m;
		
		PreparedStatement ps1=con.prepareStatement(ICommands.sAddSeq);
		ResultSet rs=ps1.executeQuery();
		rs.next();
		{
			m=rs.getInt(1);
		}
		
		PreparedStatement ps=con.prepareStatement(ICommands.sAddInsert);
		ps.setInt(1,m);
		ps.setString(2,s.getSkillName());
		
		int n=ps.executeUpdate();
		

		//Closing resources
		con.close();
		ps.close();
		ps1.close();
		rs.close();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public List<Skill> getAllSkills() throws SQLException, ClassNotFoundException
	{
		ArrayList<Skill> li=new ArrayList<Skill>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.sList);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int skillId=rs.getInt(1);
			String skillName=rs.getString(2);
			Skill p=new Skill(skillId, skillName);
			li.add(p);
		}
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return li;
	}
	
	public List<Skill> getSkillsByCId(int cid) throws SQLException, ClassNotFoundException
	{
		ArrayList<Skill> li=new ArrayList<Skill>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.sGetByCId);
		ps.setInt(1, cid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int skillId=rs.getInt(1);
			Skill p=getSkillById((skillId));
			li.add(p);
		}
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return li;
	}
	
	public boolean deleteSkill(int sid) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.sDel);
		ps.setInt(1,sid);
		
		int n=ps.executeUpdate();
		

		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}

	public Skill getSkillById(int id) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.sGetById);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int skillId=rs.getInt(1);
			String skillName=rs.getString(2);
			Skill p=new Skill(skillId, skillName);

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
	
	public Skill getSkillBySkName(String skname) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.sGetBySkNAme);
		ps.setString(1, skname);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int skillId=rs.getInt(1);
			String skillName=rs.getString(2);
			Skill p=new Skill(skillId, skillName);
			
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
}
