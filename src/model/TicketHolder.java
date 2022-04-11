package model;

import java.util.LinkedList;

public class TicketHolder {
	
	// Linked list holding every ticket
	LinkedList<Ticket> tickets = new LinkedList<Ticket>();
	
	public int getNumTickets() {
		return tickets.size();
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
	
	// pull list from database
	public void populateFromDB() {
		// TODO
	}
	
	// pull list from passed 2d string array
	public void populateFromArray(String[][] ticketList) {
		for(int x = 0; x < ticketList.length; x++) {
				tickets.add(new Ticket(ticketList[x], x));
		}
	}
	
	// delete ticket based on its id
	public void deleteByRow(int row) {
		for (int i = 0; i < tickets.size(); i++) {
			if(tickets.get(i).getCurrentRow() == row) {
				tickets.remove(i);
			}
		}
	}
	
	
}
