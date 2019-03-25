package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		ResultSet rs1=ps1.executeQuery();
		rs1.next();
		{
			m=rs1.getInt(1);
		}
		
		PreparedStatement ps=con.prepareStatement(ICommands.cAddInsert);
		ps.setInt(1,m);
		ps.setString(2,c.getCourseName());
		
		int n=ps.executeUpdate();
		
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
		
		return li;
	}
	
	
	public boolean deleteCourse(int cid) throws SQLException, ClassNotFoundException
	{

		Connection con=Conclass.getCon();
		
		PreparedStatement ps=con.prepareStatement(ICommands.cDel);
		ps.setInt(1,cid);
		
		int n=ps.executeUpdate();
		
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
		
		return li;
	}
	

}
