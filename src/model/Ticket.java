package model;

import java.util.Stack;

public class Ticket {
	
	private int id;
	private int currentRow; // keeps track of the row this ticket is in on the table
	
	private String dName; // Device name
	private String fName;
	private String lName;
	private Stack<String> disassemble;
	private Stack<String> reassemble;
	
	public Ticket() {
		id = 0;
		currentRow = -1;
		dName = "DUMMY";
		fName = "DUMMY";
		lName = "DUMMY";
	}
	
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
		String[] row = new String[4];
		row[0] = dName;
		row[1] = fName;
		row[2] = lName;
		//row[3] = "blah";
		return row;
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

	public void setlName(String lName) {
		this.lName = lName;
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
