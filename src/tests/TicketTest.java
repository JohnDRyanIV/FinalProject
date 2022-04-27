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
		System.out.println(s.toString());
		System.out.println(t.getDisassemble().toString());
		System.out.println(s.pop());
		System.out.println(t.getDisassemble().pop());
		// ACT
		actual = s.equals(t.getDisassemble());
		// ASSERT
		assertTrue(actual);
	}
	
	

}
