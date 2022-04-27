package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 * Handles connecting to the database. Modify class variables if you're unable to connect.
 * @author John Ryan
 *
 */
public class DBInfo {
	private final String url = "jdbc:mysql://localhost:3306/DSFinal"; // DATABASE URL
	private final String user = "root";
	private final String password = "chocolate430";
	
	/**
	 * @return - url of database
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @return - User of database
	 */
	public String getUser() {
		return user;
	}
	
	/**
	 * @return - Password of database
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Connects to the database using private class variables
	 * @return - Connection object to a database, or null if database can't be accessed
	 */
	public Connection connect() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printErrorMessage("The database cannot be accessed. Stack trace has been printed to console.\n " +
					"If you are seeing this message, please notify your system administrator.");
			return null;
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
