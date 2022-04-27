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
				+ "Unscrew right half of motherboard\n"
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
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	
	/*@Test
	void testPrintReassembleSteps() {
		//ARRANGE
		
		//ACT
		
		//ASSERT
	}
	*/
	

}
