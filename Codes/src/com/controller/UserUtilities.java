//Updated as of 2:14Pm 5/4/2019

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.SME;
import com.bean.Skill;
import com.bean.Trainer;
import com.service.ServClass;
import com.util.Conclass;

/**
 * Servlet implementation class Controller
 */
public class UserUtilities extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserUtilities() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "unchecked", "null" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		//For navigation (and redirecting)
		String target = "/";
		
		String userType = "";

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		HttpSession hsn = request.getSession(false);
		
		//Will be used for authentication function while changing password or while migration (SME -> Trainer).
		Connection con = null;
		try {
			con = Conclass.getCon();
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(hsn == null){
			try{
				userType = (String) hsn.getAttribute("User"); //Inducing NullPointerException
			}
			catch(NullPointerException e){
				System.out.println("Session not created");
				e.printStackTrace();
				target = "JSP/SomethingWentWrong.jsp";
			}
		}
		else{
			userType = (String) hsn.getAttribute("User");
			String username = (String) hsn.getAttribute("UserName");
				
			 ServClass dbService = new ServClass();
			
			//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
			//(#1.0)Redirecting to appropriate targets.  ---->  (only for) Navigation Bar
			if (request.getParameter("Action") != null) {
				
				String action = request.getParameter("Action");
				
				System.out.println("Here via navigation bar");
				
				//-->(#1.1) Common Navigation targets <--
				if (action.equals("Home")) {
					target = "JSP/" + userType + "Dashboard.jsp";	
				}
				else if (action.equals("LogOut")) {
					hsn.setAttribute("LoginStatus", false); //Set login status attribute to false to avoid authentication bypass after logout
					hsn.invalidate();					
					request.getSession().invalidate();
					System.gc();

					target = "JSP/" + userType + "Login.jsp";	
				}
				else if(action.equals("Signup")){
					System.out.println("Signingup");
					target = "HTML/Signup.html";
				}
				else if(action.equals("EditProfile"))
				{
					System.out.println("here");
					target = "JSP/EditProfile.jsp";
				}
				else if(action.equals("SaveChanges")){
					target = "JSP/" + userType + "Dashboard.jsp";
				}
				else if(action.equals("EditPassword")){
					target = "JSP/EditPassword.jsp";
				}
				
				//-->(#1.2) Trainer Navbar/Dashboard <--
				else if (action.equals("CreateRequest") && userType.equals("Trainer")) {
					
					ArrayList<Trainer> trainerListByUsername = (ArrayList<Trainer>) hsn.getAttribute("UserObjectList");
					
					if(trainerListByUsername.get(0).gettStatus() == 0)
						target = "JSP/RequestCreation.jsp";	//If request not created yet
					else
						target = "JSP/RequestSuccessful.jsp"; //Request cannot be "re-submitted"
				}
				else if (action.equals("ProgramsAllocated") && userType.equals("Trainer")) {
					target = "JSP/ProgramAllocated.jsp";	//Only if trainer has created their request.
				}

				
				//-->(#1.3) SME Navbar/Dashboard <--
				else if (action.equals("CreateNomination") && userType.equals("SME")) {
					target = "JSP/NominationCreation.jsp";	
				}
				else if (action.equals("ConfirmNomination") && userType.equals("SME")) {
					SME sme = null;
					try {
						sme = dbService.getSMEByUsername(username);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					hsn.setAttribute("smeObject", sme);
					target = "JSP/NominationConfirmation.jsp";	//Only if an admin has approved SME's nomination.
				}
			
				else{
					try {
 
					//=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
					//(#2.0) (Common) User Functionalities
					//(#2.0.1) Edit password after validation [explicitly made unavailable for administrators]
					if(request.getParameter("Action").equals("UpdatePassword") && (!userType.equals("Admin"))){
						
						boolean passUpdate = false; //determines alert message.
						
						//Updated password from front-end
						String oldPassword = request.getParameter("OldPassword");
						String newPassword = request.getParameter("NewPassword");
						// (Already obtained above) //username = (String) hsn.getAttribute("Username");
						
						String hashedAuthenticationKey = getHash(username, oldPassword, con, response);
						//Fetch password from appropriate database.
						String sql = "select password from " + userType + "db where username=\'"+ username +"\'";
					    PreparedStatement pst  = con.prepareCall(sql);
					    ResultSet rs = pst.executeQuery();
					    //Redundant condition (but enforces security)
					    if(rs.next()){
					    	
						    String passAuth = rs.getString(1);
						    
							if(passAuth.equals(hashedAuthenticationKey)){
								//The password hash trigger DOES NOT work for updates, hence hash HAS to be done before database update.
								hashedAuthenticationKey = getHash(username, newPassword, con, response);
								
								String sqlPasswordUpdate = "update " + userType + "db set password from  where username=\'"+ username +"\'";
								PreparedStatement pstPassword  = con.prepareCall(sqlPasswordUpdate);
								pstPassword.executeUpdate();
								
								passUpdate = true;
								hsn.setAttribute("PassUpdateBoolean", passUpdate);
								
								target = "JSP/PasswordUpdated.jsp";
							}
							//Failed authentication
							else{
								
								passUpdate = false;
								hsn.setAttribute("PassUpdateBoolean", passUpdate);
								
								target = "JSP/PasswordUpdated.jsp";
								
							}		
						}
					    else{
					    	System.out.println("?Username could not be found?");
					    	target="JSP/SomethingWentWrong.jsp";
					    }
					}
					//(#2.1) Trainer Functionalities available ONLY if userType matches string "Trainer"
					if(userType.equals("Trainer"))
					{
							
						Trainer t = dbService.getTrainersByUsername(username).get(0);
						
						//(#2.1.1) Update button on 'Edit Profile' page.
						if(action.equals("UpdateRequest"))
						{
							
							//Getting updated profile parameters
							Date tDateOfBegin = sdf.parse(request.getParameter("dob"));
							Date tDateOfEnd = sdf.parse(request.getParameter("doe"));
							String []skills = request.getParameterValues("Skills");
							
							//Reflecting the front-end changes done to bean object declared
							t.settDateOfBegin(tDateOfBegin);
							t.settDateOfEnd(tDateOfEnd);
							
							//Updating dependent skill table
							ArrayList<Skill> existingSkills = (ArrayList<Skill>) dbService.getSkillsByTId(t.getTrainerId());
							//Deleting existing skill
							for(Skill deleteSkill :existingSkills)
								dbService.deleteTSkill(t.getTrainerId(), deleteSkill.getSkillId());
							//Adding skills chosen on edit profile page
							for(String str:skills){							
								Skill s = dbService.getSkillBySkName(str);
								dbService.addTSkill(t.getTrainerId(), s.getSkillId());
							}
							
							System.out.println(t.gettStatus());
							if((t.gettStatus()>0))
								t.settStatus(t.gettStatus()); //Request created, update status if earlier wasn't done.
							else
								t.settStatus(1);
							System.out.println("After status update" + t.gettStatus());
							//Update trainer
							dbService.updateTrainer(t);
							
							hsn.setAttribute("UserObject", t);
							target = "JSP/RequestSuccessful.jsp";
						}
						//(#2.1.2) For inducing errors
						else if(action.equals("SomethingWentWrong")){
							System.out.println("User was set as Trainer but no functionality/navigation chosen.");
							target = "JSP/SomethingWentWrong.jsp";
						}
						//(#2.1.3) ??No functionality was chosen??
						else{
							System.out.println("User was set as Trainer but no functionality/navigation chosen.");
							target = "JSP/SomethingWentWrong.jsp";
						}

					}//Trainer functionalities

					//(#2.2) SME Functionalities available ONLY if userType matches string "SME"
					else if(userType.equals("SME"))
					{
						SME sme = dbService.getSMEByUsername(username);
						hsn.setAttribute("smeObject", sme);

						//(#2.2.1) Submit nomination -> for new SME registrations.
						if(action.equals("SubmitNomination"))
						{
							//Date is for future functionality only
							/*
							 * Date sDateOfBegin = sdf.parse(request.getParameter("dob"));
							 * Date sDateOfEnd = sdf.parse(request.getParameter("doe"));
							 * 							
							 * sme.setsDateOfBegin(sDateOfBegin);
							 * sme.setsDateOfEnd(sDateOfEnd);
							 * dbService.setTDates(sDateOfBegin, sDateOfEnd);
							 */
							
							String []skills = request.getParameterValues("Skills");
							
							//Firstly delete all skills corresponding to the SME.
							dbService.deleteAllSSkillsBySMEId(sme.getSMEId());
							for(String str :skills)
							{
								Skill s = dbService.getSkillBySkName(str);
								dbService.addSSkill(sme.getSMEId(), s.getSkillId());
							}

							sme.setsStatus(1);
							dbService.updateSME(sme);
							
							target = "JSP/NominationSuccessful.jsp";
						}
						
						//(#2.2.2) Migrate SME to Trainer -> This action REQUIRES a password
						else if(action.equals("UpdateNomination")){
							
							System.out.println("Migrated to trainer");
							//Fetch password from migration page
							String password = request.getParameter("Password");
							String hashedPass = getHash(sme.getsUsername(), password, con, response);
							System.out.println("input: " + hashedPass);
							System.out.println("Stored: " + sme.getsPassword());
							Trainer t = new Trainer(sme.getsFname(), sme.getsLname(), sme.getsAge(), sme.getsGender(), sme.getsContactNumber(), sme.getSmail(), sme.getsUsername(), password, 0, 0);
							
							//Authetnicate password from the password attribute of the SME object (that had been) stored in the session.
							if(hashedPass.equals(sme.getsPassword())){
								dbService.addTrainer(t);
								
								List<Skill> skillSet = dbService.getSkillsBySId(sme.getSMEId());
								//Update child records (Skills)
								for(Skill s :skillSet)
									dbService.addTSkill(dbService.getTrainersByUsername(t.gettUsername()).get(0).getTrainerId(), s.getSkillId());
								dbService.deleteAllSSkillsBySMEId(sme.getSMEId());
								
								dbService.deleteSME(sme.getSMEId());
								target = "JSP/NominationAccept.jsp";
							}
							else{
								//Testing purposes								
								pw.println("<h1> Wrong Password entered! </h1><br>Press the browser's back button");
							}
						}

						//(#2.2.3) For inducing errors on pages.
						else if(action.equals("SomethingWentWrong")){
							target = "JSP/SomethingWentWrong.jsp";
						}
						//(#2.2.4) ??No functionality was chosen??
						else{
							System.out.println(action);
							System.out.println(userType);
							System.out.println("User was set as Trainer but no functionality/navigation chosen.");
							target = "JSP/SomethingWentWrong.jsp";
						}
						
					}//SME functionalities

				}//Try block for all functionalities 
					catch (ClassNotFoundException | SQLException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						target = "JSP/SomethingWentWrong.jsp";
					} 
					
				}//Functionalities else
			
			}//Action parameter
		
		}//Session check else
		

		//Forwarding to appropriate target
		System.out.println(target);
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
		
		//Closing resources
		pw.close();
	}

	/*
	 * This function takes 2 strings as inputs and then hashes them using another function that has been stored in the database and written pl/SQL. This pl/SQL 
	 * function is the SAME one which is used for hashing the password when a person is registering and is used for storing password while adding records.
	 * The pl/SQL function uses dbms_utility package and the stored function get_hash_value to get the hashed text. This has just been used as a placeholder
	 * and can be replaced by better hash procedures/functions further down the line.
	 */
    public static String getHash(String username, String password, Connection con, HttpServletResponse response) throws SQLException, IOException 
    { 

		PrintWriter pw = response.getWriter();
		

        try { 
            CallableStatement cst = con.prepareCall("{? = call getPassHash(?, ?)}");
            cst.registerOutParameter(1, Types.CHAR);
            cst.registerOutParameter(2, Types.CHAR);
            cst.registerOutParameter(3, Types.CHAR);
            
            cst.setString(2, username);
            cst.setString(3, password);
            cst.execute();
            String hashtext = cst.getString(1);
            
            // return the HashText 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (Exception e) { 
            e.printStackTrace();
        }
        
        pw.close();
        
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
