package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Ticket;
import model.TicketHolder;

/**
 * This class retrieves information that the TicketHolder class needs
 * to populate its LinkedList 'tickets' from a database defined in 
 * the DBInfo class.
 * @author John Ryan
 *
 */
public class DBTicketHolder {
	
	/**
	 * Populates the LinkedList tickets from a database accessed with a DBInfo object
	 * @throws SQLException - if DBInfo can't connect to database
	 */
	public TicketHolder populateFromDB(TicketHolder th) {
		th.clear(); // clears any preexisting tickets
		DBInfo db = new DBInfo();

		Connection conn;
		// try to add every ticket in the database to a TicketHolder object
		try {
			conn = db.connect();
			Statement stm;
			stm = conn.createStatement();
			String SQL = "SELECT * FROM tickets";
			ResultSet rst;
			rst = stm.executeQuery(SQL);
			while (rst.next()) {
				Ticket ticket = new Ticket(rst.getInt("id"), rst.getString("DeviceName"), rst.getString("FirstName"), 
										   rst.getString("LastName"), rst.getDate("Date").toLocalDate(), rst.getString("Problem"), rst.getString("Disassemble"));
				th.add(ticket);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return th;
	}

}
