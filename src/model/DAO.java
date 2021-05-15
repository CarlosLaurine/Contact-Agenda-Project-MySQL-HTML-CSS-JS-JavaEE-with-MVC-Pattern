package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** ============================================================================================================= CONNECTION MODULE =============================================================================================================. */

	// Connection Parameters

	private String driver = "com.mysql.cj.jdbc.Driver";

	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/agendaDB?useTimezone=true&serverTimezone=UTC";

	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "Megadeth@3625";

	// Connection Method

	/**
	 * Connect.
	 *
	 * @return the connection
	 */
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
	 * =============================================================================
	 * ================================ CRUD METHODS ===============================
	 * =============================================================================
	 */

	// CRUD - CREATE

	/**
	 * Insert contact.
	 *
	 * @param contact the contact
	 */
	public void insertContact(JavaBeans contact) {

		String insertion = "insert into contacts (conName, conPhone, conEmail) values (?,?,?)";

		try {

			Connection con = connect();

			PreparedStatement pst = con.prepareStatement(insertion);

			pst.setString(1, contact.getConName());
			pst.setString(2, contact.getConPhone());
			pst.setString(3, contact.getConEmail());

			pst.executeUpdate();

			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// CRUD - READ

	/**
	 * List contacts.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listContacts() {

		ArrayList<JavaBeans> contactsList = new ArrayList<>();

		String read = "select * from contacts order by conName";

		try {

			Connection con = connect();

			PreparedStatement pst = con.prepareStatement(read);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				String conId = rs.getString(1);
				String conName = rs.getString(2);
				String conPhone = rs.getString(3);
				String conEmail = rs.getString(4);

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

	// CRUD UPDATE

	/**
	 * Select contact.
	 *
	 * @param contact the contact
	 */
	public void selectContact(JavaBeans contact) {

		String select2 = "select * from contacts where conId = ?";

		try {

			Connection con = connect();

			PreparedStatement pst = con.prepareStatement(select2);

			pst.setString(1, contact.getConId());

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				contact.setConId(rs.getString(1));
				contact.setConName(rs.getString(2));
				contact.setConPhone(rs.getString(3));
				contact.setConEmail(rs.getString(4));

			}

			con.close();

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}

	/**
	 * Update contact.
	 *
	 * @param contact the contact
	 */
	// Editing Contact
	public void updateContact(JavaBeans contact) {

		String update = "update contacts set conname=?, conphone=?, conemail=? where conid =?";

		try {
			Connection con = connect();

			PreparedStatement pst = con.prepareStatement(update);

			pst.setString(1, contact.getConName());
			pst.setString(2, contact.getConPhone());
			pst.setString(3, contact.getConEmail());
			pst.setString(4, contact.getConId());

			pst.executeUpdate();

			con.close();

		}

		catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

	/**
	 * Delete contact.
	 *
	 * @param contact the contact
	 */
	// Deleting Contact
	public void deleteContact(JavaBeans contact) {

		String deletion = "delete from contacts WHERE conid = " + contact.getConId();

		try {

			Connection con = connect();

			PreparedStatement pst = con.prepareStatement(deletion);
			pst.execute();

			con.close();

		} catch (Exception e) {

			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}

}
