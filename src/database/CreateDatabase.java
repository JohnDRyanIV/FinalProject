package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CreateDatabase {
	
	/// IMPORTANT !!!! IMPORTANT !!!! IMPORTANT !!!! IMPORTANT ///
	//////////////////////////////////////////////////////////////
	// YOU MUST EDIT DBINFO FOR YOUR OWN SETUP FOR THIS TO WORK //
	//////////////////////////////////////////////////////////////
	static DBInfo db = new DBInfo();
	
	public static void main(String[] args) {
		createTable();
		addValuesToTable();
	}
	
	private static void addValuesToTable() {
		System.out.println(LocalDate.now().toString());
		String SQL_ADD = "INSERT INTO tickets (DeviceName, FirstName, LastName, Date, Problem, Disassemble)"
				+ " VALUES (?,?,?,?,?,?),(?,?,?,?,?,?),(?,?,?,?,?,?)";
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL_ADD);
			
			pstmt.setString(1, "ASUS ROG 1857");
			pstmt.setString(2, "James");
			pstmt.setString(3, "Smith");
			pstmt.setDate  (4, Date.valueOf(LocalDate.of(2020, 6, 18)));
			pstmt.setString(5, "Keyboard nonfunctional");
			pstmt.setString(6, 
					  "Remove keyboard/Replace keyboard\n"
					+ "Remove keyboard screws/Rescrew keyboard\n"
					+ "Remove keyboard ribbon cable/Plug keyboard ribbon cable\n"
					+ "Remove screws connecting to keyboard/Rescrew keyboard\n"
					+ "Remove battery ribbon cable/Plug battery ribbon cable\n"
					+ "Unscrew back/Screw back\n");
			
			pstmt.setString(7, "HP Omen G580");
			pstmt.setString(8, "Carl");
			pstmt.setString(9, "Wilbert");
			pstmt.setDate  (10, Date.valueOf(LocalDate.of(2020, 6, 20)));
			pstmt.setString(11, "CPU fan not working");
			pstmt.setString(12, 
					  "Detach fan from heatsink/Attach new fan to heatsink\r\n"
					+ "Unplug fan connector/Plug in new fan connector\r\n"
					+ "Unscrew and remove side panel/Rescrew and return side panel\r\n");
			pstmt.setString(13, "iPad");
			pstmt.setString(14, "Caitlyn");
			pstmt.setString(15, "Jones");
			pstmt.setDate  (16, Date.valueOf(LocalDate.of(2020, 6, 25)));
			pstmt.setString(17, "iPad screen cracked, won't display,");
			pstmt.setString(18, 
					  "Unplug display cable/Plug new display cable\r\n"
					+ "Unplug battery/Replug battery\r\n"
					+ "Use sharp pry-tool to pry away screen/Set adhesive tape around screen and replace screen\r\n"
					+ "Treat back of iPad with heat tool/Wait for adhesive to set\r\n");
			
			pstmt.executeUpdate();
			System.out.println("Items added");
					
		} catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}


				
		
		
	}

	public static void createTable() {

		String SQL_CREATE = "CREATE TABLE tickets"
				+ "("
				+ "id int NOT NULL AUTO_INCREMENT,"
			    + "DeviceName VARCHAR(100) NOT NULL,"
				+ "FirstName VARCHAR(45) NOT NULL,"
			    + "LastName VARCHAR(45) NOT NULL,"
				+ "Date DATETIME NOT NULL,"
			    + "Problem VARCHAR(100) NOT NULL,"
				+ "Disassemble VARCHAR(10000) NOT NULL,"
				+ "PRIMARY KEY (id)"
			    + ");";
		
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE);
			pstmt.execute();
			System.out.println("Database created");
		} catch(SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
