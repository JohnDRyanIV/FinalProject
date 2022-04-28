package model;

import java.time.LocalDate;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * This class holds a Ticket representing a set of information and instructions about
 * a specific electronic device that is serviced to be repaired. This information includes
 * a step by step description on how to disassemble the original product and reassemble the
 * finished product.
 * @author John Ryan
 *
 */
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
	
	/**
	 * Ticket constructor used for unit tests
	 */
	public Ticket() {
		setdName("Acer G450 Predator");
		setfName("Dylan");
		setlName("Thomas");
		setCreation(LocalDate.of(2020, 5, 15));
		setProblem("Laptop won't boot");
		String dis = 
				  "Desolder chip A503/Solder new chip A503\n"
				+ "Unscrew right daughterboard/Rescrew right daughterboard\n"
				+ "Detach battery ribbon cable/Reattach battery ribbon cable\n"
				+ "Unscrew back/Rescrew back";
		setDisassemble(dis);	
	}
	
	/**
	 * Constructs a Ticket from a database
	 * @param id - id of ticket
	 * @param dName - device name of ticket
	 * @param fName - first name of ticket
	 * @param lName - last name of ticket
	 * @param date - date ticket was created
	 * @param problem - problem attached to ticket
	 * @param disassemble - stack containing assembly/disassembly steps for ticket
	 */
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
	
	/**
	 *  Constructor for creating a ticket to be pushed to database
	 * @param dName - device name of ticket
	 * @param fName - first name of ticket
	 * @param lName - last name of ticket
	 * @param date - date ticket was created
	 * @param problem - problem attached to ticket
	 * @param disassemble - stack containing assembly/disassembly steps for ticket
	 */
	public Ticket(String dName, String fName, String lName, LocalDate date, String problem, String disassemble) {
		// id will be assigned automatically by database
		setdName(dName);
		setfName(fName);
		setlName(lName);
		setCreation(date);
		setProblem(problem);
		setDisassemble(disassemble);
	}
	
	/**
	 * Converts the database String representing disassemble into a java Stack object
	 * @param disassemble2 - the string to be converted into disassemble stack
	 */
	public void setDisassemble(String disassemble2) {
		disassemble.clear();
		List<String> disassembleList = Arrays.asList(disassemble2.split("\r?\n|\r"));
		for(int i = 0; i < disassembleList.size(); i++) {
			disassemble.push(disassembleList.get(i));
		}
	}


	/**
	 * Represents disassembling one more component by popping disassemble component into reassemble stack
	 */
	public void incrementDisassemble() {
		if(disassemble.size() > 0)
			reassemble.add(disassemble.pop());
	}
	
	/** 
	 * Represents reassembling one more component by popping reassemble component into disassemble stack
	 */
	public void incrementReassemble() {
		if(reassemble.size() > 0)
			disassemble.add(reassemble.pop());
	}
	
	/**
	 * returns a string array that will be used to output ticket information to a table in MainFrame
	 * @param currentRow - sets the row this ticket is placed in on the graph in MainFrame
	 * @return - string array used to output ticket information to table in MainFrame
	 */
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
	
	/**
	 * Outputs a String of all remaining steps in disassemble stack in LIFO order. Used in ViewDialog
	 * @return - String of remaining steps in disassemble stack in LIFO order.
	 */
	public String printDisassembleSteps() {
		String disString = "";	// String that will be returned: lists every disassembly step remaining
		String temp = ""; // where string is temporarily held while operations determining what text is output are performed on it
		@SuppressWarnings("unchecked")
		Stack<String> tempStack = (Stack<String>) getDisassemble().clone();
		int indexEnd = -1;	  // index where the disassembly step ends in this string (before "/") - will be determined in for loop
		for(int i = 0; i < disassemble.size(); i++) {
			temp = tempStack.pop();
			indexEnd = temp.indexOf("/");
			disString += temp.substring(0, indexEnd) + "\n";
		}
		tempStack = null; // garbage collection
		return disString;
	}
	
	/**
	 * Outputs a String of all remaining steps in reassemble stack in LIFO order. Used in ViewDialog
	 * @return - String of remaining steps in reassemble stack in LIFO order.
	 */
	public String printReassembleSteps() {
		String reaString = ""; // string that will be returned: lists every assembly step remaining
		String temp = ""; // where string is temporarily held while operations determining what text is output are performed on it
		@SuppressWarnings("unchecked")
		Stack<String> tempStack = (Stack<String>) getReassemble().clone();
		int indexStart = -1;	  // index where the assembly step starts in this string (after "/"). Will be determined in for loop
		for(int i = 0; i < reassemble.size(); i++) {
			temp = tempStack.pop();
			indexStart = temp.indexOf("/") + 1;
			reaString += temp.substring(indexStart) + "\n";
		}
		tempStack = null; // garbage collection
		return reaString;
	}
	
	/**
	 * Converts disassemble stack to a String for storage in database.
	 */
	public String disassembleToDatabase() {
		String disString = ""; // String that will be returned to database-friendly format
		@SuppressWarnings("unchecked")
		Stack<String> tempStack = (Stack<String>) getDisassemble().clone();
		for(int i = 0; i < disassemble.size(); i++) {
			disString += tempStack.pop() + "\n";
		}
		tempStack = null;	// garbage collection
		return disString;

	}
	
	/**
	 * Resets the disassemble and reassemble stacks to their default state. Used for when
	 * exiting and reentering a ViewDialog
	 */
	public void resetStacks() {
		while(!reassemble.isEmpty())
			incrementReassemble();
	}
	
	// Getters - Setters
	
	public int getId() {
		return id;
	}
	// id should only be set in constructor

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
