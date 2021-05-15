package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Controller.
 */

@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The contact. */
	JavaBeans contact = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String action = request.getServletPath();

		System.out.println(action);

		if (action.equals("/main")) {

			contacts(request, response);
		} else if (action.equals("/insert")) {
			insertContact(request, response);
		} else if (action.equals("/select")) {
			listContact(request, response);
		} else if (action.equals("/update")) {
			updateContact(request, response);
		} else if (action.equals("/delete")) {
			deleteContact(request, response);
		} else if (action.equals("/report")) {
			generatePDFReport(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}

/**
 * Contacts.
 *
 * @param request the request
 * @param response the response
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws ServletException the servlet exception
 */
//List Contacts
	protected void contacts(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ArrayList<JavaBeans> contactsList = dao.listContacts();

		request.setAttribute("contacts", contactsList);

		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");

		rd.forward(request, response);

	}

/**
 * Insert contact.
 *
 * @param request the request
 * @param response the response
 * @throws IOException Signals that an I/O exception has occurred.
 */
//New Contact
	protected void insertContact(HttpServletRequest request, HttpServletResponse response) throws IOException {

		contact.setConName(request.getParameter("name"));
		contact.setConPhone(request.getParameter("phone"));
		contact.setConEmail(request.getParameter("email"));

		dao.insertContact(contact);

		response.sendRedirect("main");

	}

//Contact Edition Method

	/**
 * List contact.
 *
 * @param request the request
 * @param response the response
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws ServletException the servlet exception
 */
protected void listContact(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		contact.setConId(request.getParameter("conId"));

		dao.selectContact(contact);

		request.setAttribute("id", contact.getConId());
		request.setAttribute("name", contact.getConName());
		request.setAttribute("phone", contact.getConPhone());
		request.setAttribute("email", contact.getConEmail());

		RequestDispatcher rd = request.getRequestDispatcher("edit.jsp");

		rd.forward(request, response);
	}

//Contact Update Method

	/**
 * Update contact.
 *
 * @param request the request
 * @param response the response
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws ServletException the servlet exception
 */
protected void updateContact(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		contact.setConId(request.getParameter("id"));
		contact.setConName(request.getParameter("name"));
		contact.setConPhone(request.getParameter("phone"));
		contact.setConEmail(request.getParameter("email"));

		dao.updateContact(contact);

		response.sendRedirect("main");
	}

// Contact Deletion Method

	/**
 * Delete contact.
 *
 * @param request the request
 * @param response the response
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws ServletException the servlet exception
 */
protected void deleteContact(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		contact.setConId(request.getParameter("conId"));

		dao.deleteContact(contact);

		response.sendRedirect("main");

	}

// PDF Report Generation Method

	/**
 * Generate PDF report.
 *
 * @param request the request
 * @param response the response
 * @throws IOException Signals that an I/O exception has occurred.
 * @throws ServletException the servlet exception
 */
protected void generatePDFReport(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// Generating a Document Object to access the Arguments and Methods of the
		// External I.O Library IText PDF 5.5.13
		Document document = new Document();

		// Setting Try/Catch Structure to deal with Runtime IO Exceptions/ further PDF
		// Generation Exceptions
		try {

			// Specifying Response Content Type
			response.setContentType("apllication/pdf");

			// Setting the Generated Document's Name
			response.addHeader("Content-Disposition", "inline; filename= Contacts.pdf");

			// Creating Document
			PdfWriter.getInstance(document, response.getOutputStream());

			// Opening the Generated Document to set its Content
			document.open();
			document.add(new Paragraph("Contact List:"));
			// Adding Line Breakers
			document.add(new Paragraph(" "));
			document.add(new Paragraph(" "));

			// Creating a Contact Table

			// OBS: Since in Java Web Projects the Reports are usually generated in a
			// Dynamic way, I will create another Object whose type is PdfPTable, an IText
			// PDF Library that allows Dynamic Table Management

			PdfPTable table = new PdfPTable(3);

			// Creating Header
			PdfPCell col1 = new PdfPCell(new Paragraph("Name"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Phone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));

			table.addCell(col1);
			table.addCell(col2);
			table.addCell(col3);

			// Seeding the Table with the Database Contacts

			ArrayList<JavaBeans> list = dao.listContacts();
			for (JavaBeans contact : list) {

				table.addCell(contact.getConName());
				table.addCell(contact.getConPhone());
				table.addCell(contact.getConEmail());

			}

			document.add(table);

			document.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
