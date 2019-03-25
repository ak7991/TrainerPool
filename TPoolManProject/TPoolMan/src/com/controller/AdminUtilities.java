package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Course;
import com.bean.SME;
import com.bean.Skill;
import com.bean.Trainer;
import com.service.ServClass;

/**
 * Servlet implementation class FilterAllocation
 */
public class AdminUtilities extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUtilities() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		//String variable for setting target for request dsipatcher
		String target = "";
		
		//Create Service object for accessing databse ops
		ServClass dbService = new ServClass();
		
		//Get previously created session.
		HttpSession hsn = request.getSession(false);
		
		//========================================================================================
		//If admin wants to filter trainers by program to be taught during a particular date period.
		if(request.getParameter("AdminAction").equals("FilterTrainers")){
			
			try {
				
				int courseId = Integer.parseInt(request.getParameter("CId"));
					
				//User HAD TO enter date in this format
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				//Parse user entered string to 'usable' format (java.util.Date)
				java.util.Date filterFromDate = sdf.parse(request.getParameter("FilterFromDate"));
				java.util.Date filterToDate = sdf.parse(request.getParameter("FilterToDate"));
					
				Course c = null;
				
				c = dbService.getCourseById(courseId);
				
				ArrayList<Skill> skillSet = new ArrayList<Skill>();
				
				skillSet = (ArrayList<Skill>) dbService.getSkillsByCId(c.getCourseId());
				
				/*
				 * A program can have multiple skill reqs, so each skill in the selected program is a key
				 * of the map, and each value corresponding to the key is list of trainer fit for that skill.
				 * Hence the perfect fit for the program to be taught would be a trainer that would be present
				 * in the intersection of all lists.				 * 
				 */
				
				HashMap<Skill, ArrayList<Trainer>> programMap = new HashMap<Skill, ArrayList<Trainer>>();
				
				
				//Getting a list of trainers eligible for the selected program during the selected date
				for(Skill s :skillSet){
					
					ArrayList<Trainer> trainerListByDate = new ArrayList<Trainer>();
					trainerListByDate = (ArrayList<Trainer>) dbService.getTrainersByDates(filterFromDate, filterToDate);
					
					hsn.setAttribute("trainerListByDate", trainerListByDate);
					
					ArrayList<Trainer> trainerListBySkill = new ArrayList<Trainer>();
					trainerListBySkill = (ArrayList<Trainer>) dbService.getTrainersBySkillId(s.getSkillId());
					
					hsn.setAttribute("trainerListBySkill", trainerListBySkill);
					
					//Only those trainers who are eligible for a 'skill' prerequisite for a program and are available at any given date
					ArrayList<Trainer> trainerList = (ArrayList<Trainer>) intersection(trainerListByDate, trainerListBySkill);
					
					programMap.put(s, trainerList);
					
				}

				hsn.setAttribute("ProgramMap", programMap);
				
				//Set target for search results
				target = "JSP/FilteredTrainers.jsp";
				
			} catch (ClassNotFoundException|SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				target = "JSP/SomethingWentWrong.jsp";
			}
		}
		
		//------------------------------------------------------------------------------------------
		//If admin wants to allocate trainer(s) to the selected program for a particular date period.
		else if(request.getParameter("AdminAction").equals("AllocateTrainers")){
	
			try {
				
				//Search parameters
				int courseId = Integer.parseInt(request.getParameter("CId"));
				//User HAD TO enter date in this format
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				//Parse user entered string to 'usable' format (java.util.Date)
				java.util.Date filterFromDate = sdf.parse(request.getParameter("FilterFromDate"));
				java.util.Date filterToDate = sdf.parse(request.getParameter("FilterToDate"));
				
				Course c = null;
				//Get course which the administrator wants to allocate trainers to.
				c = dbService.getCourseById(courseId);
				
				//Get Trainer ID's of trainers whose checkbox was checked
				String []trainerIdList = request.getParameterValues("UpdateAllocation");

				ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
				
				//Getting arraylist of trainer objects whose status has to be altered (from String array created via checkbox values.
				for(String trainerId :trainerIdList){
							
					//Getting trainer(s) by ID 
					ArrayList<Trainer> trainerListById = (ArrayList<Trainer>) dbService.getTrainerById(Integer.parseInt(trainerId));
					//Getting trainer(s) by date availabilty
					ArrayList<Trainer> trainerListByDates = (ArrayList<Trainer>) dbService.getTrainersByDates(filterFromDate, filterToDate);
					
					/*
					 * Intersection of the above two lists will yield the record of a particular trainer who is free for the period
					 * specified. Although the method used to add to trainerList is .addAll, the intersection HAS to return 
					 * only a singular trainer.
					*/
					trainerList.addAll((ArrayList<Trainer>) intersection(trainerListById, trainerListByDates));
					
				}
						
				//Assuming that update will go through entirely.
				int unsuccessfulUpdate = 0;
						
				//Updating database to reflect the altered allocation status AND allocation date of trainers.
				for(Trainer t :trainerList){
							
					/*
					 * The date availability of the trainer has to be altered according to the allocated course's duration.
					 * For this the original date of availability of trainer is split in three parts after allocation of a course.
					 * Part one is the available date period before the allocated course's start.
					 * Part two is now unavailable for allocation and coincides the allocated course's duration.
					 * Part three is the available date period after the allocated course's end.
					 * Eg: If a trainer entered his date of availabilty as 1st March to 30th March and an Administrator
					 * allocates the trainer a course from 15th March to 20th March. Then this allocation splits the 
					 * aforementioned trainer's single entry into 3 entries, one before and one after the course which are readded to 
					 * unallocated trainers' Database (TrainerDb) while the middle entry of blocked date period is moved to allocated
					 * trainers' database (AllocationDb).
					 */
							
					//Date period split 1 (available before course)
					Date fromDate1 = t.gettDateOfBegin();
					Date toDate1 = filterFromDate;
					//Date period split 2 (blocked for course)
					Date fromDate2 = filterFromDate;
					Date toDate2 = filterToDate;
					//Date period split 3 (available after course)
					Date fromDate3 = filterToDate;
					Date toDate3 = t.gettDateOfEnd();
					
					//Split part 1 update to trainerDB
					Trainer temp1 = t;
					temp1.settDateOfBegin(fromDate1);
					temp1.settDateOfEnd(toDate1);
					temp1.settStatus(1); //Allocated to a course
					
					//Split part 2 update to trainerDB
					Trainer temp2 = t;
					temp1.settDateOfBegin(fromDate2);
					temp1.settDateOfEnd(toDate2);
					temp1.settStatus(1); //Allocated to a course
							
					//Split part 3 update trainerDB
					Trainer temp3 = t;
					temp3.settDateOfBegin(fromDate3);
					temp3.settDateOfEnd(toDate3);
					temp3.settStatus(1); //Allocated to a course
					
					try{
						//Execute update(s)
						dbService.updateTrainer(temp1);
						dbService.addAllocation(temp2, c.getCourseId());
						dbService.updateTrainer(temp3);
					}
					//If update didn't go through
					catch(SQLException e){
						//Increase integer to reflect number of unsuccessful updates
						e.printStackTrace();
						unsuccessfulUpdate += 1;
					}

				}

				hsn.setAttribute("UnsuccessfulUpdates", unsuccessfulUpdate);
				target = "JSP/Allocation.jsp";
				
				} 
			catch (NumberFormatException | ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				target = "JSP/UnsuccessfulAllocation.jsp";
			}
	
		}
		
		/*
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		//If admin wants to delete allocation of trainer(s) to the selected program for a particular date period.
		else if(request.getParameter("AdminAction").equals("UnallocateTrainers")){
	
			try {
				
				int courseId = Integer.parseInt(request.getParameter("CId"));
				Course c = null;
				//Get course which the administrator wants to unallocate trainers to.
				c = dbService.getCourseById(courseId);
				
				//Get Trainer ID's of trainers whose checkbox was checked
				String []trainerIdList = request.getParameterValues("UpdateAllocation");
				//User HAD TO enter date in this format
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				//Parse user entered string to 'usable' format (java.util.Date)
				java.util.Date filterFromDate = sdf.parse(request.getParameter("FilterFromDate"));
				java.util.Date filterToDate = sdf.parse(request.getParameter("FilterToDate"));
						
				ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
				
				//Getting arraylist of trainer objects whose status has to be altered (from String array created via checkbox values.
				for(String trainerId :trainerIdList){
							
					Trainer t = dbService.getTrainerById(Integer.parseInt(trainerId));
					trainerList.add(t);
					
				}
						
				//Assuming that update will go through entirely.
				int unsuccessfulUpdate = 0;
						
				//Updating database to reflect the altered allocation status AND allocation date of trainers.
				for(Trainer t :trainerList){
							
					
					 * The date availability of the trainer has to be altered according to the unallocated course's duration.
					 * For this the original date of availability of trainer is merged back from three parts after allocation
					 * of a course. Part one is the available date period before the allocated course's start.
					 * Part two is now available for allocation and coincides the allocated course's duration.
					 * Part three is the available date period after the allocated course's end.
					 * Eg: If a trainer entered his date of availabilty as 1st March to 30th March and an Administrator
					 * allocates the trainer a course from 15th March to 20th March. Then this allocation splits the 
					 * aforementioned trainer's single entry into 3 entries, one before and one after the course which are readded to 
					 * unallocated trainers' Database (TrainerDb) while the middle entry of blocked date period is moved to allocated
					 * trainers' database (AllocationDb). Now the removal of the above allocation will result in merging back of
					 * available dates before the course, during the course he was allocated to and afte the course.
					 
							
					//All entries of a particular trainer have to be in ascending order of the start date record.
					TreeSet<Trainer> trainerSetByBeginDate = new TreeSet<Trainer>();
					trainerSetByBeginDate.add(t);
					//Date period split 1 (available before course)
					Date fromDate1 = t.gettDateOfBegin();
					Date toDate1 = filterFromDate;
					//Date period split 2 (blocked for course)
					Date fromDate2 = filterFromDate;
					Date toDate2 = filterToDate;
					//Date period split 3 (available after course)
					Date fromDate3 = filterToDate;
					Date toDate3 = t.gettDateOfEnd();
					
					//Split part 1 update to trainerDB
					Trainer temp1 = t;
					temp1.settDateOfBegin(fromDate1);
					temp1.settDateOfEnd(toDate1);
					temp1.settStatus(t.gettStatus() + 1); //Allocated to one (more) course
					
					//Split part 2 update to trainerDB
					Trainer temp2 = t;
					temp2.settDateOfBegin(fromDate2);
					temp2.settDateOfEnd(toDate2);
					temp2.settStatus(t.gettStatus() + 1); //Allocated to one (more) course
							
					//Split part 3 update trainerDB
					Trainer temp3 = t;
					temp3.settDateOfBegin(fromDate3);
					temp3.settDateOfEnd(toDate3);
					temp3.settStatus(t.gettStatus() + 1); //Allocated to one (more) course
					
					try{
						//Execute update(s)
						dbService.updateTrainer(temp1);
						dbService.addAllocation(temp2, c.getCourseId());
						dbService.updateTrainer(temp3);
					}
					//If update didn't go through
					catch(SQLException e){
						//Increase integer to reflect number of unsuccessful updates
						e.printStackTrace();
						unsuccessfulUpdate += 1;
					}

				}

				hsn.setAttribute("UnsuccessfulUpdates", unsuccessfulUpdate);
				target = "JSP/Allocation.jsp";
				
				} 
			catch (NumberFormatException | ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				target = "JSP/SomethingWentWrong.jsp";
			}
	
		}*/
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		//If admin wants to filter SME's by program to be taught during a particular date period.
		else if(request.getParameter("AdminAction").equals("FilterSMEs")){
			try {
				
				int courseId = Integer.parseInt(request.getParameter("CId"));
					
				//User HAD TO enter date in this format
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				//Parse user entered string to 'usable' format (java.util.Date)
				java.util.Date filterFromDate = sdf.parse(request.getParameter("FilterFromDate"));
				java.util.Date filterToDate = sdf.parse(request.getParameter("FilterToDate"));
					
				Course c = null;
				
				c = dbService.getCourseById(courseId);
				
				ArrayList<Skill> skillSet = new ArrayList<Skill>();
				
				skillSet = (ArrayList<Skill>) dbService.getSkillsByCId(c.getCourseId());
				
				/*
				 * A program can have multiple skill reqs, so each skill in the selected program is a key
				 * of the map, and each value corresponding to the key is list of trainer fit for that skill.
				 * Hence the perfect fit for the program to be taught would be a trainer that would be present
				 * in the intersection of all lists. 
				 */
				
				HashMap<Skill, ArrayList<SME>> programMap = new HashMap<Skill, ArrayList<SME>>();
				
				
				//Getting a list of trainers eligible for the selected program during the selected date
				for(Skill s :skillSet){
					
					ArrayList<SME> SMEListByDate = new ArrayList<SME>();
					SMEListByDate = (ArrayList<SME>) dbService.getSMEByDates(filterFromDate, filterToDate);
					
					ArrayList<SME> SMEListBySkill = new ArrayList<SME>();
					SMEListBySkill = (ArrayList<SME>) dbService.getSMEsBySkillId(s.getSkillId());
					
					//Only those SME who are eligible for a 'skill' prerequisite for a program and are available at any given date
					ArrayList<SME> SMEList = (ArrayList<SME>) intersection(SMEListByDate, SMEListBySkill);
					
					programMap.put(s, SMEList);
					
				}

				hsn.setAttribute("ProgramMap", programMap);
				
				//Set target for search results
				target = "JSP/FilteredSMEs.jsp";
				
			} catch (ClassNotFoundException|SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				target = "JSP/SomethingWentWrong.jsp";
			}
		}
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//If admin wants to nominate SMEs to the selected program for a particular date period.
		else if(request.getParameter("AdminAction").equals("NominateSMEs")){
	
			try {
				
				//Get Trainer ID's of trainers whose checkbox was checked
				String []SMEIdList = request.getParameterValues("UpdateNomination");
						
				ArrayList<SME> SMEList = new ArrayList<SME>();
				
				//Getting arraylist of trainer objects whose status has to be altered (from String array created via checkbox values.
				for(String SMEId :SMEIdList){
							
					SME s = dbService.getSMEById(Integer.parseInt(SMEId));
					SMEList.add(s);
					
				}
						
				//Assuming that update will go through entirely.
				int unsuccessfulUpdate = 0;
						
				//Updating database to reflect the altered allocation status AND allocation date of trainers.
				for(SME s :SMEList){
							
					/*
					 * SMEs have to be nominated not to a course or to a date period BUT they have to be filtered by that.
					 * This is because SMEs can be seen potential trainers, hence the admin needs to be able to monitor
					 * the potential supply of trainers for a particular date/course requirement, although he can allocate
					 * them to that requirement after they have become trainers.
					 */
					
					//Set SME status to 2 if nominated as trainer
					SME temp1 = s;
					temp1.setsStatus(2); //Nominated as trainer
					
					try{
						//Execute update(s)
						dbService.updateSME(temp1);
					}
					//If update didn't go through
					catch(SQLException e){
						//Increase integer to reflect number of unsuccessful updates
						e.printStackTrace();
						unsuccessfulUpdate += 1;
					}

				}

				hsn.setAttribute("UnsuccessfulUpdates", unsuccessfulUpdate);
				target = "JSP/Allocation.jsp";
				
				} 
			catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				target = "JSP/UnsuccessfulNomination.jsp";
			}
	
		}
		
		//Forward to appropriate target
		RequestDispatcher userNav = request.getRequestDispatcher(target);
		userNav.forward(request, response);
		
		//Close all opened resources
		pw.close();
		
	}
	
	//Function for returning intersection of 2 list objects
	 public <T> List<T> intersection(List<T> list1, List<T> list2) {
	        List<T> list = new ArrayList<T>();

	        for (T t : list1) {
	            if(list2.contains(t)) {
	                list.add(t);
	            }
	        }

	        return list;
	    }

	 /*
	  * This function is for determining whether a collection of  pairs of 'From' and 'To' date can be merged or not if yes, then 
	  * it will return a arrayList with the first element as 'From' date of the first date pair (given) and the second (last) 
	  * element as the 'To' date of the last pair (given). 
	  * Eg: dateMap = [{1, (1/1/2000, 3/1/2000)}, {2, (3/1/2000, 5/1/2000)}, {3, (5/1/2000, 9/1/2000)}]
	  * then the function returns (1/1/2000, 9/1/2000).
	  * Note: For the function to work properly EACH date pair has to have the first element LESSER than OR EQUAL to the second.
	  */
	 public Date[] checkAdjacencyOfDates(HashMap<Integer, Date[]> dateMap){
		 
		 //This function needs a hashmap that has consequetive integers as keys to each date pair
		 
		 //For iterating the date map
		 Set<Integer> keySet  = dateMap.keySet();
		 
		 boolean adjacency = true;
		 
		 for(int i :keySet){
			 
			 if(i == (keySet.size()-1))
				 break;
			 //Checking continuity
			 if (dateMap.get(i)[1].compareTo(dateMap.get(i+1)[0]) == 0)
				 adjacency = true;
			 else{
				 adjacency = false;
			 }
				 
			 
		 }
		 
		 Date [] merged = {dateMap.get(1)[0], dateMap.get(keySet.size()-1)[1]}; 
		if(adjacency)
			return merged;
		else
			return null;
		
	 }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
