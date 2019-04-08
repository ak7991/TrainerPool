//Updated as of 10:35PM, 3/4/2019

package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.ServClass;

/**
 * Servlet implementation class Landing_page
 */
@SuppressWarnings("unused")
public class LandingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LandingPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		//String for setting target for different actions of different users
		String target = "";

		//Get which user tried to login/signup/navigate
		String userType = (request.getParameter("User"));

		//If login redirect -> redirect to login page
		if(request.getParameter("IndexAction") != null){
				target = "JSP/" + userType + "Login" + ".jsp";
		}
		
		if(request.getParameter("Action") != null){
			
			//If login request -> Authenticate credentials
			if(request.getParameter("Action").equals("Login")){

				 try {
					//=========JDBC connection=========
				    //Landing Page doesn't use util layer
				          
				    String url="jdbc:oracle:thin:@localhost:1521:xe";
				    String user="tpool";
				    String pass="hr";
			    	Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(url,user,pass);
				    //================================
					
					if(con == null){
						System.out.println("Connection object lost/uninitiated");
						target = "JSP/SomethingWentWrong.jsp";
					}
						
					else{
						//~~~~~~~~~~~SQL query and authentication~~~~~~~~~
				    	String uname = request.getParameter(userType + "Username");
				    	String password = request.getParameter(userType + "Password");
				    	
				    	
				    	//Get the password hash
				    	String hashedAuthenticationKey = getHash(uname, password, con, response);
				    	
						String sql = "select password from " + userType + "db where username=\'"+uname+"\'";
					    PreparedStatement pst  = con.prepareCall(sql);
	
					    //pst.setString(1, uname);
					    
					    ResultSet rs = pst.executeQuery(sql);
					     
					    boolean authentication = false;
	
					    //Only password is needed as hashed key is generated via concatenation of credentials
				    	String passAuth = null; 
				    	
				    	
					    if(rs.next()){
							pw.println("Got the password");
					    	passAuth = rs.getString(1);
	
					    	try{
							    //Setting appropriate status in authentication boolean
	
							    if(passAuth.equals(hashedAuthenticationKey))
							    	authentication = true;
							    else if(uname == null || password == null)
							    	authentication = false;
					    	}
					    	catch(NullPointerException np){
					    		pw.println("<h2> Authentication could not be done due to unsuccessful call to database hashing function, press the browser's back button.");
					    	}
					    		
					    }
					    //Username not in database.
					    else
					    	authentication = false;
					    
						//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					    
					    //----------------Page Navigation------------------
					    
					    //Password mismatch
						if(!authentication){
							System.out.println("<h2>Invalid credentials</h2>");
							
							//Set target for re-login/signup
							target = "JSP/Unsuccessful.jsp";
							pw.println("no");
						}
						//Authentication done
						else{
							
							ServClass dbService = new ServClass();
							
							HttpSession hsn = request.getSession(true);
							hsn.setAttribute("User", userType);
							hsn.setAttribute("UserName", uname);
							hsn.setAttribute("Login", true); //Boolean that dictates login status explicitly
										
							//Admin functionalities don't warrant a need to access the admin record from database (right now).
							if(userType.equals("Trainer"))
								hsn.setAttribute("UserObjectList", dbService.getTrainersByUsername(uname));
							else if(userType.equals("SME")) // Attribute named UserObjectList and not UserObject for consistency
								hsn.setAttribute("UserObjectList", dbService.getSMEByUsername(uname)); 
							
							
							//Set target to dashboard (Welcome)
							target = "JSP/" + userType + "Dashboard.jsp";
							System.out.println("Forwarding to..." + target);
						} 
						//-------------------------------------------------
					}
				 }
				 catch (SQLException | ClassNotFoundException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
					 target = "JSP/SomethingWentWrong.jsp";
				 }
						
			}
				 
			//If signup requested
			else if(request.getParameter("Action").equals("SignUp")){
				System.out.println("LandingPage: " + userType);
				HttpSession hsn = request.getSession(true);
				hsn.setAttribute("User", userType);
				hsn.setAttribute("LoginStatus", 0);
				hsn.setAttribute("Username", request.getParameter("Username"));
				//Set target for signup (new user)
				System.out.println("signing up");
				target = "HTML/SignUp.html";
			}			
			//Instantiated when an SME has migrated to trainer
			else if(request.getParameter("Action").equals("TrainerReLogin")){
				System.out.println("LandingPage: " + userType);
				
				HttpSession hsn = request.getSession(false); //Note that the session HAS to be created earlier, if not then it isn't necessary.
				if(hsn != null){
					hsn.setAttribute("LoginStatus", 0); //Explicitly se =t login status as 'false' (0), will be set to 1 after relogin.
					
					/* These attributes will be set after login
					hsn.setAttribute("User", userType);
					hsn.setAttribute("Username", request.getParameter("Username"));
					*/
				}
				target = "JSP/TrainerLogin.jsp";
			}
			
			//Unreachable code??
			else if(request.getParameter("Action").equals("allocate")){
				HttpSession hsn = request.getSession(false);
				hsn.setAttribute("User", userType);
				hsn.setAttribute("UserName", request.getParameter(userType + "Username"));
				hsn.setAttribute("Password", request.getParameter(userType + "Password"));
				//Set target for unallocation jsp
				target = "JSP/Allocation.jsp";
			}
			else if(request.getParameter("Action").equals("calendarList")){
				HttpSession hsn = request.getSession(false);
				hsn.setAttribute("User", userType);
				hsn.setAttribute("UserName", request.getParameter(userType + "Username"));
				hsn.setAttribute("Password", request.getParameter(userType + "Password"));
				//Set target for unallocation jsp
				target = "JSP/Calendar.jsp";
			}
			else if(request.getParameter("Action").equals("unAllocated")){
				HttpSession hsn = request.getSession(false);
				hsn.setAttribute("User", userType);
				hsn.setAttribute("UserName", request.getParameter(userType + "Username"));
				hsn.setAttribute("Password", request.getParameter(userType + "Password"));
				//Set target for unallocation jsp
				target = "JSP/Unallocation.jsp";
			}
			
			else if(request.getParameter("Action").equals("home")){
				HttpSession hsn = request.getSession(false);
				hsn.setAttribute("User", userType);
				hsn.setAttribute("UserName", request.getParameter(userType + "Username"));
				hsn.setAttribute("Password", request.getParameter(userType + "Password"));
				//Set target for home jsp
				target = "JSP/" + userType + "Dashboard.jsp";
			}
			else
				target = "JSP/SomethingWentWrong.jsp";

		}

		//Forward User to appropriate target
		RequestDispatcher userNav = request.getRequestDispatcher(target);
		userNav.forward(request, response);
			
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
