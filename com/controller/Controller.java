package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Controller() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    ServClass sc=new ServClass();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
		
		HttpSession hsn=request.getSession(false);
		if(hsn==null)
		{
			pw.println("Sorry, there's something wrong with the page. Kindly log back in !");
			RequestDispatcher rd=request.getRequestDispatcher("Index.html");
			rd.forward(request, response);
		}
		else
		{
			String username=(String) hsn.getAttribute("UserName");
			String userType=(String) hsn.getAttribute("User");
			
			try {
			
					String action=request.getParameter("Action");
				
					
					if(action.equals("Edit Profile"))
					{
						RequestDispatcher rd=request.getRequestDispatcher("EditProfile.jsp");
						rd.forward(request, response);
					}
					if(action.equals("Save Changes"))
					{
						RequestDispatcher rd=request.getRequestDispatcher(userType + "Dashboard.jsp");
						rd.forward(request, response);
					}
					
					
					if(userType.equals("Trainer"))
					{
						Trainer t=sc.getTrainerByUsername(username);
						hsn.setAttribute("tObject", t);
						
						if(action.equals("Create Request"))
						{
							sc.tRequest(t);
							RequestDispatcher rd=request.getRequestDispatcher("RequestSuccess.jsp");
							rd.forward(request, response);
						}
						if(action.equals("Update Request"))
						{
							sc.tRequest(t);
							RequestDispatcher rd=request.getRequestDispatcher("RequestCreation.jsp");
							rd.forward(request, response);
						}
						if(t.gettStatus()==1)
						{
							//confirmation mail
							RequestDispatcher rd=request.getRequestDispatcher("RequestAccept.jsp");
							rd.forward(request, response);
						}
					}
					
					if(userType.equals("SME"))
					{
						SME sme=sc.getSMEByUsername(username);
						hsn.setAttribute("smeObject", sme);
						
						if(action.equals("Create Nomination"))
						{
							sc.nominate(sme);
							RequestDispatcher rd=request.getRequestDispatcher("NominationSuccess.jsp");
							rd.forward(request, response);
						}
						if(action.equals("Update Nomination"))
						{
							sc.nominate(sme);
							RequestDispatcher rd=request.getRequestDispatcher("NominationCreation.jsp");
							rd.forward(request, response);
						}
						if(sme.getsStatus()==1)
						{
							RequestDispatcher rd=request.getRequestDispatcher("NominationAccept.jsp");
							rd.forward(request, response);
						}
						if(action.equals("Re-login as Trainer"))
						{
							RequestDispatcher rd=request.getRequestDispatcher("TrainerDashboard.jsp");
							rd.forward(request, response);
						}
					}
					
					
				} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
			}
		}
		pw.close();
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
