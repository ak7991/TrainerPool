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

    ServClass sc=new ServClass();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		
	
		HttpSession hsn=request.getSession(false);
		String userType = (String)hsn.getAttribute("User");
		pw.println("Reg");
		if(hsn==null)
		{
			pw.println("Sorry, there's something wrong with the page. Kindly log back in !");
			RequestDispatcher rd=request.getRequestDispatcher("Index.html");
			rd.forward(request, response);
		}
		else
		{
			String Fname=request.getParameter("FirstName");
			String Lname=request.getParameter("LastName");
			int Age=Integer.parseInt(request.getParameter("Age"));
			String Gender=request.getParameter("gender");
			String ContactNumber=request.getParameter("Contact");
			String mail=request.getParameter("EmailID");
			String Username=request.getParameter("UserId");
			String Password=request.getParameter("Password");
			
			String action=request.getParameter("Action");
			pw.println("Regif");
			if(userType.equals("Trainer"))
			{
				pw.println("Regifif");
				try {
						Trainer t=new Trainer(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, 0, 0);
						pw.println(t);
						if(action.equals("Register"))
						{
							if(sc.addTrainer(t))
								pw.println("yes");
							else
								pw.println("No");
							hsn.setAttribute("tObject", t);
						}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			else if(userType.equals("SME"))
			{
				
				try {
						SME sme=new SME(Fname, Lname, Age, Gender, ContactNumber, mail, Username, Password, 0, 0);
						
						if(action.equals("Register"))
						{
							sc.addSME(sme);
							hsn.setAttribute("smeObject", sme);
						}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			RequestDispatcher userNav = request.getRequestDispatcher("JSP/"+userType + "Login.jsp");
			//userNav.forward(request, response);
				
			pw.close();
			
		}
		
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
