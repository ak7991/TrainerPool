//Updated as of 3:22PM, 4/1/2019

package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bean.Course;
import com.interfaces.ICommands;
import com.interfaces.ICourseDAO;
import com.util.Conclass;

public class CourseDAO implements ICourseDAO{
	
	public boolean addCourse(Course c) throws SQLException, ClassNotFoundException
	{
		Connection con=Conclass.getCon();
		int m;
		
		PreparedStatement ps1=con.prepareStatement(ICommands.cAddSeq);
		ResultSet rs=ps1.executeQuery();
		rs.next();
		{
			m=rs.getInt(1);
		}
		
		PreparedStatement ps=con.prepareStatement(ICommands.cAddInsert);
		ps.setInt(1,m);
		ps.setString(2,c.getCourseName());
		
		int n=ps.executeUpdate();
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		if(n>0)
			return true;
		
		return false;

	}
	
	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException
	{
		ArrayList<Course> li=new ArrayList<Course>();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cList);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int courseId=rs.getInt(1);
			String courseName=rs.getString(2);
			Course p=new Course(courseId, courseName);
			li.add(p);
		}
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return li;
	}
	
	
	public boolean deleteCourse(int cid) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cDel);
		ps.setInt(1,cid);
		
		int n=ps.executeUpdate();
		

		//Closing resources
		con.close();
		ps.close();
		
		if(n>0)
			return true;
		
		return false;
	}
	
	public Course getCourseById(int id) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cGetById);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int courseId=rs.getInt(1);
			String courseName=rs.getString(2);
			Course p=new Course(courseId, courseName);
			return p;
		}
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return null;
	}
	
	public Course getCourseByCName(String cname) throws ClassNotFoundException, SQLException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cGetByCName);
		ps.setString(1, cname);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			int courseId=rs.getInt(1);
			String courseName=rs.getString(2);
			Course p=new Course(courseId, courseName);
			return p;
		}
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return null;

	}

	public List<Course> getCoursesByTId(int tid) throws SQLException, ClassNotFoundException
	{
		ArrayList<Course> li=new ArrayList<Course>();
		CourseDAO cdao= new CourseDAO();
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cGetByTId);
		ps.setInt(1, tid);
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			int cId=rs.getInt(1);
			Course c=cdao.getCourseById(cId);
			
			li.add(c);
		}
		

		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return li;
	}
	
	
	public int getCIdByTIdAndDates(int tid,  Date aDateBegin, Date aDateEnd ) throws SQLException, ClassNotFoundException
	{
		
		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cGetByTIdAndDates);
		
		int cId = 0;
		
		ps.setInt(1, tid);
		ps.setDate(2, new java.sql.Date(aDateBegin.getTime()));
		ps.setDate(3, new java.sql.Date(aDateEnd.getTime()));
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			cId=rs.getInt(1);
		
		//Closing resources
		con.close();
		ps.close();
		rs.close();
		
		return cId;
	}
}
