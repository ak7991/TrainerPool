//Updated as of 3:22PM, 4/1/2019

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
import com.comparator.TDateOfBeginComparator;
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
		String userType = (String) hsn.getAttribute("User");
			
		//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
		//(#1.0)Redirecting to appropriate targets.  ---->  (only for) Navigation Bar
		if (request.getParameter("Action") != null) {
			
			//Logged in user MUST be an admin
			if(userType.equals("Admin")){
			
				//System.out.println("Here via navigation bar");
				if(request.getParameter("Action").equals("AllocateTrainers")){
					target = "JSP/Allocation.jsp";
				}
				else if(request.getParameter("Action").equals("UnallocateTrainers")){
					target = "/JSP/Unallocation.jsp";
					System.out.println(target);
				}
				else if (request.getParameter("Action").equals("CalendarList")) {
					
					target = "JSP/Calendar.jsp";
				}
				else if (request.getParameter("Action").equals("NominateSMEs")) {
					target = "JSP/NominateSMEs.jsp";	
				}
				else if (request.getParameter("Action").equals("Home")) {
					target = "JSP/AdminDashboard.jsp";	
				}
				else if (request.getParameter("Action").equals("LogOut")) {
					
					hsn.setAttribute("LoginStatus", false); //Set login status attribute to false to avoid authentication bypass after logout
					
					hsn.invalidate();
					hsn = null;
					request.getSession().invalidate();
					System.gc();
					Runtime.getRuntime().gc();
					target = "JSP/AdminLogin.jsp";	
				}
			}
			//For inducing errors on pages
			else if(request.getParameter("Action").equals("SomethingWentWrong")){

				target = "JSP/SomethingWentWrong.jsp";
			}
		}
		
		
		//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
		//(#2.0)Admin Utilities (FilterTrainers, AllocateTrainers, UnallocateTrainers, GetAllSMEs, NominateSMEs)
		if(request.getParameter("AdminAction") != null){
			
			//AdminAction parameter is defined ONLY in pages accessed by administrators.
			
			//========================================================================================
			//(#2.1)If admin wants to filter trainers by program to be taught during a particular date period.
			if(request.getParameter("AdminAction").equals("FilterTrainers")){

				try {
					
					int courseId = Integer.parseInt(request.getParameter("CId"));
						
					//User HAD TO enter date in this format
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
					//Parse user entered string to 'usable' format (java.util.Date)
					java.util.Date filterFromDate = sdf.parse(request.getParameter("FilterFromDate"));
					java.util.Date filterToDate = sdf.parse(request.getParameter("FilterToDate"));
					
					//Set search parameters to session for backed allocation
					hsn.setAttribute("CId", courseId);
					hsn.setAttribute("FilterFromDate", filterFromDate);
					hsn.setAttribute("FilterToDate", filterToDate);
					
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
					
					System.out.println(skillSet);
					
					//Getting a list of trainers eligible for the selected program during the selected date
					for(Skill s :skillSet){
						
						ArrayList<Trainer> trainersListByDate = new ArrayList<Trainer>();
						trainersListByDate = (ArrayList<Trainer>) dbService.getTrainersByDates(filterFromDate, filterToDate);

						ArrayList<Trainer> trainersListBySkill = new ArrayList<Trainer>();
						trainersListBySkill = (ArrayList<Trainer>) dbService.getTrainersBySkillId(s.getSkillId());
						System.out.println(trainersListBySkill);
						//Only those trainers who are eligible for a 'skill' prerequisite for a program and are available at any given date
						ArrayList<Trainer> trainerList = trainersListByDate;
						trainerList.retainAll(trainersListBySkill); //Intersection of skill filtered list and date filtered list.
						
						programMap.put(s, trainerList);
						
					}

					System.out.println(programMap);
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
			//(#2.2)If admin wants to allocate trainer(s) to the selected program for a particular date period.
			else if(request.getParameter("AdminAction").equals("AllocateTrainers")){
		
				try {
					
					//Search parameters
					//These parameters were stored in session attribute in FilterTrainer module
					int courseId = (int) hsn.getAttribute("CId");
					//Parse user entered string to 'usable' format (java.util.Date)
					java.util.Date filterFromDate = (Date) hsn.getAttribute("FilterFromDate");
					java.util.Date filterToDate = ((Date) hsn.getAttribute("FilterToDate"));
					
					//With the above given search parameters, all that is needed is trainers who are to be allocated.
					
					Course c = null;
					//Get course which the administrator wants to allocate trainers to.
					c = dbService.getCourseById(courseId);
					
					//Get list of trainers whose checkbox was checked
					String []trainerIdList = request.getParameterValues("UpdateAllocation");
				
					ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
					
					//Getting arraylist of trainer objects whose status has to be altered (from String array created via checkbox values).
					for(String trainerId :trainerIdList){
								
						//Getting trainer(s) by ID 
						ArrayList<Trainer> trainerListById = (ArrayList<Trainer>) dbService.getTrainersById(Integer.parseInt(trainerId));
						//Getting trainer(s) by date availabilty (session attribute)
						ArrayList<Trainer> trainerListByDates = (ArrayList<Trainer>) dbService.getTrainersByDates(filterFromDate, filterToDate);
						
						/*
						 * Intersection of the above two lists will yield the record of a particular trainer who is free for the period
						 * specified. Although the method used to add to trainerList is .addAll, the intersection HAS to add 
						 * only a singular trainer.
						*/
						trainerListById.retainAll(trainerListByDates);
						trainerList.addAll((ArrayList<Trainer>) trainerListById);
						
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
						
						//(Split part 1)  to be updated to trainerDB
						Trainer temp1 = new Trainer();
						temp1 = t.clone();
						temp1.settDateOfBegin(fromDate1);
						temp1.settDateOfEnd(toDate1);
								
						//(Split part 3) to be updated to trainerDB
						Trainer temp3 = new Trainer();
						temp3  = t.clone();
						temp3.settDateOfBegin(fromDate3);
						temp3.settDateOfEnd(toDate3);
						
						//(Split part 2) to be updated to AllocationDB
						Trainer temp2 = new Trainer();
						temp2 = t.clone();
						temp2.settDateOfBegin(fromDate2);
						temp2.settDateOfEnd(toDate2);
						
						
						try{
							
							//Update Status of all trainer records to reflect newly allocated course
							int newStatus = t.gettStatus() + 1;
							temp1.settStatus(newStatus);
							temp2.settStatus(newStatus); //This doesn't matter, added just for code consistency.
							temp3.settStatus(newStatus);
							
							//Execute update(s)
							dbService.addAllocation(temp2, c.getCourseId());
							
							//Deletes existing record
							dbService.deleteTrainer(t);
							
							dbService.addTrainer(temp1);
							dbService.updateTrainer(temp1);
							
							dbService.addTrainer(temp3);
							dbService.updateTrainer(temp3);

							System.out.println(t);
							System.out.println(temp1);
							System.out.println(temp2);
							System.out.println(temp3);
						}
						
						//If update didn't go through
						catch(SQLException | ParseException e){
							//Increase integer to reflect number of unsuccessful updates
							e.printStackTrace();
							unsuccessfulUpdate += 1;
						}

					}

					hsn.setAttribute("UnsuccessfulUpdates", unsuccessfulUpdate);
					target = "JSP/SuccessfulAllocation.jsp";
					
					} 
				catch (NumberFormatException | ClassNotFoundException | SQLException | CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					target = "JSP/SomethingWentWrong.jsp";
				}
		
			}

			//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			//If admin wants to delete allocation of trainer(s) to the selected program for a particular date period.
			else if(request.getParameter("AdminAction").equals("UnallocateTrainers")){
		
				try {
					
					//Get Trainer ID's of trainers whose checkbox was checked
					String []trainerIdList = request.getParameterValues("UpdateAllocation");
							
					ArrayList<Trainer> trainerList = new ArrayList<Trainer>();
					
					//Getting arraylist of trainer objects whose status has to be altered (from String array created via checkbox values.
					for(int i = 0; i < trainerIdList.length; i++){
						int courseId = Integer.parseInt(trainerIdList[i].split("&")[1]);
						HashMap<String, Trainer> tempMapByTId = (HashMap<String, Trainer>) dbService.getRecordsByTId(Integer.parseInt(trainerIdList[i].split("&")[0]));
						ArrayList<Trainer> tempListByCId = (ArrayList<Trainer>) dbService.getRecordsByCId(courseId);
						System.out.println("Recs by Tid" + tempMapByTId + "end TId");
						System.out.println("Recs by Cid" + tempListByCId + "end cId");
						System.out.println(courseId);
						//Get all records for a given tid.
						ArrayList<Trainer> tempListByTId = new ArrayList<Trainer>();
						Set<String> kset = tempMapByTId.keySet();
						for(String s :kset)
							tempListByTId.add(tempMapByTId.get(s));
						tempListByCId.retainAll(tempListByTId);	//Will return a singular trainer object in a list.
						System.out.println("After intersection " + tempListByCId);
						trainerList.addAll(tempListByCId);
						
					}
							
					System.out.println(trainerIdList.length);
					System.out.println(trainerList.size());
					//Updating database to reflect the altered allocation status AND allocation date of trainers.
					for(int i = 0; i < trainerList.size(); i++){
						System.out.println("TrainerIDList" + trainerIdList[i]);

						System.out.println(trainerIdList[i].split("&")[1]);
						int courseId = Integer.parseInt(trainerIdList[i].split("&")[1]);
						Trainer t = trainerList.get(i);
						/*
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
						*/ 
						
						//Deleting allocation
						dbService.delAllocation(t, courseId);
						
						//All entries of a particular trainer (from trainerDB i.e. unallocated entries).
						ArrayList<Trainer> checkForMerge = (ArrayList<Trainer>) dbService.getTrainersById(t.getTrainerId());
//						System.out.println("these will be merge-checked" + checkForMerge + "end of merge-check");
						//Get the unallocated entries to be merged (deleted and inserted back) with the allocation record in trainerDB.
						ArrayList<Trainer> mergeThis = checkAdjacencyOfTrainerDates(t, checkForMerge);

//						System.out.println("merging these entries" + mergeThis + "end of merged entries");
						
						//All deleted entries will be merged and replaced by THIS trainer object.
						Trainer mergedTrainer = null;
						
						/*
						 * This means that there is an unallocated period of the trainer right before AND right after the
						 *allocation that was just deleted (from allocationDB). So now, both records in the mergeThis array
						 *will be deleted from the trainerDB, merged with allocated record and inserted back (into trainerDB). 
						 */
						
						//Delete adjacent records
						for(Trainer toBeDeleted :mergeThis){
//							System.out.println("toBeDeleted" + toBeDeleted + "end toBeDeleted");
							boolean b = dbService.deleteTrainer(toBeDeleted);
							System.out.println(b);
						}	

						if(mergeThis.size() == 2){
							
							
							Collections.sort(mergeThis, new TDateOfBeginComparator());
							mergedTrainer = mergeThis.get(0);
							mergedTrainer.settDateOfEnd(mergeThis.get(1).gettDateOfEnd());
							
						}
						/*
						 * This means that there is EITHER a(n) (unallocated) record just before the allocated record OR just 
						 * after it, but not both. So after checking the case scenario, appropriate record will be deleted and
						 * merged (with the preivously deleted allocation record) and inserted back)
						 */
						else if(mergeThis.size() == 1){
							
							if(mergeThis.get(0).gettDateOfEnd().equals(t.gettDateOfBegin())){
								mergedTrainer = mergeThis.get(0);
								mergedTrainer.settDateOfEnd(t.gettDateOfEnd());
							}
							if(mergeThis.get(0).gettDateOfBegin().equals(t.gettDateOfEnd())){
								mergedTrainer = mergeThis.get(0);
								mergedTrainer.settDateOfBegin(t.gettDateOfBegin());
							}
							
						}
						/*
						 * This means that there is no (unallocated) record adjacent to the allocation record deleted, hence
						 * it will be added to the trainerDB as is. 
						 */
						else
							mergedTrainer = t;
						
						//Execute update(s)

						//Add merged record
						dbService.addTrainer(mergedTrainer);
						dbService.updateTrainer(mergedTrainer);

					}

					target = "JSP/Unallocation.jsp";
					
					} 
				catch (NumberFormatException | ClassNotFoundException | SQLException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					target = "JSP/SomethingWentWrong.jsp";
				}
		
			}
			
			//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			//If admin wants to filter SME's by program to be taught during a particular date period.
			else if (request.getParameter("AdminAction").equals("GetAllSMEs")){
				
				try {

					ArrayList<SME> SMEList = new ArrayList<SME>();
					SMEList = (ArrayList<SME>) dbService.getAllSMEs();
					
					//Set list of all SMEs as an attribute of the session.
					hsn.setAttribute("SMEList", SMEList);
					
					target = "SMENomination";
				}
				catch(SQLException | ClassNotFoundException e){
					target = "JSP/SomethingWentWrong.jsp";
				}
				
			}
			
			//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
			//If admin nominates any SME, then update the status in the SME database
			else if (request.getParameter("AdminAction").equals("NominateSMEs")) {
				
				try{

					//Get SME ID's of SMEs whose checkbox was checked
					String []SMEIdList = request.getParameterValues("UpdateNomination");			
					ArrayList<SME> SMEList = new ArrayList<SME>();
					
					//Get array list of sme objects via a string array of SME ID's obtained via checkbox values.
					for(String SMEId :SMEIdList)					
						SMEList.add(dbService.getSMEById(Integer.parseInt(SMEId)));
	
					//Update SMEs' status
					for(SME s :SMEList){
						s.setsStatus(2); //Nominated SME's status changed to 2. 
						dbService.updateSME(s);
					}
					target = "JSP/Nomination.jsp";
				}
				catch(SQLException | ClassNotFoundException e){
					e.printStackTrace();
					target = "SomethingWentWrong.jsp";
				}
			}
			//For inducing errors on pages
			else if(request.getParameter("AdminAction").equals("SomethingWentWrong")){
				target = "JSP/SomethingWentWrong.jsp";
			}
			
		}
		
//		System.out.println(target);
		//Forward to appropriate target
		RequestDispatcher userNav = request.getRequestDispatcher(target);
		userNav.forward(request, response);

		//Close all opened resources
		pw.close();
		
	}

	 /*
	  * <---------------------------------------------Date Merge functions<---------------------------------------------
	  * 
	  * These function are for determining whether any object in a given collection of objects having pairs of 'From' and 'To' 
	  * date can be merged with another object (of the same type) or not. 
	  * 
	  * Dates in examples are (dd/MM/yyyy)
	  * Eg #1: 
	  * Object given -> (3/1/2000, 5/1/2000)
	  * dates mapped to objects of collection given -> [{1, (1/1/2000, 3/1/2000)},  {2, (5/1/2000, 9/1/2000)}, {3, (13/1/2000, 7/1/2000)]
	  * then the function returns [{1, (1/1/2000, 3/1/2000)},  {2, (5/1/2000, 9/1/2000)}]. 
	  * Explanation -> This is returned because the 1 and 2 objects have their to and from dates coinciding with given object's
	  *  from and to date (RESPECTIVELY).
	  * 
	  * Eg #2: 
	  * Object given -> (3/1/2000, 5/1/2000)
	  * dates mapped to objects of collection given -> [{1, (1/1/2000, 3/1/2000)},  {2, (10/1/2000, 15/1/2000), {3, (7/1/2000, 10/1/2000)}]
	  * then the function returns [{1, (1/1/2000, 3/1/2000)}]. 
	  * Explanation -> This is returned because the only object whose to/from date matched with the from/to date of the given 
	  * object was object 1. NOTE that there IS a couple of pair of dates (object 2 and 3) which can be merged but aren't.
	  * 
	  * Eg #3: 
	  * Object given -> (13/2/2000, 25/2/2000)
	  * dates mapped to objects of collection given -> [{1, (1/1/2000, 3/1/2000)},  {2, (10/1/2000, 15/1/2000), {3, (7/1/2000, 10/1/2000)}]
	  * then the function returns [].
	  * 
	  * Note: For the function to work properly EACH date pair of all passed objects have to have the first element LESSER 
	  * than (or equal to) the second.
	  */
	 public ArrayList<Trainer> checkAdjacencyOfTrainerDates(Trainer t, ArrayList<Trainer> trainerList){
		
		 ArrayList<Trainer> returnList = new ArrayList<Trainer>();
		 
		 for(Trainer temp :trainerList){
			 if(t.gettDateOfBegin().equals(temp.gettDateOfEnd())){
				//Ensuring that the trainer object that has the begin date same as the end dateof the trainer object passed is added to the list at index 0
				 returnList.add(0,  temp);  
//				 System.out.println("added " + temp);
			 }
			 else if(t.gettDateOfEnd().equals(temp.gettDateOfBegin())){
				 returnList.add(temp);	
//				 System.out.println("added " + temp);
			 }
		 }
		 return returnList;

	 }
	 //Future functionality
	 public ArrayList<SME> checkAdjacencyOfSMEDates(SME s, ArrayList<SME> SMEList){
			
		 ArrayList<SME> returnList = new ArrayList<SME>();
		 
		 for(SME temp :SMEList){
			 if(s.getsDateOfBegin().equals(temp.getsDateOfBegin()))
				 returnList.add(0,  temp);
			 else if(s.getsDateOfEnd().equals(temp.getsDateOfEnd()))
				 returnList.add(1,  temp);		 
		 }
		 return returnList;

	 }
	 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
