package com.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bean.Course;

public interface ICourseDAO {
	
	public boolean addCourse(Course c) throws SQLException, ClassNotFoundException;
	public List<Course> getAllCourses() throws SQLException, ClassNotFoundException;
	public boolean deleteCourse(int cid) throws SQLException, ClassNotFoundException;
	public Course getCourseById(int id) throws ClassNotFoundException, SQLException;
	public Course getCourseByCName(String cname) throws ClassNotFoundException, SQLException;
	public List<Course> getCoursesByTId(int tid) throws SQLException, ClassNotFoundException;
	

}
