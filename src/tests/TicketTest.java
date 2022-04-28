package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Stack;

import org.junit.jupiter.api.Test;

import model.Ticket;
import model.TicketHolder;

public class TicketTest {
		

	@Test
	void testSetDisassemble() {
		// ARRANGE
		Ticket t = new Ticket();
		Stack<String> s = new Stack<String>();
		boolean actual;
		
		t.setDisassemble(
				  "line1/line1\n"
				+ "line2/line2\n");
		s.push("line1/line1");
		s.push("line2/line2");
		// ACT
		actual = s.equals(t.getDisassemble());
		// ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testPrintDisassembleSteps() {
		//ARRANGE
		Ticket t = new Ticket();
		String expected = 
				  "Unscrew back\n"
				+ "Detach battery ribbon cable\n"
				+ "Unscrew right daughterboard\n"
				+ "Desodder chip A503\n";
		boolean actual;
		//ACT
		actual = expected.equals(t.printDisassembleSteps());
		//ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		Ticket t = new Ticket();
		String expected = 
				  "Sodder new chip A503\n"
				+ "Rescrew right daughterboard\n"
				+ "Reattach battery ribbon cable\n"
				+ "Rescrew back\n";
		boolean actual;
		//ACT
		while(t.getDisassemble().size() > 0)
			t.incrementDisassemble();
		actual = expected.equals(t.printReassembleSteps());
		//ASSERT
		assertTrue(actual);
	}
	
	
	void testIncrementDisassemble() {
		//ARRANGE
		Ticket t = new Ticket();
		int disSize = t.getDisassemble().size();
		int reaSize = t.getReassemble().size();
		boolean actual;
		//ACT
		t.incrementDisassemble();
		actual = (disSize > t.getDisassemble().size() && reaSize < t.getReassemble().size());
		//ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testIncrementReassemble() {
		//ARRANGE
		Ticket t = new Ticket();
		int disSize;
		int reaSize;
		boolean actual;
		//ACT
		while(t.getDisassemble().size() > 0)
			t.incrementDisassemble();
		disSize = t.getDisassemble().size();
		reaSize = t.getReassemble().size();
		t.incrementReassemble();
		actual = (disSize < t.getDisassemble().size() && reaSize > t.getReassemble().size());
		//ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testGraphOutput() {
		//ARRANGE
		Ticket t = new Ticket();
		String[] expected = {"Dylan", "Thomas", "2020-05-15", "Acer G450 Predator", "Laptop won't boot"};
		boolean actual = true;
		//ACT
		for(int i = 0; i < expected.length; i++) {
			if(!expected[i].equals(t.graphOutput(0)[i]))
				actual = false;
		}
		//ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testDisassembleToDatabase() {
		//ARRANGE
		Ticket t = new Ticket();
		String expected = 
				  "Unscrew back/Rescrew back\n"
				+ "Detach battery ribbon cable/Reattach battery ribbon cable\n"
			    + "Unscrew right daughterboard/Rescrew right daughterboard\n"
				+ "Desolder chip A503/Solder new chip A503\n";
		boolean actual;
		//ACT
		actual = expected.equals(t.disassembleToDatabase());
		//ASSERT
		assertTrue(actual);
	}
	
}
