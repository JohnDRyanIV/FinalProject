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
	
	// Constructor for initializing a ticket from an array
	public Ticket(String[] content, int id) {
		int length = content.length;
		this.id = id;
		currentRow = -1;
		dName = content[0];
		fName = content[1];
		lName = content[2];
		/*
		for(int i = 3; i < length; i++) {
			disassemble.add(content[i]);
		}*/
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
		setdName(dName);
		setfName(fName);
		setlName(lName);
		setCreation(date);
		setProblem(problem);
		setDisassemble(disassemble);
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
		System.out.println(SQL);
		
		try {
			Connection conn = db.connect();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, Integer.toString(getId()));
			System.out.println(pstmt.toString());
			
			pstmt.executeUpdate();
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	// converts string of disassemble from database to stack in ticket class
	private void setDisassemble(String disassemble2) {
		// TODO Auto-generated method stub
		List<String> disassembleList = Arrays.asList(disassemble2.split("\n"));
		for(int i = disassembleList.size() - 1; i > -1; i--) {
			disassemble.push(disassembleList.get(i));
		}
	}

	// represents disassembling one more component by popping disassembly component into reassembly stack
	public void incrementDisassemble() {
		reassemble.add(disassemble.pop());
	}
	
	// represents reassembling one more component by popping reassembly component into disassembly stack.
	public void incrementReassemble() {
		disassemble.add(reassemble.pop());
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
	
	public String[] graphOutput(int currentRow) {
		this.currentRow = currentRow;
		String[] row = new String[8];
		row[0] = dName;
		row[1] = problem;
		row[2] = fName;
		row[3] = lName;
		row[4] = creation.toString();
		//row[3] = "blah";
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
			disString += disassemble.elementAt(i);
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
