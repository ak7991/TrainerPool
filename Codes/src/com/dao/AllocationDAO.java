//Updated as of 3:22PM, 4/1/2019

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.Trainer;
import com.interfaces.IAllocationDAO;
import com.interfaces.ICommands;
import com.util.Conclass;



public class AllocationDAO implements IAllocationDAO{
	
	public boolean addAllocation(Trainer t, int cid) throws SQLException, ClassNotFoundException
	{
		Connection con = Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aAddAlloc);
		ps.setInt(1,t.getTrainerId());
		ps.setInt(2, cid);
		ps.setDate(3, new java.sql.Date(t.gettDateOfBegin().getTime()));
		ps.setDate(4, new java.sql.Date(t.gettDateOfEnd().getTime()));
		
		int n = ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
	
		if(n>0)
			return true;
		
		return false;

	}
	
	public boolean delAllocation(Trainer t, int cid) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aDelAlloc);
		ps.setInt(1,t.getTrainerId());
		ps.setInt(2, cid);
		ps.setDate(3, new java.sql.Date(t.gettDateOfBegin().getTime()));
		ps.setDate(4, new java.sql.Date(t.gettDateOfEnd().getTime()));
		
		int n=ps.executeUpdate();
		
		//Closing resources
		con.close();
		ps.close();
	
		if(n>0)
			return true;
		
		return false;

	}
	
	public Map<String, Trainer> getAllRecords() throws SQLException, ClassNotFoundException
	{
		TrainerDAO tdao = new TrainerDAO();
		Connection con = Conclass.getCon();
		
		PreparedStatement ps = con.prepareStatement(ICommands.aList);
		ResultSet rs = ps.executeQuery();
		
		Map<String,Trainer> hmap = new HashMap<String,Trainer>();

		int i = 0;
		
		while(rs.next())
		{
			int tId = rs.getInt(1);
			int cId = rs.getInt(2);
			Date tDateOfBegin = rs.getDate(3);
			Date tDateOfEnd = rs.getDate(4);
			
			Trainer t = tdao.getTrainersById(tId).get(0);
			t.settDateOfBegin(tDateOfBegin);
			t.settDateOfEnd(tDateOfEnd);
			
			//The key of the hashmap is a string expression -> courseId + " " + (A integer from a sequence for unique key)
			hmap.put((String.valueOf(cId + " " + i)), t);
			
			 i++;
			
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		return hmap;
	}
	
	
	public HashMap<String, Trainer> getRecordsByTId(int tid) throws SQLException, ClassNotFoundException
	{
		TrainerDAO tdao = new TrainerDAO();
		Connection con = Conclass.getCon();
		PreparedStatement ps = con.prepareStatement(ICommands.aGetByTId);
		ps.setInt(1, tid);
		ResultSet rs = ps.executeQuery();
		
		HashMap<String, Trainer> programMap = new HashMap<String, Trainer>();

		while(rs.next())
		{
			int i = 0;
			
			int tId = rs.getInt(1);
			Trainer t = tdao.getTrainersById(tId).get(0);
			
			Date tDateOfBegin = rs.getDate(3);
			Date tDateOfEnd = rs.getDate(4);
			t.settDateOfBegin(tDateOfBegin);
			t.settDateOfEnd(tDateOfEnd);
			
			//The key of the hashmap is a string expression -> courseId + " " + (A integer from a sequence for unique key)
			String key = rs.getInt(2) + " " + i;
			programMap.put(key, t);
			System.out.println("Rec for: " + tId + " " + t);
			
			i++;
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return programMap;
	}
	
	public List<Trainer> getRecordsByCId(int cid) throws SQLException, ClassNotFoundException
	{
		TrainerDAO tdao = new TrainerDAO();
		
		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aGetByCId);
		ps.setInt(1, cid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int tId=rs.getInt(1);
			Date tDateOfBegin=rs.getDate(3);
			Date tDateOfEnd=rs.getDate(4);
			
			Trainer t=tdao.getTrainersById(tId).get(0);
			t.settDateOfBegin(tDateOfBegin);
			t.settDateOfEnd(tDateOfEnd);
			li.add(t);
		}
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return li;
	}

}
