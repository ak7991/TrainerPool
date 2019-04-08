//Updated as of 2:20PM 4/4/2019

package com.interfaces;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bean.Trainer;

public interface IAllocationDAO {
	
	public boolean addAllocation(Trainer t, int cid) throws SQLException, ClassNotFoundException;
	public boolean delAllocation(Trainer t, int cid) throws SQLException, ClassNotFoundException;
	public Map<String, Trainer> getAllRecords() throws SQLException, ClassNotFoundException;
	public HashMap<String, Trainer> getRecordsByTId(int tid) throws SQLException, ClassNotFoundException;
	public List<Trainer> getRecordsByCId(int cid) throws SQLException, ClassNotFoundException;

}
