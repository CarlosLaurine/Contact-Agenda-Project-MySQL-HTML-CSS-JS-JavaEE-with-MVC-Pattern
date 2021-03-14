package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */

//URL Patterns for Servlet Requests
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	JavaBeans contact = new JavaBeans();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws IOException 
	 *  @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			
			String action = request.getServletPath();
			
			//Testing Request connection with Servlet at the Console
			System.out.println(action);
	       
	        if(action.equals("/main")) {
	        	
	        	contacts(request, response);
	        }
	        else if(action.equals("/insert")) {
	        	newContact(request, response);
	        }
	        else if(action.equals("/select")) {
	        	listContact(request, response);
	        }
	        else {
	        	response.sendRedirect("index.html");
	        }
}

//List Contacts
protected void contacts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	
	//Creating an Object that will receive JavaBeans data from the mySQL DataBase
	ArrayList<JavaBeans> contactsList = dao.listContacts();
	
	/* Test of List Receipt
	for (JavaBeans contact : contactsList) {
		
		System.out.println(contact.getConId());
		System.out.println(contact.getConName());
		System.out.println(contact.getConPhone());
		System.out.println(contact.getConEmail());
		
	}
	*/
	
	//Forwarding the list to document agenda.jsp
	
	//Setting attribute in order to make contactsList accessible through agenda.jsp 
	request.setAttribute("contacts", contactsList);
	
	//Dispatching list to document agenda.jsp
	
	RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
	
	rd.forward(request, response);
	
	
}


//New Contact
protected void newContact(HttpServletRequest request, HttpServletResponse response) throws IOException {
	//Form Data recognizing Test
	
	/*
	System.out.println(request.getParameter("name"));
	System.out.println(request.getParameter("phone"));
	System.out.println(request.getParameter("email"));
	*/
	
	//Setting JavaBeans Variables
	contact.setConName(request.getParameter("name"));
	contact.setConPhone(request.getParameter("phone"));
	contact.setConEmail(request.getParameter("email"));
	
	//Call on insertContact Method passing the Contact Object
	dao.insertContact(contact);
	
	//Redirect for the Document agenda.jsp
	response.sendRedirect("main");
	
}

//Contact Edition Method

protected void listContact(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	//Using a String Variable receiving the id Parameter from the Selected Contact sent by agenda.jsp
	String conId = request.getParameter("conId");
	//Testing specific Contact ID Parameter Received by the Servlet's Accuracy
	//System.out.println(conId);
	
	//Setting JavaBeans Variable after Test
	contact.setConId(conId);
	
}
}


