package inputValidation;

/**
 * This class ensures that no Ticket input is invalid. For an input to be invalid, 
 * it needs to either be empty, or to contain more characters than the maximum 
 * number that can fit in its database field.
 * @author John Ryan
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
	
	public boolean isValidDeviceName(String device) {
		if(device.length() > DEVICE_NAME_MAXLENGTH || device.length() < DEVICE_NAME_MINLENGTH)
			return false;
		return true;
	}
	
	public boolean isValidFirstName(String first) {
		if (first.length() > FIRST_NAME_MAXLENGTH || first.length() < FIRST_NAME_MINLENGTH)
			return false;
		return true;
	}
	
	public boolean isValidLastName(String last) {
		if (last.length() > LAST_NAME_MAXLENGTH || last.length() < LAST_NAME_MINLENGTH)
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
