package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import database.DBInfo;

/**
 * This class holds Ticket objects. It's where a LinkedList of tickets can be organized
 * and it's also what handles string output to the graph. It can populate itself from a
 * database of fields compatible with Ticket objects. It is what allows Tickets to be
 * viewed or deleted from the database using the graph in MainFrame.
 * @author John Ryan
 */
public class TicketHolder {
	
	// Linked list holding every ticket
	private LinkedList<Ticket> tickets = new LinkedList<Ticket>();
	
	public int getNumTickets() {
		return tickets.size();
	}
	
	/**
	 * Returns the ticket at index i
	 * @param i - index of ticket being returned
	 * @return returns ticket at index i
	 */
	public Ticket getTicket(int i) {
		try {
			return tickets.get(i);
		} catch(IndexOutOfBoundsException e1) {
			System.out.println(e1.getStackTrace());
		}
		return new Ticket();
	}
	
	/**
	 * Returns a String array that is output to one row on a graph.
	 * It is formatted such that row selected will be accurate in descending order.
	 * @param i - index of ticket in LinkedList tickets to be displayed
	 * @return - String array of what will be displayed in this row
	 */
	public String[] displayRowOnGraphDescending(int i) {
		return tickets.get(i).graphOutput(i);
	}
	
	/**
	 * Returns a String array that is output to one row on a graph.
	 * It is formatted such that row selected will be accurate in ascending order.
	 * @param i - index of ticket in LinkedList tickets to be displayed
	 * @return - String array of what will be displayed in this row
	 */
	public String[] displayRowOnGraphAscending(int i) {
		return tickets.get(i).graphOutput(tickets.size() - i - 1);
	}
	
	@Override
	public String toString() {
		return "TicketHolder [tickets=" + tickets + "]";
	}

	/**
	 * Organizes the LinkedList tickets by date using insertion sort
	 */
	public void orgByDate() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getCreation().compareTo(tickets.get(i).getCreation()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	/**
	 * Organizes the LinkedList tickets by device name using insertion sort
	 */
	public void orgByDevice() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getdName().toLowerCase().compareTo(tickets.get(i).getdName().toLowerCase()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	/**
	 * Organizes the LinkedList tickets by first name using insertion sort
	 */
	public void orgByFirst() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getfName().toLowerCase().compareTo(tickets.get(i).getfName().toLowerCase()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	/**
	 * Organizes the LinkedList tickets by last name using insertion sort
	 */
	public void orgByLast() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getlName().toLowerCase().compareTo(tickets.get(i).getlName().toLowerCase()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	/**
	 * Organizes the LinkedList tickets by problem using insertion sort
	 */
	public void orgByProblem() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getProblem().toLowerCase().compareTo(tickets.get(i).getProblem().toLowerCase()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	/**
	 * Populates the LinkedList tickets from a database accessed with a DBInfo object
	 * @throws SQLException - if DBInfo can't connect to database
	 */
	public void populateFromDB() throws SQLException {
		tickets.clear(); // clears any preexisting tickets
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
	
	/**
	 * Deletes Ticket from LinkedList tickets based on row variable of a Ticket
	 * @param row - row variable of a Ticket.
	 */
	public void deleteByRow(int row) {
		for (int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getCurrentRow() == row) {
				tickets.get(i).deleteTicket();
				tickets.remove(i);
			}
		}
	}
	
	
}
