package inputValidation;

/**
 * This class ensures that no Ticket input is invalid. For an input to be invalid, 
 * it needs to either be empty, or to contain more characters than the maximum 
 * number that can fit in its database field.
 * @author John Ryan
 * 
 */
public class TicketValidator {
	
	// Lengths for these fields. Min lengths are set to ensure every field has information
	private final int DEVICE_NAME_MINLENGTH = 1;
	private final int DEVICE_NAME_MAXLENGTH = 100;
	
	private final int FIRST_NAME_MINLENGTH = 1;
	private final int FIRST_NAME_MAXLENGTH = 45;
	
	private final int LAST_NAME_MINLENGTH = 1;
	private final int LAST_NAME_MAXLENGTH = 45;
	
	private final int PROBLEM_MINLENGTH = 4;
	private final int PROBLEM_MAXLENGTH = 100;
	
	private final int REPAIR_STEPS_MINLENGTH = 3;
	private final int REPAIR_STEPS_MAXLENGTH = 10000;

	
	public TicketValidator() {
		// empty cause all fields we need are final
	}
	
	/**
	 * Checks to see if the device name is valid. A valid device must be between 1 and 100
	 * characters, inclusive.
	 * @param device - String that's being validated
	 * @return - true if valid, false if invalid
	 */
	public boolean isValidDeviceName(String device) {
		if(device.length() > DEVICE_NAME_MAXLENGTH || device.length() < DEVICE_NAME_MINLENGTH)
			return false;
		return true;
	}
	
	/**
	 * Checks to see if the first name is valid. A valid first name must be between 1 and
	 * 45 characters, inclusive.
	 * @param device - String that's being validated
	 * @return - true if valid, false if invalid
	 */
	public boolean isValidFirstName(String first) {
		if (first.length() > FIRST_NAME_MAXLENGTH || first.length() < FIRST_NAME_MINLENGTH)
			return false;
		return true;
	}
	
	/**
	 * Checks to see if the last name is valid. A valid last name must be between 1 and
	 * 45 characters, inclusive
	 * @param device - String that's being validated
	 * @return - true if valid, false if invalid
	 */
	public boolean isValidLastName(String last) {
		if (last.length() > LAST_NAME_MAXLENGTH || last.length() < LAST_NAME_MINLENGTH)
			return false;
		return true;
	}
	
	/**
	 * Checks to see if the problem description is valid. A valid problem description
	 * must be between 4 and 100 characters long, inclusive.
	 * @param device - String that's being validated
	 * @return - true if valid, false if invalid
	 */
	public boolean isValidProblem(String problem) {
		if(problem.length() > PROBLEM_MAXLENGTH || problem.length() < PROBLEM_MINLENGTH)
			return false;
		return true;
	}
	
	/**
	 * Checks to see if the repair steps are valid. A valid repair step string must be
	 * between 3 and 10000 characters, inclusive.
	 * @param device - String that's being validated
	 * @return - true if valid, false if invalid
	 */
	public boolean isValidRepairSteps(String repairSteps) {
		if(repairSteps.length() > REPAIR_STEPS_MAXLENGTH || repairSteps.length() < REPAIR_STEPS_MINLENGTH)
			return false;
		return true;
	}
	

}
