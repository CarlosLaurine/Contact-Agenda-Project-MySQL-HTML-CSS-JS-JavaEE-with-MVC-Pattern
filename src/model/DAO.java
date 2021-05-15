package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	/** ============================================================================================================= 
	                                                  CONNECTION MODULE
	    =============================================================================================================**/
	
	
	//Connection Parameters
	
	private String driver = "com.mysql.cj.jdbc.Driver";

	private String url = "jdbc:mysql://127.0.0.1:3306/agendaDB?useTimezone=true&serverTimezone=UTC";
	
	private String user = "root";
	private String password = "Megadeth@3625";
	
	
	//Connection Method
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
	 		return con;
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	/*
	//Connection Test at the Console 
	
	public void ConnectionTest() {
		try {
			Connection con = connect();
			System.out.println(con);
			con.close();
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	*/
	

	/*=============================================================================================================
	                                                CRUD METHODS
	  =============================================================================================================*/
	

	//CRUD - CREATE
	
	public void insertContact (JavaBeans contact) {
		
		//Creating Command String with ? Parameters to be replaced later on the Method
		String create = "insert into contacts (conName, conPhone, conEmail) values (?,?,?)";
		//Try Catch for DB possible Connection Exceptions
		try {
			//Open Connection
			Connection con = connect();
			//Preparing Query for its execution at the DataBase
			PreparedStatement pst = con.prepareStatement(create);
			//Replacing the ? parameters with the JavaBeans variables' content
			pst.setString(1, contact.getConName());
			pst.setString(2, contact.getConPhone());
			pst.setString(3, contact.getConEmail());
			//Execute Query
			pst.executeUpdate();
			//Closing DataBase Connection to ensure proper Performance and Security
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}	
	
	//CRUD - READ
	
    public ArrayList<JavaBeans> listContacts(){
     
    	//Creating an object to Access JavaBeans Class
    	
    	ArrayList<JavaBeans> contactsList = new ArrayList<>();
    
    	//Creating Command String
    	
    	String read = "select * from contacts order by conName";
    	
    	//Try Catch for DB possible Connection Exceptions
    	
    	try {
    		
    		//Opening DB Connection
    		Connection con = connect();
    		
			//Preparing Query to be Executed at the DataBase
    		PreparedStatement pst = con.prepareStatement(read);
    		
    		//Temporarily storing DataBase Result in a ResultSet Object and Executing Query
    		ResultSet rs = pst.executeQuery();
    		
    		
    		//While loop used to fullfill the Arraylist with Result Set's Data
    		
    		while(rs.next()) {
    			
    			//Support variables that receive the mySQL Data
    			
    			String conId = rs.getString(1);
    			String conName = rs.getString(2);
    			String conPhone = rs.getString(3);
    			String conEmail = rs.getString(4);
    			
    			//Fulfilling the ArrayList
    			
    			contactsList.add(new JavaBeans(conId, conName, conPhone, conEmail));

    		}
    		
    		//Closing DataBase Connection to ensure proper Performance and Security

    		con.close();
    		
    		return contactsList;
    		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
	
	
	//CRUD UPDATE
    
    //Method for Contact Selection
    
    public void selectContact(JavaBeans contact) {
    	
    	
    	//Creating Command String with ? Parameters to be replaced later on the Method
    	
    	String select2 = "select * from contacts where conId = ?";
    	
    	//Try Catch for DB possible Connection Exceptions
    	
    	try {
    		
    		//Opening DB Connection
    		
    		Connection con = connect();
    		
    		//Preparing Query to be Executed at the DataBase
    		
    		PreparedStatement pst = con.prepareStatement(select2);
    		
			//Replacing the ? parameters with the JavaBeans variables' content
    		
    		pst.setString(1, contact.getConId());
    		
    		//Temporarily storing DataBase Result in a ResultSet Object and Executing Query

    		ResultSet rs = pst.executeQuery();
    		
    		/*while Loop used to set JavaBeans attributes with ResultSet Object's Elements 
    		according to its Indexed Categories (Column  Number) ([id - 1] [name - 2] [phone-3] [email - 4])
    		*/
    		while(rs.next()) {
    			
    			contact.setConId(rs.getString(1));
    			contact.setConName(rs.getString(2));
    			contact.setConPhone(rs.getString(3));
    			contact.setConEmail(rs.getString(4));
    			
    		}
    		
    		//Close DataBase Connection to ensure proper Performance and Security

    		con.close();
    		
    		
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
			
		}
    }
	
    //Editing Contact
	public void updateContact(JavaBeans contact) {
		
		String edit = "update contacts set conname=?, conphone=?, conemail=? where conid =?";
		
		try {
			Connection con = connect();
			
			PreparedStatement pst = con.prepareStatement(edit);
			
			pst.setString(1, contact.getConName());
			pst.setString(2, contact.getConPhone());
			pst.setString(3, contact.getConEmail());
			pst.setString(4, contact.getConId());
			
			pst.executeUpdate();
			
			con.close();

			
		}
		
		catch (Exception e) {

			System.out.println(e.getMessage());
			
		}

		
	}
	
	//Deleting Contact
		public void deleteContact(JavaBeans contact) {
			
			String deletion = "delete from contacts WHERE conid = " + contact.getConId();
			
			try {
				
				Connection con = connect();
				
				PreparedStatement pst = con.prepareStatement(deletion);
				pst.execute();
				
				con.close();
				
			} catch (Exception e) {
				
				System.out.println(e.getMessage());

				
			}
		}
	
}
