package inputValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketValidator {
	
	// string of illegal characters for customer names. legal ones outside of A-z are '-.
	private final String ILLEGAL_CHAR_CUSTNAME = "1234567890!@#$%^&*()_=+[{]}\\|;:\"<>/?";

	// Lengths for these fields. Min lengths are set to ensure every field has information
	private final int DEVICE_NAME_MAXLENGTH = 100;
	private final int DEVICE_NAME_MINLENGTH = 1;
	private final int FIRST_NAME_MAXLENGTH = 45;
	private final int FIRST_NAME_MINLENGTH = 1;
	private final int LAST_NAME_MAXLENGTH = 45;
	private final int LAST_NAME_MINLENGTH = 1;
	private final int PROBLEM_MAXLENGTH = 100;
	private final int PROBLEM_MINLENGTH = 4;
	private final int REPAIR_STEPS_MAXLENGTH = 10000;
	private final int REPAIR_STEPS_MINLENGTH = 3;
	
	public TicketValidator() {
		// empty cause all fields we need are final
	}
	
	public boolean isValidDeviceName(String device) {
		if(device.length() > DEVICE_NAME_MAXLENGTH || device.length() < DEVICE_NAME_MINLENGTH)
			return false;
		return true;
	}
	
	public boolean isValidFirstName(String first) {
		if (first.length() > FIRST_NAME_MAXLENGTH || first.length() < FIRST_NAME_MINLENGTH)
			return false;
		Pattern pattern = Pattern.compile(ILLEGAL_CHAR_CUSTNAME);
		Matcher matcher = pattern.matcher(first);
		if(matcher.find())	// if any illegal characters are found in customer name, name isn't valid
			return false;
		// if both checks failed, return true
		return true;
	}
	
	public boolean isValidLastName(String last) {
		if (last.length() > LAST_NAME_MAXLENGTH || last.length() < LAST_NAME_MINLENGTH)
			return false;
		Pattern pattern = Pattern.compile(ILLEGAL_CHAR_CUSTNAME);
		Matcher matcher = pattern.matcher(last);
		if(matcher.find())	// if any illegal characters are found in customer name, name isn't valid
			return false;
		return true;
	}
	
	public boolean isValidProblem(String problem) {
		if(problem.length() > PROBLEM_MAXLENGTH || problem.length() < PROBLEM_MINLENGTH)
			return false;
		return true;
	}
	
	public boolean isValidRepairSteps(String repairSteps) {
		if(repairSteps.length() > REPAIR_STEPS_MAXLENGTH || repairSteps.length() < REPAIR_STEPS_MINLENGTH)
			return false;
		// TODO: Another if statement to check that each line has a slash separating the two messages being left
		return true;
	}
	

}
