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
	
	public String[] displayLineOnGraphDescending(int id) {
		return tickets.get(id).graphOutput(id);
	}
	
	public String[] displayLineOnGraphAscending(int id) {
		return tickets.get(id).graphOutput(tickets.size() - id - 1);
	}
	
	@Override
	public String toString() {
		return "TicketHolder [tickets=" + tickets + "]";
	}

	// organize by date
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
	
	// organize by device name
	public void orgByDevice() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getdName().compareTo(tickets.get(i).getdName()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	// organize by first name
	public void orgByFirst() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getfName().compareTo(tickets.get(i).getfName()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	// organize by last name
	public void orgByLast() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getlName().compareTo(tickets.get(i).getlName()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
	}
	
	// organize by problem
	public void orgByProblem() {
		int n = tickets.size();
		Ticket key;
		for(int j = 1; j < n; j++) {
			key = tickets.get(j);
			int i = j - 1;
			
			while (i >= 0) {
				if (key.getProblem().compareTo(tickets.get(i).getProblem()) < 0) {
					break;
				}
				tickets.set(i+1, tickets.get(i));
				i--;
			}
			tickets.set(i+1, key);
		}
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
