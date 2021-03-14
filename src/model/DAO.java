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
		
		//Creating Command String
		String create = "insert into contacts (conName, conPhone, conEmail) values (?,?,?)";
		//Try Catch for DB possible Connection Exceptions
		try {
			//Open Connection
			Connection con = connect();
			//Preparing Query for its execution at the DataBase
			PreparedStatement pst = con.prepareStatement(create);
			//Replacing the ? parameters for the JavaBeans variables' content
			pst.setString(1, contact.getConName());
			pst.setString(2, contact.getConPhone());
			pst.setString(3, contact.getConEmail());
			//Execute Query
			pst.executeUpdate();
			//Close DataBase Connection to ensure proper Performance and Security
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
    	
    	try {
    		
    		//Opening DB Connection
    		Connection con = connect();
    		
			//Preparing Query to be Executed at the DataBase
    		PreparedStatement pst = con.prepareStatement(read);
    		
    		//Temporarily storing DataBase Result in an Object and Executing Query
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
    		
    		con.close();
    		
    		return contactsList;
    		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
    }
	
	
	
	
	
	
}
