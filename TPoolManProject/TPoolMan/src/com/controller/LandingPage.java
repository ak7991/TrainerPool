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

		//Get which user tried to login/signup
		String userType = (request.getParameter("User"));
		

		
		//If login request
		if(request.getParameter("Action").equals("Login")){

			 try {
					//=========JDBC connection========
				    //ResourceBundle rb= ResourceBundle.getBundle("dbms");
				          
				    String url="jdbc:oracle:thin:@localhost:1521:xe";
				    String user="tpool";
				    String pass="hr";
			    	Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con = DriverManager.getConnection(url,user,pass);
				    //================================
					
					if(con == null)
						pw.println("None");
					
					
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
				    		pw.println("<h2> Authentication could not be done due to unsuccessful call to database hashing function");
				    	}
				    		
				    }
				    
					//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				    
				    //----------------Page Navigation------------------
				    
				    pw.println(hashedAuthenticationKey);
				    pw.println(passAuth);
					if(!authentication){
						pw.println("<h2>Invalid credentials</h2>");
						
						//Set target for re-login/signup
						target = "JSP/Unsuccessful.jsp";
						pw.println("no");
					}
					else{
						HttpSession hsn = request.getSession(true);
						hsn.setAttribute("User", userType);
						hsn.setAttribute("UserName", uname);
						pw.println("Authentication true");
												
						//Set target to dashboard (Welcome)
						target = "JSP/" + userType + "Dashboard.jsp";
						pw.println("Forwarding to..." + target);
					} 
					//-------------------------------------------------
					
			    }	
			    catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			    catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		//If signup requested
		else if(request.getParameter("Action").equals("SignUp")){
			HttpSession hsn = request.getSession(true);
			hsn.setAttribute("User", userType);
			hsn.setAttribute("UserName", request.getParameter(userType + "Username"));
			hsn.setAttribute("Password", request.getParameter(userType + "Password"));
			//Set target for signup (new user)
			target = "HTML/SignUp.html";
		}

		//Forward User to appropriate target
		RequestDispatcher userNav = request.getRequestDispatcher(target);
		userNav.forward(request, response);
			
		pw.close();
		
	}
	
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
