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

import com.bean.Course;
import com.bean.Trainer;
import com.interfaces.IAllocationDAO;
import com.interfaces.ICommands;
import com.util.Conclass;



public class AllocationDAO implements IAllocationDAO{
	
	
	TrainerDAO tdao=new TrainerDAO();
	
	public boolean addAllocation(Trainer t, int cid) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aAddAlloc);
		ps.setInt(1,t.getTrainerId());
		ps.setInt(2, cid);
		ps.setDate(3, new java.sql.Date(t.gettDateOfBegin().getTime()));
		ps.setDate(4, new java.sql.Date(t.gettDateOfEnd().getTime()));
		
		int n=ps.executeUpdate();
		
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
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public Map<Integer,Trainer> getAllRecords() throws SQLException, ClassNotFoundException
	{
		Map<Integer,Trainer> hmap=new HashMap<Integer,Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aList);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int tId=rs.getInt(1);
			int cId=rs.getInt(2);
			Date tDateOfBegin=rs.getDate(3);
			Date tDateOfEnd=rs.getDate(4);
			
			Trainer t=tdao.getTrainersById(tId).get(0);
			t.settDateOfBegin(tDateOfBegin);
			t.settDateOfEnd(tDateOfEnd);
			hmap.put(cId,t);
			
		}
		
		return hmap;
	}
	
	
	public List<Trainer> getRecordsByTId(int tid) throws SQLException, ClassNotFoundException
	{
		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aGetByTId);
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
		
		return li;
	}
	
	public List<Trainer> getRecordsByCId(int cid) throws SQLException, ClassNotFoundException
	{
		ArrayList<Trainer> li=new ArrayList<Trainer>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.aGetByCId);
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
		
		return li;
	}

}
