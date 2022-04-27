package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import model.Ticket;

/**
 * This class is responsible for inserting and deleting Ticket objects
 * to and from a ticket database defined in the DBInfo class.
 * @author John Ryan
 *
 */
public class DBTicket {
	
	/**
	 * Inserts a Ticket object into the database defined in DBInfo
	 * @param t - Ticket object to be added to database
	 */
	public void insertTicket(Ticket t) {
		DBInfo db = new DBInfo();
		String SQL =  "INSERT INTO tickets(DeviceName, FirstName, LastName, Date, Problem, Disassemble) " 
					+ "VALUES(?,?,?,?,?,?)";
		
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, t.getdName());
			pstmt.setString(2, t.getfName());
			pstmt.setString(3, t.getlName());
			pstmt.setDate  (4, Date.valueOf(t.getCreation()));
			pstmt.setString(5, t.getProblem());
			pstmt.setString(6, t.disassembleToDatabase());
			
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			printErrorMessage("The database cannot be accessed. Stack trace has been printed to console.\n " +
							"If you are seeing this message, please notify your system administrator.");
		}

	}
	
	/**
	 * Deletes a Ticket object from the database defined in DBInfo.
	 * Searches for ticket to be deleted based on its id.
	 * @param t - Ticket to be deleted from database
	 */
	public void deleteTicket(Ticket t) {
		DBInfo db = new DBInfo();
		String SQL = "DELETE FROM tickets WHERE (id = ?)";
		
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, Integer.toString(t.getId()));
			
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			printErrorMessage("The database cannot be accessed. Stack trace has been printed to console.\n " +
					"If you are seeing this message, please notify your system administrator.");
		}
	}
	
	/**
	 * Prints an error message in a JOptionPane.
	 * @param error - String of error message to be printed.
	 */
	private void printErrorMessage(String error) {
		JOptionPane.showMessageDialog(null, error);
	}
	
}
