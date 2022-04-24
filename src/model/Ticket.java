package model;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import database.DBInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Ticket {

	private int id;			// id of ticket (primary key in database)
	
	private int currentRow; // keeps track of the row this ticket is in on the table
	
	private String dName;	// Device name
	private String fName;	// Customer first name
	private String lName;	// Customer last name
	private String problem;	// Problem with device
	private LocalDate creation;	// Date ticket was created
	
	private Stack<String> disassemble = new Stack<String>();	// Stack containing disassembly steps
	private Stack<String> reassemble = new Stack<String>();		// Stack containing assembly steps
	
	public Ticket() {
		currentRow = -1;
		dName = "DUMMY";
		fName = "DUMMY";
		lName = "DUMMY";
	}
	
	// CONSTRUCTOR FOR INITIALIZING A TICKET FROM DATABASE
	public Ticket(int id, String dName, String fName, String lName, LocalDate date, String problem, String disassemble) {
		this.id = id;
		setdName(dName);
		setfName(fName);
		setlName(lName);
		setCreation(date);
		setProblem(problem);
		setDisassemble(disassemble);
		// OTHER METHODS TO CONVERT DATE AND DISASSEMBLY STRING INTO DATE AND STACK DATATYPES
	}
	
	// Constructor for creating a ticket to be pushed to database
	public Ticket(String dName, String fName, String lName, LocalDate date, String problem, String disassemble) {
		// id will be assigned automatically by database
		setdName(dName);
		setfName(fName);
		setlName(lName);
		setCreation(date);
		setProblem(problem);
		setDisassemble(disassemble);
	}
	
	// converts string of disassemble from database to stack in ticket class
	private void setDisassemble(String disassemble2) {
		List<String> disassembleList = Arrays.asList(disassemble2.split("\r?\n|\r"));
		for(int i = disassembleList.size() - 1; i > -1; i--) {
			disassemble.push(disassembleList.get(i));
		}
	}

	// inserts a ticket into the database
	public void insertTicket() {
		DBInfo db = new DBInfo();
		String SQL = "INSERT INTO tickets(DeviceName, FirstName, LastName, Date, Problem, Disassemble) " + "VALUES(?,?,?,?,?,?)";
		
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, getdName());
			pstmt.setString(2, getfName());
			pstmt.setString(3, getlName());
			pstmt.setDate  (4, Date.valueOf(getCreation()));
			pstmt.setString(5, getProblem());
			pstmt.setString(6, disassembleToDatabase());
			
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

	}
	
	// deletes a ticket from the database
	public void deleteTicket() {
		DBInfo db = new DBInfo();
		String SQL = "DELETE FROM tickets WHERE (id = ?)";
		
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, Integer.toString(getId()));
			
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// represents disassembling one more component by popping disassembly component into reassembly stack
	public void incrementDisassemble() {
		if(disassemble.size() > 0)
			reassemble.add(disassemble.pop());
		else
			// TODO: Add exception for disassemble stack being empty
			;
	}
	
	// represents reassembling one more component by popping reassembly component into disassembly stack.
	public void incrementReassemble() {
		if(reassemble.size() > 0)
			disassemble.add(reassemble.pop());
		else
			// TODO: Add exception for reassembly stack being empty
			;
	}

	// Returns a list of all remaining entries in the disassemble stack
	public String disassemblePrint() {
		String dis = null;
		int size = disassemble.size();
		for(int i = 0; i < size; i++) {
			dis += disassemble.get(i) + "\n";
		}
		return dis;
	}

	// Returns a list of all remaining entries in the assemble stack
	public String reassemblePrint() {
		String reas = null;
		int size = reassemble.size();
		for(int i = 0; i < size; i++) {
			reas += reassemble.get(i) + "\n";
		}
		return reas;
	}
	
	// returns a string array that will be used to output ticket information to a table in MainFrame
	public String[] graphOutput(int currentRow) {
		this.currentRow = currentRow;
		String[] row = new String[8];
		row[0] = fName;
		row[1] = lName;
		row[2] = creation.toString();
		row[3] = dName;
		row[4] = problem;
		return row;
	}
	
	// output for view window disassembly steps
	public String printDisassembleSteps() {
		String disString = "";	// String that will be returned: lists every disassembly step remaining
		String temp = ""; // where string is temporarily held while operations determining what text is output are performed on it
		int indexEnd = -1;	  // index where the disassembly step ends in this string (before "/") - will be determined in for loop
		for(int i = disassemble.size() - 1; i > -1; i--) {
			temp = disassemble.elementAt(i);
			indexEnd = temp.indexOf("/");
			disString += temp.substring(0, indexEnd) + "\n";
		}
		return disString;
	}
	
	// output for view window assembly steps
	public String printReassembleSteps() {
		String reaString = ""; // string that will be returned: lists every assembly step remaining
		String temp = ""; // where string is temporarily held while operations determining what text is output are performed on it
		int indexStart = -1;	  // index where the assembly step starts in this string (after "/"). Will be determined in for loop
		for(int i = reassemble.size() - 1; i > -1; i--) {
			temp = reassemble.elementAt(i);
			indexStart = temp.indexOf("/") + 1;
			reaString += temp.substring(indexStart) + "\n";
		}
		return reaString;
	}
	
	// Converts the disassemble stack to a String so it can be more easily stored in a database
	// Don't need one for reassemble, because contents of reassemble are contained in disassemble stack
	public String disassembleToDatabase() {
		String disString = ""; // String that will be returned to database-friendly format
		for(int i = disassemble.size() - 1; i > -1; i--) {
			disString += disassemble.elementAt(i) + "\n";
		}
		return disString;

	}
	
	// Getters - Setters
	
	public int getId() {
		return id;
	}
	// id should only be set upon initialization

	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}
	
	public LocalDate getCreation() {
		return creation;
	}

	public void setCreation(LocalDate date) {
		this.creation = date;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public Stack<String> getDisassemble() {
		return disassemble;
	}

	public void setDisassemble(Stack<String> disassemble) {
		this.disassemble = disassemble;
	}

	public Stack<String> getReassemble() {
		return reassemble;
	}

	public void setReassemble(Stack<String> reassemble) {
		this.reassemble = reassemble;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", currentRow=" + currentRow + ", dName=" + dName + ", fName=" + fName + ", lName="
				+ lName + ", disassemble=" + disassemble + ", reassemble=" + reassemble + "]";
	}
	
	
}
