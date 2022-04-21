package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import database.DBInfo;

public class TicketHolder {
	
	// Linked list holding every ticket
	LinkedList<Ticket> tickets = new LinkedList<Ticket>();
	
	public int getNumTickets() {
		return tickets.size();
	}
	
	// returns ticket at index i in LinkedList tickets
	public Ticket getTicket(int i) {
		try {
			return tickets.get(i);
		} catch(IndexOutOfBoundsException e1) {
			System.out.println(e1.getStackTrace());
		}
		return new Ticket();
	}
	
	public String[] displayLineOnGraph(int id) {
		return tickets.get(id).graphOutput(id);
	}
	
	@Override
	public String toString() {
		return "TicketHolder [tickets=" + tickets + "]";
	}

	// organize by date
	public void orgByDate() {
		//TODO
	}
	
	// organize by device name
	public void orgByDevice() {
		// TODO
	}
	
	// organize by first name
	public void orgByFirst() {
		// TODO
	}
	
	// organize by last name
	public void orgByLast() {
		// TODO
	}
	
	// organize by problem
	public void orgByProblem() {
		// TODO
	}
	
	// pull list from database
	public void populateFromDB() throws SQLException {
		DBInfo db = new DBInfo();
		
		Connection conn = db.connect();
		Statement stm;
		stm = conn.createStatement();
		String SQL = "SELECT * FROM tickets";
		ResultSet rst;
		rst = stm.executeQuery(SQL);
		while (rst.next()) {
			Ticket ticket = new Ticket(rst.getInt("id"), rst.getString("DeviceName"), rst.getString("FirstName"), 
									   rst.getString("LastName"), rst.getDate("Date").toLocalDate(), rst.getString("Problem"), rst.getString("Disassemble"));
			tickets.add(ticket);
		}


	}
	
	// pull list from passed 2d string array
	public void populateFromArray(String[][] ticketList) {
		for(int x = 0; x < ticketList.length; x++) {
				tickets.add(new Ticket(ticketList[x], x));
		}
	}
	
	// delete ticket from database & tickets LinkedList based on its row (from the table)
	public void deleteByRow(int row) {
		for (int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getCurrentRow() == row) {
				tickets.get(i).deleteTicket();
				tickets.remove(i);
			}
		}
	}
	
	
}
