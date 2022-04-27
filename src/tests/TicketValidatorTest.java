package tests;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import inputValidation.TicketValidator;
public class TicketValidatorTest {
	
	@Test
	void testIsValidDeviceNameTrue() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual;
		String input = "HP Omen G850";
		// ACT
		actual = tv.isValidDeviceName(input);
		// ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testIsValidFirstNameTrue() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual;
		String input = "Kaylee";
		// ACT
		actual = tv.isValidFirstName(input);
		// ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testIsValidLastNameTrue() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual;
		String input = "O'Brian";
		// ACT
		actual = tv.isValidLastName(input);
		// ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testIsValidProblemTrue() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual;
		String input = "The screen is cracked in multiple places";
		// ACT
		actual = tv.isValidProblem(input);
		// ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testIsValidRepairStepsTrue() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual;
		String input = 
				  "step1/step1\n"
				+ "step2/step2\n"
				+ "step3/step3\n";
		// ACT
		actual = tv.isValidRepairSteps(input);
		// ASSERT
		assertTrue(actual);
	}
	
	@Test
	void testIsValidDeviceNameFalse() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual1;
		boolean actual2;
		String input1 = "";
		// Next string is over 100 characters
		String input2 = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		// ACT
		actual1 = tv.isValidDeviceName(input1);
		actual2 = tv.isValidDeviceName(input2);
		// ASSERT
		assertFalse(actual1);
		assertFalse(actual2);
	}
	
	@Test
	void testIsValidFirstNameFalse() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual1, actual2;
		String input1 = "";
		// Next string over 45 characters
		String input2 = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		// ACT
		actual1 = tv.isValidFirstName(input1);
		actual2 = tv.isValidFirstName(input2);
		// ASSERT
		assertFalse(actual1);
		assertFalse(actual2);
	}
	
	@Test
	void testIsValidLastNameFalse() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual1, actual2;
		String input1 = "";
		// Next string over 45 characters
		String input2 = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		// ACT
		actual1 = tv.isValidLastName(input1);
		actual2 = tv.isValidLastName(input2);
		// ASSERT
		assertFalse(actual1);
		assertFalse(actual2);
	}
	
	@Test
	void testIsValidProblemFalse() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual1, actual2;
		String input1 = "";
		// Next string over 100 characters
		String input2 = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		// ACT
		actual1 = tv.isValidProblem(input1);
		actual2 = tv.isValidProblem(input2);
		// ASSERT
		assertFalse(actual1);
		assertFalse(actual2);
	}
	
	@Test
	void testIsValidRepairStepsFalse() {
		// ARRANGE
		TicketValidator tv = new TicketValidator();
		boolean actual1, actual2;
		String input1 = "a/";
		String input2 = ""; // longer than 10000 characters
		for(int i = 0; i <= 10000; i++)
			input2 += "z";
		// ACT
		actual1 = tv.isValidRepairSteps(input1);
		actual2 = tv.isValidRepairSteps(input2);
		// ASSERT
		assertFalse(actual1);
		assertFalse(actual2);
	}
}
