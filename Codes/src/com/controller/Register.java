//Updated as of 4:53PM, 4/1/2019

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.SME;
import com.bean.Trainer;
import com.service.ServClass;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		/*
		 * Session must NOT be created here.
		 * -> If new user registers make a session when they login.
		 * -> If existing user edits profile use the session made when they logged in.
		 */
		HttpSession hsn = request.getSession(false);
		String userType = (String)hsn.getAttribute("User");
		
		ServClass dbService = new ServClass();
		
		String target = "/";

		try{
			
			String Fname = request.getParameter("FirstName");
			String Lname = request.getParameter("LastName");
			int Age = Integer.parseInt(request.getParameter("Age"));
			String Gender = request.getParameter("gender");
			String ContactNumber = request.getParameter("Contact");
			String mail = request.getParameter("EmailID");
			String Username = null;
			if(hsn.getAttribute("UserName") != null){
				System.out.println("Username found!");
				Username = (String) hsn.getAttribute("UserName"); //Unchangeable static field username saved in session (after logging in)
			}
			else{
				System.out.println("Username not found!");
				Username = request.getParameter("Username"); //The username is set for the first time (while signing up).
			}
			System.out.println(Username);
			String Password = null; //This isn't entered by user again; (hash) has to be fetched from database.
			
			String action = request.getParameter("Action");
			System.out.println(userType);
				
			try {
				
				Object o = null;
				
				//Signup -> (new) registration.
				if(action.equals("Register"))
				{
					if(userType.equals("SME")){
						//Fetch password from Signup page
						Password = request.getParameter("Password"); 
						
						//SME object that has to be added (with status 0 as no nomination created)
						o = new SME(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, 0, 0);
						dbService.addSME((SME) o); //Add User to database
						target = "HTML/Singup.html";
					}
					if(userType.equals("Trainer")){
						//Fetch password from Signup page
						Password = request.getParameter("Password"); 
						
						//Trainer object that has to be added (with status 0 as no request created)
						o = new Trainer(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, 0, 0);
						dbService.addTrainer((Trainer) o); //Add User to database
					}
					hsn.setAttribute("UserObject", o);
					target = "JSP/" + userType + "Login.jsp"; //Redirect to login page after registration
				}	
				//Edit Profile -> Update details
				else if(action.equals("Save") && hsn != null)
				{
					
					if(userType.equals("SME")){
						
						//SME object that has to be altered 
						o = new SME(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, 0, 0);
						dbService.updateSME((SME) o); //Update profile attributes to database
						hsn.setAttribute("UserObject", o);
						target = "JSP/" + userType + "Dashboard.jsp";
					}
					
					if(userType.equals("Trainer")){
						System.out.println("Editing profile of trainer");
						
						/*
						 * All trainer objects with a given ID have to be altered because it could be possible that the trainer has been allocated and the records 
						 * have been splitted. So for an effective update the database would have to reflect changes accross all records.
						 * Right now the update profile has to be done BEFORE allocation and the trainer would NOT be able to edit their profile they are allocated.
						 */

						o = (Trainer) dbService.getTrainersByUsername(Username).get(0); //Ideally only one object will be in the list.
						Password = ((Trainer) o).gettPassword(); //Fetch password from database	(will be needed while updating (line #148))
						
						//If allocation not done.
						if(((Trainer) o).gettStatus() == 0){
							o = new Trainer(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, ((Trainer) o).getTrainerId(), 0);
							dbService.updateTrainer((Trainer) o); //Update profile attributes to database, dates are null as request not created
							
							hsn.setAttribute("UserObject", o);
							target = "JSP/" + userType + "Dashboard.jsp";
						}
						if(((Trainer) o).gettStatus() == 1){
							boolean b = dbService.deleteTrainer((Trainer) o); 
							System.out.println("Deleted trainer for profile update: " + b);
							java.util.Date dob = ((Trainer) o).gettDateOfBegin();
							java.util.Date doe = ((Trainer) o).gettDateOfEnd();
							
							Trainer t = new Trainer(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, dob, doe, ((Trainer) o).getTrainerId(), 1);
							dbService.addTrainer(t); //Update profile attributes to database, dates aren't null as request created
							hsn.setAttribute("UserObject", o);
							target = "JSP/" + userType + "Dashboard.jsp";
						}
						//Else the trainer is shown that they can't edit their profile.
						else
							target = "JSP/RequestSuccessful.jsp";
					}
					
					
				}
				//For inducing errors on pages
				else if(request.getParameter("Action").equals("SomethingWentWrong")){
					target = "JSP/SomethingWentWrong.jsp";
				}
			} 
			catch (ClassNotFoundException | SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				target = "JSP/SomethingWentWrong.jsp";
			}
		}//End of 1st try
		catch(NullPointerException e){
			e.printStackTrace();
			target = "JSP/SomethingWentWrong.jsp";
		}
				
			RequestDispatcher userNav = request.getRequestDispatcher(target);
			userNav.forward(request, response);
			
			pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
